package com.learn.realtime.springboot.inventorylisting.config.custom.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CustomBeanCreator {

    @Bean
    @Profile("dev")
    public Company getCompany(){
        return Company.builder().name("Wipro").build();
    }
}
