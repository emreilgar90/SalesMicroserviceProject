package com.emre.config.security;


import com.emre.repository.entity.Auth;
import com.emre.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {
    @Autowired
    AuthService authService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserByAuthId(Long authid){  //18.01-->1:53
        /**authid'nin olup olmadığını sorgulamak için authService'e erişmek gerekiyordu. Bunun
         * için yukarıda @Autowired kullandık. Belki enjekte ederekte olabilir denemedim !!!
         * Optional Auth döndüyor*/
        Optional<Auth> auth = authService.findById(authid);
        if(auth.isEmpty()) return null;//KULLANICI YOKSA NULL DÖN
        List<GrantedAuthority> authorites = new ArrayList<>();//.authorities() içerisine List aldığı için oluşturduk
        /**Burada belirtilen isimlendirmeler tamamen size aittir. Özel bir kullanım değildir !
         * Yetki Adı: Yönetici, Asistan */
        authorites.add(new SimpleGrantedAuthority("USER"));
        authorites.add(new SimpleGrantedAuthority("EDITOR"));
        authorites.add(new SimpleGrantedAuthority("GEÇİYORDUM"));
        return User.builder() //KİMLİK KARTI OLUŞTURUP RETURN YAPIYORUZ !
                .username(authid.toString())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorites)//BİR KULLANICI ELİNDE Kİ HANGİ ADRESLERE(ENDPOİNTLERE) GİDEBİLİR
                .build();
    }
}
