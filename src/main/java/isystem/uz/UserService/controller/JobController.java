package isystem.uz.UserService.controller;

import isystem.uz.UserService.model.Job;
import isystem.uz.UserService.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    @Autowired
    private JobController(JobService jobService){
        this.jobService = jobService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createJob(@RequestBody Job job){
        boolean resul = jobService.create(job);
        return ResponseEntity.ok(resul);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable("id") Integer id){
        Job result = jobService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJob(@RequestBody Job job,
                                        @PathVariable("id") Integer id){
        Job result = jobService.update(id, job);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable("id") Integer id){
        Job result = jobService.delete(id);
        return ResponseEntity.ok(result);
    }


}
