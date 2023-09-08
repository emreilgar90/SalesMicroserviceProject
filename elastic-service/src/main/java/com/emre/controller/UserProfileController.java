package com.emre.controller;

import com.emre.dto.request.UserProfileSaveRequestDto;
import com.emre.mapper.IUserProfileMapper;
import com.emre.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userprofile")  /**yml da bu yolu yazdım elasticsearch: http://localhost:9100/userprofile **/
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;


    /**Birisi kayıt olduğunda önbelleğe kayıt bilgisi dursun diye yapıyoruz.**/
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto){
        /**Gelen dto'yu doğrudan userProfileService de save metodu ile kaydedemeyiz ! dolayısıyla
         * mapper kullanıp nesneye çevirmemiz lazım **/
        userProfileService.save(IUserProfileMapper.INSTANCE.fromUserProfileDto(dto));
       return ResponseEntity.ok().build();
    }
}
