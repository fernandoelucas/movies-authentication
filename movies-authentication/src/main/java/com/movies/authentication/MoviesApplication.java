package com.movies.authentication;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesApplication {


    @PostConstruct
    private void init() {
        TimeZone.setDefault(TimeZone.getDefault());
        System.out.println("Spring boot application running in UTC timezone :"+new Date());
    }
    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }
}
