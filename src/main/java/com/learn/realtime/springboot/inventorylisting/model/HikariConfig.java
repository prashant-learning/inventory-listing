package com.learn.realtime.springboot.inventorylisting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Configuration
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HikariConfig {

    private int connectionTimeout;
    private int idleTimeout;
    private int maximumPoolSize;
    private int minimumIdle;
    private String poolName;


}
