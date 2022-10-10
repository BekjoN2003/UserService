package com.example.userservice.userService;

import com.example.userservice.exception.BadRequest;
import com.example.userservice.model.Application;
import com.example.userservice.model.Job;
import com.example.userservice.model.User;
import com.example.userservice.repository.ApplicationRepository;
import com.example.userservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ApplicationRepository appRepository;
    public Boolean createJob(Job job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setStatus(true);
        jobRepository.save(job);
        return true;
    }

    public Job getJob(Integer id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isEmpty()) throw new BadRequest("Job not found!");
        return optionalJob.get();
    }

    public Boolean updateJob(Job job, Integer id) {
        Job j = getJob(id);
        j.setTitle(job.getTitle());
        j.setDescription(job.getDescription());
        j.setUpdatedAt(LocalDateTime.now());
        jobRepository.save(j);
        return true;
    }

    public Boolean deleteJob(Integer id) {
        Job job = getJob(id);
        jobRepository.delete(job);
        return true;
    }

    public List<Job> getJobs(){
        return jobRepository.findAll();
    }

    public List<User> getUsersByJob(Integer jobId){
        List<Application> applications = findByJobId(jobId);
        List<User> users = new LinkedList<>();
        for (Application app:applications) {
            users.add(app.getUser());
        }
        return users;
    }

    public Double getAllSalaryByJobId(Integer jobId){
        List<Application> applications = findByJobId(jobId);
        return Salary.getSalary(applications);
    }

    private List<Application> findByJobId(Integer jobId){
        getJob(jobId);
        List<Application> applications = appRepository.findByJob_id(jobId);
        if(applications.isEmpty()) {
            throw new BadRequest("Job is not in the applications");
        }
        return applications;
    }
}
