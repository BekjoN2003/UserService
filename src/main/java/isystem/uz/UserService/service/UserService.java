package isystem.uz.UserService.service;

import isystem.uz.UserService.exception.BadRequestException;
import isystem.uz.UserService.model.Application;
import isystem.uz.UserService.model.Job;
import isystem.uz.UserService.model.User;
import isystem.uz.UserService.repository.ApplicationRepository;
import isystem.uz.UserService.repository.UserRepository;
import isystem.uz.UserService.type.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

//zimport java.util.stream.Collectors;

@Service
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;


    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(true);
        user.setUserType(UserType.VISITOR);
        userRepository.save(user);
        return user;
    }

    public User get(Integer id) {
        return getEntity(id);
    }

    public User update(Integer id, User user) {
        User entity = getEntity(id);
        convertToEntity(user, entity);
        entity.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(entity);
    }

    public User delete(Integer id) {
        User user = getEntity(id);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.delete(user);
        return user;
    }

    public List<User> getAll(Integer size, Integer page) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findAll(pageRequest);

        List<User> userList = new LinkedList<>();

        for (User u : userPage) {
            userList.add(u);
        }
        return userList;
    }

    public List<Job> getJobs(Integer id) {
        getEntity(id);
        List<Application> applicationList = applicationRepository.findByUserId(id);
        if (applicationList.isEmpty()) {
            throw new BadRequestException("user has not any job");
        }
        List<Job> jobList = new LinkedList<>();
        for (Application application : applicationList) {
            jobList.add(application.getJob());
        }
        return jobList;
//        return applicationList.stream().map(Application::getJob).collect(Collectors.toList());
    }

    private User getEntity(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        return optional.get();
    }


    private void convertToEntity(User user, User entity) {
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        entity.setBirth(user.getBirth());
        entity.setAvatar(user.getAvatar());
        entity.setAddress(user.getAddress());
        entity.setAddress2(user.getAddress2());
        entity.setCityId(user.getCityId());
        entity.setPostcode(user.getPostcode());
        entity.setEmailVerifiedAt(user.getEmailVerifiedAt());
        entity.setPhoneVerifiedAt(user.getPhoneVerifiedAt());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setDeletedAt(user.getDeletedAt());
        entity.setQrCode(user.getQrCode());
        entity.setStatus(user.getStatus());
    }

    public Double getSumSalary(Integer id) {
        getEntity(id);
        List<Application> applicationList = applicationRepository.findByUserId(id);
        if (applicationList.isEmpty()) {
            throw new BadRequestException("User has not any job");
        }


        double sum = 0.0;
        for (Application application : applicationList) {
            if (application.getEndDate() == null) {
                long month = ChronoUnit.MONTHS.between(application.getStartDate(),
                        LocalDate.now());
                sum += month * application.getSalary();
            } else {
                long month = ChronoUnit.MONTHS.between(application.getStartDate(), application.getEndDate());
                sum += month * application.getSalary();
                sum += application.getSalary() / application.getEndDate().lengthOfMonth() *
                        application.getEndDate().getDayOfMonth();
            }
        }
        return sum;
    }

    public Double getSalaryByMonth(Integer id, LocalDate date) {
        getEntity(id);
       List<Application> applicationList = applicationRepository.salaryByMont(id, date);
        double sum = 0.0;
        for (Application application:applicationList) {
            sum += application.getSalary();
        }
        return sum;
    }


}
