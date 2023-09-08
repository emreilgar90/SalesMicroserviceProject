package com.emre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SalesMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalesMicroServiceApplication.class);
    }
}