package com.guptem.journalApp.controllers;

import com.guptem.journalApp.entities.User;
import com.guptem.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/createUser")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userService.createNewUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(path = "/health-check")
    public String getHealthStatus() {
        return "OK";
    }

}
