package com.guptem.journalApp.controllers;

import com.guptem.journalApp.entities.User;
import com.guptem.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/createUser")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userService.createUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/getUserById/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable Long Id) {
        return new ResponseEntity<>(userService.getUserById(Id), HttpStatus.OK);
    }

    @PutMapping(path = "/updateUser/id/{Id}")
    public ResponseEntity<?> updateUser(@PathVariable Long Id, @RequestBody User newUser) {
        return new ResponseEntity<>(userService.updateUser(Id, newUser), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long Id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
