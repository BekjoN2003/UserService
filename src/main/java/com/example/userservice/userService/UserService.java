package com.example.userservice.userService;

import com.example.userservice.exception.BadRequest;
import com.example.userservice.model.Application;
import com.example.userservice.model.Job;
import com.example.userservice.model.User;
import com.example.userservice.repository.ApplicationRepository;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository appRepository;

    public Boolean createUSer(User user){
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(true);
        userRepository.save(user);
        return true;
    }

    public User getUser(Integer id){
        Optional<User> optional = userRepository.findById(id);
        if(optional.isEmpty()) throw new BadRequest("User not found!");
        return optional.get();
    }

    public Boolean updateUser(User user, Integer id){
        User u = getUser(id);
        u.setUpdatedAt(LocalDateTime.now());
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setAddress(user.getAddress());
        u.setAddress2(user.getAddress2());
        u.setAvatar(user.getAvatar());
        u.setCityId(user.getCityId());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        userRepository.save(user);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        User user = getUser(id);
        userRepository.delete(user);
        return true;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<Job> getUserJobs(Integer userId){
        List<Application> applications = getAppByUser(userId);
        List<Job> jobs = new LinkedList<>();
        for (Application app: applications) {
            jobs.add(app.getJob());
        }
        return jobs;
    }


    public Double getAllSalary(Integer userId) {
        List<Application> applications = getAppByUser(userId);
        return Salary.getSalary(applications);
    }

    public Double getSalaryByDate(Integer userId, String date) {
        List<Application> applications = appRepository.findByUser_id(userId);
        for (Application app : applications) {

            if (app.getStartDate().isBefore(LocalDate.parse(date))) {
                if(app.getEndDate()==null && LocalDate.now().isAfter(LocalDate.parse(date))) {
                    return app.getSalary();
                }
                else if(app.getEndDate().isAfter(LocalDate.parse(date))) {
                    return app.getSalary();
                }
            }
        }
        throw new BadRequest("Please enter another date!");
    }

    public String getExperience(Integer uderId) {
        List<Application> applications = getAppByUser(uderId);
        long month = 0;
        for (Application app : applications) {
            if(!app.getStatus()){
                month += ChronoUnit.MONTHS.between(app.getStartDate(), app.getEndDate());
            }
            else month += ChronoUnit.MONTHS.between(app.getStartDate(), LocalDate.now());
        }
        return String.format("Quantity experience per month: %d", month);
    }


    public List<Job> getJobByDate(Integer id, String date) {
        getUser(id);
        List<Application> applications = appRepository.findByUser_id(id);
        List<Job> jobList = new LinkedList<>();
        for(Application app:applications) {
           jobList.add(app.getJob());
        }
        return jobList;
    }

    private List<Application> getAppByUser(Integer id){
        getUser(id);
        List<Application> applications = appRepository.findByUser_id(id);
        if(applications.isEmpty()){
            throw new BadRequest("No working people found!");
        }
        return applications;
    }
}
