package userdetails.controllers;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import userdetails.entities.User;
import userdetails.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService service;
    Logger logger=LoggerFactory.getLogger(UserController.class);

    private final JobLauncher jobLauncher;
    private final Job job;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        List<User> userList = service.getAllUsers();
        HttpStatus status = !userList.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        //String message = !userList.isEmpty() ? "users data" + userList : "Notfound";
        return ResponseEntity.status(status).body(userList);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable int userid) {
        Optional<User> user = service.getUserById(userid);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody @Valid User u) {
            service.add(u);
            return ResponseEntity.ok("user added successfully") ;
    } 
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userid}")
    public ResponseEntity<?> updateUser(@RequestBody User u, @PathVariable int userid) {
        service.update(u, userid);
        return ResponseEntity.ok("user updated");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userid) {
        service.delete(userid);
        return ResponseEntity.ok("user deleted");
    }

    @GetMapping("/load")
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException
                 | JobRestartException
                 | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
