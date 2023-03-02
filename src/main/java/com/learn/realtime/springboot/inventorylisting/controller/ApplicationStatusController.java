package com.learn.realtime.springboot.inventorylisting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationStatusController {

    @GetMapping("/status")
    public ResponseEntity<String> getApplicationStatus(){

        return ResponseEntity.ok("Application is running");
    }
}
