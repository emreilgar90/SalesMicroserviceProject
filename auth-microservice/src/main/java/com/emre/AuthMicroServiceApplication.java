package com.emre;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
/**1.ADIM Bu anatasyonu yazıyoruz ki FeignClient aktif olsun.
 Burada yapamaya çalışacağımız şey; Auth service de kullanıcı kaydettiğimiz
 anda UserProfileService de bir User oluştursun. Fieldlarının içerisinde
 authid de olacak.*/
public class AuthMicroServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(AuthMicroServiceApplication.class, args);
    }
}