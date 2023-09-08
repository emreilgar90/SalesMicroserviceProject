package com.emre.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/**
 * DİKKAT!!!
 * 1- bu sınıf mutlaka serileştirilmelidir.
 * 2- mutlaka bu sınıfın paket adı ve tanımlamalarını kaşılayan consumer
 * aynı yapılandırmayı kullanmalıdır.
 */
public class CreateUser implements Serializable {
    Long authid;
    String username;
    String email;
}

