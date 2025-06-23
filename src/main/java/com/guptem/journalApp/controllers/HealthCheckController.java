package com.guptem.journalApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HealthCheckController {

    @GetMapping(path = "/health-check")
    public String getHealthStatus() {
        return "OK";
    }

}
