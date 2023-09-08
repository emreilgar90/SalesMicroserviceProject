package com.emre.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(5100,"Sunucuda beklenmeyen hata oluştu",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(5200,"Geçersiz token bilgisi",HttpStatus.BAD_REQUEST),
    UNAUTHRIZED_REQUEST(5201,"Yetkisiz giriş denemesi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(4100,"Parametre eksik ya da hatalı",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4110,"Kullanıcı adı ya da şifre hatalı", HttpStatus.BAD_REQUEST),
    REGISTER_REPASSWORD_ERROR(4111,"Girilen şifreler uyuşmuyor",HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICI_ADI_KAYITLI(4112,"Kullanıcı adı kayıtlı", HttpStatus.BAD_REQUEST);



    int code;
    String message;
    HttpStatus httpStatus;
}

