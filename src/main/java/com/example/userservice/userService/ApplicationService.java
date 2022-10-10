package com.example.userservice.userService;

import com.example.userservice.exception.BadRequest;
import com.example.userservice.model.Application;
import com.example.userservice.model.Job;
import com.example.userservice.model.User;
import com.example.userservice.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ApplicationService {
    @Autowired
    ApplicationRepository appRepository;
    @Autowired
    UserService userService;
    @Autowired
    JobService jobService;
    public Boolean createApp(Application app) {
        app.setCreatedAt(LocalDateTime.now());
        app.setStatus(true);
        appRepository.save(app);
        return true;
    }

    public Application getApp(Integer id) {
        Optional<Application> optional = appRepository.findById(id);
        if(optional.isEmpty()) {
            throw new BadRequest("Application not found!");
        }
        return optional.get();
    }

    public Boolean updateApp(Application app, Integer id) {
        Application a = getApp(id);
        a.setUser_id(app.getUser_id());
        a.setJob_id(app.getJob_id());
        a.setSalary(app.getSalary());
        a.setUpdatedAt(LocalDateTime.now());
        appRepository.save(app);
        return true;
    }

    public Boolean deleteApp(Integer id) {
        Application app = getApp(id);
        appRepository.delete(app);
        return true;
    }

    public List<Application> getAllApps(){
        return appRepository.findAll();
    }

    public List<User> getUsersFromApp(){
        List<User> users = new LinkedList<>();
        for (Application app: getAllApps()) {
            users.add(app.getUser());
        }
        if(users.isEmpty()){
            throw new BadRequest("Application list is empty!");
        }
        return users;
    }

    public List<Job> getJobsFromApp(){
        List<Job> jobs = new LinkedList<>();
        for (Application app: getAllApps()) {
            jobs.add(app.getJob());
        }
        if(jobs.isEmpty()){
            throw new BadRequest("Application list is empty!");
        }
        return jobs;
    }
}
