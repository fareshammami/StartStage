package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"events.dewdrop"})
@EntityScan(basePackages = {"events.dewdrop.entities"})
@EnableMongoRepositories(basePackages = {"events.dewdrop.repository"})
public class DewdropDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DewdropDemoApplication.class, args);
    }
}
