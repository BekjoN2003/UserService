package com.example.userservice.controller;

import com.example.userservice.model.Application;
import com.example.userservice.model.Job;
import com.example.userservice.model.User;
import com.example.userservice.userService.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> createApp(@RequestBody Application app){
        Boolean result = applicationService.createApp(app);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getApp(@PathVariable("id") Integer id){
        Application result = applicationService.getApp(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApp(@RequestBody Application app,
                                        @PathVariable("id") Integer id){
        Boolean result = applicationService.updateApp(app,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApp(@PathVariable("id") Integer id){
        Boolean result = applicationService.deleteApp(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAllApps(){
        List<Application> result = applicationService.getAllApps();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsersFromApp(){
        List<User> result = applicationService.getUsersFromApp();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getJobs")
    public ResponseEntity<?> getJobsFromApp(){
        List<Job> result = applicationService.getJobsFromApp();
        return ResponseEntity.ok(result);
    }
}
