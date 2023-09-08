package com.emre.manager;

import com.emre.dto.request.CreateProfileRequestDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**2. olarak manager adı altında bir paket oluşturuyoruz ve istek atacağımız
 * service adında bir interface oluşturuyoruz. Aşağıda görüldüğü gibi
 * Usermicroservice'inde ki UserProfileController'i na
 * ulaşsın diye IUserProfileManager oluşturduk.
 *
 * 3- Buraya da @FeignClient anatasyonu yazıp isim ver.
 * isimlendirme benzersiz olmalı.
 * 4-url le ulaşmak istediğin microservice'in ayağa kalktığı portu
 * burada userprofilemicroservice i 9093 de kalkıyor ve
 * controller'ının adresi; v1/dev/userprofile
 * 5-Karşı tarafın controllerında ulaşmak istediğin metodun
 * yani CREATEPROFILE metonunun gövdesini kopyalayıp buraya yapıştır !
 * 6-CreateProfileRequestDto 'yu UserProfile microservice'inde ki dto paketi içeirsinden kopyala
 * kendi dto paketinin içerisine yapıştır.**/
@FeignClient(name="user-profile-manager"
        ,url ="${myapplication.user.feignurl}", decode404 = true)
          /**${myapplication.user.feignurl}  --> gidip yml dan çekecek**/
public interface IUserProfileManager {
    @PostMapping("/createprofile")
    ResponseEntity<Boolean> createProfile(@RequestBody @Valid CreateProfileRequestDto dto);

}
