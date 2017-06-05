package com.example;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.rest", "com.example.service", "com.example.config"})
@EntityScan("com.example.model")
@EnableJpaRepositories("com.example.repository")

public class ExampleApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ExampleApp.class, args);
    }

}