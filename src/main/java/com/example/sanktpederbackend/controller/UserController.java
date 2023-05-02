package com.example.sanktpederbackend.controller;

import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.model.user.User;
import com.example.sanktpederbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUsersById(@PathVariable Integer id) {
        return userService.getUsersById(id).orElseThrow(() -> new RuntimeException("Users with id: " + id + " not found"));
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return userService.postUser(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> putItem(@PathVariable Integer id, @RequestBody User user) {
        return userService.putUser(user, id);
    }

    // put multiple items
    @PutMapping("/user")
    public ResponseEntity<User> putItems(@RequestBody List<User> users) {
        return userService.putUsers(users);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}

