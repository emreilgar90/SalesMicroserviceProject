package com.emre;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserProfileMicroserviceApplication {
    public static void main(String[] args) {

        SpringApplication.run(UserProfileMicroserviceApplication.class, args);
    }

}