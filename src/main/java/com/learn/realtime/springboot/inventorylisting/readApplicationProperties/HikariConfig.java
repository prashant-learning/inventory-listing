package com.learn.realtime.springboot.inventorylisting.readApplicationProperties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HikariConfig {

    private int connectionTimeout;
    private int idleTimeout;
    private int maximumPoolSize;
    private int minimumIdle;
    private String poolName;

}
