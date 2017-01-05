package com.jazs.highscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.jazs.highscore") 
public class HighscoreApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HighscoreApplication.class, args);
    }
	
}
