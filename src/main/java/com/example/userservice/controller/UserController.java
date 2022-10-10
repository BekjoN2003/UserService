package com.example.userservice.controller;

import com.example.userservice.model.Job;
import com.example.userservice.model.User;
import com.example.userservice.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        Boolean result = userService.createUSer(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id){
        User rersult = userService.getUser(id);
        return ResponseEntity.ok(rersult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user,
                                        @PathVariable("id") Integer id){
        Boolean result = userService.updateUser(user,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        Boolean result = userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> result = userService.getAllUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getJobs(@PathVariable("id") Integer id){
        List<Job> result = userService.getUserJobs(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/allSalary/{id}")
    public ResponseEntity<?> getAllSalary(@PathVariable("id") Integer id){
        Double result = userService.getAllSalary(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getSalary/{id}/{date}")
    public ResponseEntity<?> getSalaryByDate(@PathVariable("id") Integer id,
                                             @PathVariable("date") String date){
        Double result = userService.getSalaryByDate(id,date);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/expirience/{id}")
    public ResponseEntity<?> getExpirience(@PathVariable("id") Integer userId){
        String result = userService.getExperience(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getJob/{id}/{date}")
    public ResponseEntity<?> getJobByDate(@PathVariable("id") Integer id,
                                             @PathVariable("date") String date){
        List<Job> result = userService.getJobByDate(id,date);
        return ResponseEntity.ok(result);
    }
}
