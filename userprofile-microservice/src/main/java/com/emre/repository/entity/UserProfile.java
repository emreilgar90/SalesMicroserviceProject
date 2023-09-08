package com.emre.repository.entity;

import com.emre.utillity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class UserProfile extends BaseEntity {

    @Id   //Mongodb String olarak işaretlenmiş bir id'yi uudi olarak yapıyor.
    String id;  //MongoDb ye döndükten sonra Long değiş String olmalı
    /**Auth microservice de kayıt olan bir kullanıcının id bilgisini tutar.**/
    Long authid;
    String username;
    String email;
    String phone;
    String address;
    String profileimage;
    String avatar;
    String info;
    String facebook;
    String twitter;
    String instagram;


}
