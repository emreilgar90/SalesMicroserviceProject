package com.emre.repository;

import com.emre.repository.entity.UserProfile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository                                                                /**Id artık String*/
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {

     /**ilk sorguyu user profile den ne istenir diye düşününce bulabilirsin, authid si verilen
      * kişinin profile bilgilerini getirmek.
      * bunu yaparkende Optional sorguluyoruz ki yoksa hata dönmesin.
      * başka sorgularda yazabilirsin mailine göre,telefonuna göre vb.*/
     Optional<UserProfile> findOptionalByAuthid(Long authid);




}
