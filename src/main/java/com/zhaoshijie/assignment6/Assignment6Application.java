package com.zhaoshijie.assignment6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Assignment6Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment6Application.class, args);
    }

}
