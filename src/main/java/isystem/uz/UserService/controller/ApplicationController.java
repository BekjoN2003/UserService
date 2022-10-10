package isystem.uz.UserService.controller;

import isystem.uz.UserService.model.Application;

import isystem.uz.UserService.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/application")
public class ApplicationController {


    private final ApplicationService applicationService;

    @Autowired
    private ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createApp(@RequestBody Application application) {
        Application resul = applicationService.create(application);
        return ResponseEntity.ok(resul);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getApp(@PathVariable("id") Integer id){
        Application result = applicationService.get(id);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateApp(@RequestBody Application application,
                                       @PathVariable("id") Integer id){
        Application result = applicationService.update(id, application);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteApp(@PathVariable("id") Integer id){
        Application result = applicationService.delete(id);
        return ResponseEntity.ok(result);
    }

}