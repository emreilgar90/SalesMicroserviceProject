package com.emre.controller;

import com.emre.dto.request.BaseRequestDto;
import com.emre.dto.request.CreateProfileRequestDto;
import com.emre.repository.entity.UserProfile;
import com.emre.service.UserProfileService;
import com.emre.utillity.TokenGenerator;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;

import static com.emre.constants.RestApis.*;

@RestController
@RequestMapping(USERPROFILE)
@RequiredArgsConstructor

public class UserProfileController {

    /**@RequestBody @Valid   muhakkak eklenecek !*/

    private final UserProfileService userProfileService;
    private final TokenGenerator tokenGenerator;
    @PostMapping(GETALL)
    public  ResponseEntity <List<UserProfile>> userProfileList(@RequestBody @Valid BaseRequestDto dto){
        /**Tüm kullanıcıları getirmek için BaseRequestDto oluşturup içerisinde Token oluşturdum
         * gelen token'ı tokenGenerator da encode ettim ! */

        return ResponseEntity.ok(userProfileService.findAll(dto));
    }



    @PostMapping(CREATEPROFILE)
    @CrossOrigin("*")
    /**Bu endpoint dışarıya açılmayacak sadece Auth istek atacak.*/
    public ResponseEntity<Boolean> createProfile(@RequestBody @Valid CreateProfileRequestDto dto){
        userProfileService.save(
                UserProfile.builder()
                        .authid(dto.getAuthid())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .build()
        );
        return ResponseEntity.ok(true);
    }

    @GetMapping(GETALL) /**gateway denemek için yazdım metodu*/
    public  ResponseEntity <List<UserProfile>> userProfileList(){
        return ResponseEntity.ok(userProfileService.findAll());
    }


}
