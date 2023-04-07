package com.learn.realtime.springboot.inventorylisting.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/// Request Entity
/// POST PUT PATCH  -> RequestEntity to get data
@RestController
@RequestMapping("/api")
public class ApplicationStatusController {

    private static Logger logger = LoggerFactory.getLogger(ApplicationStatusController.class);

    @Tag(name = "Application Status")
    @Operation(summary = "Application status", description = "Application is running or shutdown")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully fetched the application status"),
                    @ApiResponse(responseCode = "500", description = "Application is down")
            }
    )

    @GetMapping("/status")
    public ResponseEntity<String> getApplicationStatus(){

        for (int i =0 ; i < 1000; i++){
            logger.trace("Application is shutdown");
            logger.error("Application is error");
        }

        return ResponseEntity.ok("Application is running");
    }
}
