package com.learn.realtime.springboot.inventorylisting.controller.api;

import com.learn.realtime.springboot.inventorylisting.config.custom.bean.CustomBeanCreator;
import com.learn.realtime.springboot.inventorylisting.model.HikariConfig;
import com.learn.realtime.springboot.inventorylisting.readApplicationProperties.DatasourceConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private String poolSize;

    @Autowired
    private Environment environment;

    @Autowired
    private HikariConfig hikariConfig;

    @Autowired
    private DatasourceConfig datasourceConfig;

    @Autowired
    private CustomBeanCreator customBeanCreator;

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

        System.out.println(hikariConfig.getConnectionTimeout());
        System.out.println(hikariConfig.getIdleTimeout());
        System.out.println(hikariConfig.getPoolName());
        System.out.println(hikariConfig.getMinimumIdle());
        System.out.println(hikariConfig.getPoolName());
      //  System.out.println(environment.getProperty("spring.datasource.hikari.poolName"));

        System.out.println("*********************************************************");

        System.out.println(datasourceConfig.getPassword());
        System.out.println(datasourceConfig.getHikari().getPoolName());

        System.out.println( customBeanCreator.getCompany().getName());
        return ResponseEntity.ok("Application is running  at server port = "+ poolSize);
    }
}
