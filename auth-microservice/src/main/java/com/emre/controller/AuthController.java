package com.emre.controller;

import com.emre.dto.request.DoLoginRequestDto;
import com.emre.dto.response.DoLoginResponseDto;
import com.emre.dto.request.RegisterRequestDto;
import com.emre.dto.response.RegisterResponseDto;

import com.emre.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;






import javax.validation.Valid;


import static com.emre.constants.RestApi.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;
    /**
     * Bir end point e istek atarken farklı yollarla parametre gönderilebilir
     * 1- Header, başlık içinde
     * 2-Body, from elementi içinde
     * burada body içinde parametreleri almak güvenlidir ---> @RequestBody
     * @Valid ---> Girilen bilgilerin belli kriterleri sağlaması için belirlediğimiz
     * formatta girilmesini sağlar. SpringBoot Validasyonudur, bağımlılık olarak eklemek
     * lazım. **/
    @PostMapping(DOLOGIN)   //Veri göndermek için post
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(
                DoLoginResponseDto.builder()
                   .token(authService.doLogin(dto))
                   .build());
    }
    @CrossOrigin("*")
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.save(dto));
    }

    @GetMapping("/say")
    @PreAuthorize("hasAuthority('EDITOR') or hasAnyAuthority('ADMIN')") //BU YETKİYE SAHİPSE ERİŞEBİLİR
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Selam");
    }
}
