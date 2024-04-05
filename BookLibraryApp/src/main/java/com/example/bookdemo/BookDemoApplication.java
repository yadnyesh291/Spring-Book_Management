package com.example.bookdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@SpringBootApplication
@EntityScan("com.example.bookdemo.model")
public class BookDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookDemoApplication.class, args);
    }
}
