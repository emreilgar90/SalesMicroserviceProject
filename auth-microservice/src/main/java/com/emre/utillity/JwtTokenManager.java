package com.emre.utillity;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    //@Value("${SECRETKEY}")
    //String secretKey;
    private final String sifreAnahtari = "1234";

    /**Login olan kişinin id bilgisini kullanarak token üretmek için kullanılır.*/
    public Optional<String> createToken(Long id){
        String token;

        Long exDate=1000L*60*15; //15 DK içinde ex olacak
        try {
            /**Claim nesnesi olarak yerleştirdiğiniz bilgiler açık okunur olduğunu asla unutma !
             * kullanıcı adı, şifre sakın koyma !*/
            token= JWT.create()
                    .withClaim("id",id)  //token içerisine eklemek istediğin nesneleri yaz
                    .withClaim("howtopage","AuthMicroservice")
                    .withClaim("isOne", true)
                    .withIssuer("java")  //sahibi
                    .withIssuedAt(new Date())  //oluşturma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate))   //ne zaman geçersiz olacağı
                    .sign(Algorithm.HMAC512(sifreAnahtari)); //sign ile hangi algoritma ile imzalandığını belirtiyoruz
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();  //oluşturamadıysa boş dönüyor

        }

    }
    /**Kullanıcı tarafından verilen token bilgisinin doğruluğunu kontrol etmek için kullanıyoruz,
     * token imzası ve geçerliliği kontrol edilir**/
    public Boolean validateToken(String token){  /**16.01--->02.00*/
        try {
            /**Token doğrulamak için*/
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(sifreAnahtari))
                    .withIssuer("java")
                    .build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if(decodedJWT==null)
                return false;
        }catch (Exception exception){
                return false;
        }
        return true;
    }

    /**Token bilgisi gönderen kullanıcının Id bilgisi token Payload içinden alınır.**/
    public Optional<Long> getByIdFromToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(sifreAnahtari))
                    .withIssuer("java")
                    .build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if(decodedJWT==null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong()); /**16.01 -->02.03*/
        }catch (Exception exception) {
            return Optional.empty();
        }
    }

}

