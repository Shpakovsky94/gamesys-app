package com.task.gamesys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GamesysApplication {

  public static void main(String[] args) {
    SpringApplication.run(GamesysApplication.class, args);
  }
}
