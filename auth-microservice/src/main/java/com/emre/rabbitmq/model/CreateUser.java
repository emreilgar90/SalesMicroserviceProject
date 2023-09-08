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
/**DİKKAT
 * 1-Bu sınıf mutlaka serileştirilmelidir
 * 2-Mutlaka bu sınıfın paket adı ve tanımlamalarını karşılayan
 * consumer aynı yapılandırmayı kullanmalıdır
 * serializable yaptığımız zaman onu base64 formata yani text'e çevirebiliyorsunuz. Sonrasında deserialize
 * yapabilmesi için eksiksiz yazılması gerek.
 * Serializable yapıldığında komple paket adı da dahil olmak üzere serileştiriliyor.*/
public class CreateUser implements Serializable {
    Long authid;
    String username;
    String email;

}
