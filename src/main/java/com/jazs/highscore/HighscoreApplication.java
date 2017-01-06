package com.jazs.highscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
@ComponentScan("com.jazs.highscore") 
public class HighscoreApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HighscoreApplication.class);
	}
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HighscoreApplication.class, args);
    }
	
}
