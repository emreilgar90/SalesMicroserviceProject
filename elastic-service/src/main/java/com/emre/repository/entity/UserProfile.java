package com.emre.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(indexName="userprofile")
public class UserProfile {
    /**Elasticsearch bir db gibi davranacağından içinde tuttuğu verilere id vermesi olasıdır.
     * Ayrıca indeksleme gibi işlemler içinde gereklidir.
     * Bu nedenle isteğe bağlı olarak önbellek aldığınız db'nin id bilgisini kayıt edebileceğiniz gibi
     * bu işlemi elasticsearch de yapabilir.
     *
     * Bu tanımlamayla bir entity oluşturuyoruz gelen veriyi alıp ön belleğine atacak.*/
    @Id
    String id;
    String userid;
    Long authid; //token kontrolü için
    String username;
    String profileimg;

}
