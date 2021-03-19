package com.task.gamesys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.task.gamesys")
public class GamesysApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesysApplication.class, args);
    }

}
