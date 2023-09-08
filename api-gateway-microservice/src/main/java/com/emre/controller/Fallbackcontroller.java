package com.emre.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Fallbackcontroller {
    @GetMapping("/fallbackauth")
    public ResponseEntity<String> fallbackauth(){
        return ResponseEntity.ok("Auth Service şuan çalışmıyor.");
    }

    @GetMapping("/fallbackproduct")
    public ResponseEntity<String> fallbackproducrt(){
        return ResponseEntity.ok("Product Service şuan çalışmıyor.");
    }
    @GetMapping("/fallbacksales")
    public ResponseEntity<String> fallbacksales(){
        return ResponseEntity.ok("Sales Service şuan çalışmıyor.");
    }
    @GetMapping("/fallbackuser")
    public ResponseEntity<String> fallbackuser(){
        return ResponseEntity.ok("Userprofile Service şuan çalışmıyor.");
    }
}
