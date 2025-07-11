package com.guptem.journalApp.controllers;

import com.guptem.journalApp.api.response.WeatherResponse;
import com.guptem.journalApp.entities.User;
import com.guptem.journalApp.services.UserService;
import com.guptem.journalApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/getUserById/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable Long Id) {
        return new ResponseEntity<>(userService.getUserById(Id), HttpStatus.OK);
    }

    @PutMapping(path = "/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {
        return new ResponseEntity<>(userService.updateUser(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long Id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path =  "/checkLog")
    public boolean checkLog(@RequestBody User user) {
        boolean val = userService.saveNewUser(user);
        return val;
    }

    @Autowired
    private WeatherService weatherService;

    @GetMapping(path = "/greeting")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("MUMBAI");

        String greeting = "Hi " + authentication.getName();

        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greeting += ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        } else {
            greeting += ", Weather info not available";
        }

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

}
