package isystem.uz.UserService.controller;

import isystem.uz.UserService.model.Job;
import isystem.uz.UserService.model.User;
import isystem.uz.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired//
    private UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user){
        User resul = userService.create(user);
        return ResponseEntity.ok(resul);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id){
        User result = userService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user,
                                        @PathVariable("id") Integer id){
        User result = userService.update(id,user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        User result = userService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam("size") Integer size,
                                  @RequestParam("page") Integer page){

        List<User> result = userService.getAll(size, page);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}/jobs")
    public ResponseEntity<?> getJobs(@PathVariable("id") Integer id){
        List<Job> result = userService.getJobs(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/salary/sum")
    public ResponseEntity<?> getSumSalary(@PathVariable("id") Integer id){
        Double result = userService.getSumSalary(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/salary/{date}")
    public ResponseEntity<?> getSumSalary(@PathVariable("id") Integer id,
                                          @PathVariable("date") String date){
        Double result = userService.getSalaryByMonth(id, LocalDate.parse(date));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<?> getPresentSalary(@PathVariable("id") Integer id){
        Double result = userService.getSalaryByMonth(id, LocalDate.now());
        return ResponseEntity.ok(result);
    }
}
