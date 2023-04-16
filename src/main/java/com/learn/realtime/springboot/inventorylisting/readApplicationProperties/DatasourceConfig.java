package com.learn.realtime.springboot.inventorylisting.readApplicationProperties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.datasource")
@Configuration
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatasourceConfig {

    private String driverClassName;
    private HikariConfig hikari;
    private String password;
    private String type;
    private String url;
    private String username;
}
