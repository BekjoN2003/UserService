package com.example.userservice.controller;

import com.example.userservice.model.Job;
import com.example.userservice.model.User;
import com.example.userservice.userService.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    JobService jobService;

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job){
        Boolean result = jobService.createJob(job);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable("id") Integer id){
        Job result = jobService.getJob(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody Job job,
                                        @PathVariable("id") Integer id){
        Boolean result = jobService.updateJob(job,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable("id") Integer id){
        Boolean result = jobService.deleteJob(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAllJobs(){
        List<Job> result = jobService.getJobs();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<?> getUsersByJob(@PathVariable("id") Integer id){
        List<User> result = jobService.getUsersByJob(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/allSalary/{id}")
    public ResponseEntity<?> getAllSalary(@PathVariable("id") Integer id){
        Double result = jobService.getAllSalaryByJobId(id);
        return ResponseEntity.ok(result);
    }
}
