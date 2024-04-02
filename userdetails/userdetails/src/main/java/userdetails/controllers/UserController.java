package userdetails.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import userdetails.entities.Role;
import userdetails.entities.User;
import userdetails.exceptions.UserNotFoundException;
import userdetails.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        List<User> userList = service.getAllUsers();
        HttpStatus status = !userList.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String message = !userList.isEmpty() ? "users data" + userList : "Notfound";
        return ResponseEntity.status(status).body(message);
    }
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        List<Role> roleList = service.getAllRoles();
        HttpStatus status = !roleList.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String message = !roleList.isEmpty() ? "users data" + roleList : "Notfound";
        return ResponseEntity.status(status).body(message);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable int userid) {
        Optional<User> user = service.getUserById(userid);
        // HttpStatus status = user.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        // String message = user.isPresent() ? "user present" + user : "user not found";
        // return ResponseEntity.status(status).body(message);
        if(user.isPresent())
            return ResponseEntity.ok(user);
        throw new UserNotFoundException("user "+userid+" not found");
    }

    // @GetMapping("/getUsersByName/{userName}")
    // public ResponseEntity<?> getUserByName(@PathVariable int userName) {
    // Optional<User> user = service.getUserById(userName);
    // if (user == null)
    // return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    // return new ResponseEntity<>(user, HttpStatus.OK);
    // }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody @Valid User u) {
        int user = service.add(u);
        HttpStatus status = user == 1 ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED;
        String message = user == 1 ? "user added" : "user already present";
        return ResponseEntity.status(status).body(message);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<?> updateUser(@RequestBody User u, @PathVariable int userid) {
        int user = service.update(u, userid);
        HttpStatus status = user == 1 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String message = user == 1 ? "user updated" : "user not found";
        return ResponseEntity.status(status).body(message);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<String> deleteUserById(@PathVariable int userid) {
        int result = service.delete(userid);
        HttpStatus status = result == 1 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String message = result == 1 ? "User deleted" : "User not found";
        return ResponseEntity.status(status).body(message);
    }

}
