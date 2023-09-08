package com.emre.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@MappedSuperclass
/**@MappedSuperclass" anotasyonu, JPA tarafından sunulan bir özelliktir ve bir sınıfın diğer sınıflar
 *  tarafından kalıtım alınabilecek bir temel sınıf olduğunu belirtmek için kullanılır.
 *  Yani, bu sınıfın tablo olarak atanmayacağı, ancak alt sınıflar tarafından kalıtım
 *  alındığında tablo olarak atanacak ortak özelliklerin ve alanların bulunduğu bir sınıfı
 *  tanımlamak için kullanılır.*/

public class BaseEntity {
    Long createdate;
    Long updatedate;
    boolean isactive;
}
