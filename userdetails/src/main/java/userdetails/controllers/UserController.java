package userdetails.controllers;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import userdetails.entities.User;
import userdetails.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


//@EnableOAuth2Client
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService service;
    Logger logger=LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        List<User> userList = service.getAllUsers();
        HttpStatus status = !userList.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        //String message = !userList.isEmpty() ? "users data" + userList : "Notfound";
        return ResponseEntity.status(status).body(userList);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable int userid) {
        Optional<User> user = service.getUserById(userid);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody @Valid User u) {
            service.add(u);
            return ResponseEntity.ok("user added successfully") ;
    } 
    

    @PutMapping("/{userid}")
    public ResponseEntity<?> updateUser(@RequestBody User u, @PathVariable int userid) {
        service.update(u, userid);
        return ResponseEntity.ok("user updated");
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userid) {
        service.delete(userid);
        return ResponseEntity.ok("user deleted");
    }

}
