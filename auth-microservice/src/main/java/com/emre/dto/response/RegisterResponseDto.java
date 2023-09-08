package com.emre.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterResponseDto {
    //sistemin anlamı cevap vermesi için yazıyoruz.

    Long authid;
    String content;//içerik, başarı ile giriş yaptınız veya girmiş olduğunuz şifreler uyuşmamaktadır gibi
    String email;
    /**
     * 100-kayıt başarılı
     * 200-kayıt beklemede
     * 300-hata
     * 400-parametre hataları**/
    Integer registerstate;
}
