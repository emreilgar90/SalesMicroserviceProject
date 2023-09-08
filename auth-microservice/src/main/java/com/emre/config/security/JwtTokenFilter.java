package com.emre.config.security;

import com.emre.exception.AuthMicroserviceException;
import com.emre.exception.ErrorType;
import com.emre.utillity.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails; //KİMLİK KARTINI AUTOWİRED İLE NESNESİNİ OLUŞTURDUK
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /**Gelen isteğin içerisinde token bilgisi olup olmadığının kontrol edilmesi gerekir.
         * İlk olarak buna bakılır. Ayrıca kullanıcı önceden bir kimlik kartı almış yani kendisini
         * doğrulamışsa yine burada ek işlem yapmamak adına gelen istediği olduğu gibi iade ederiz.*/
        final String authHeaderParameters = request.getHeader("Authorization");
        //authHeaderParameters boş değilse ve içerisinde "Bearer " kelimesi varsa...
        if(authHeaderParameters != null && authHeaderParameters.startsWith("Bearer ")
                && SecurityContextHolder.getContext().getAuthentication()==null){
            /**"bearer " substring'in 7 olma sebebi 7.harften sonra token bilgisinin gelmesi*/
            String token =  authHeaderParameters.substring(7);
            /**token'ı doğrumalak gerekiyor, bunu yapan JwtTokenManager'dı ! --> BUNUN İÇİN;
             * @Autowired
             *     JwtTokenManager jwtTokenManager; ile yukarıya ekledim.
             *     sonrasında jwtTokenManager da getByIdFromToken ile token ı sorgulayıp ne döndürdüğüne
             *     baktım. Optional<Long> dönüyordu buna authid dedim.
             *     */
            Optional<Long> authid= jwtTokenManager.getByIdFromToken(token); //token doğrulamak için jwttokenmanager
            if(authid.isPresent()){
                UserDetails userDetails= jwtUserDetails.getUserByAuthId(authid.get());
                UsernamePasswordAuthenticationToken  authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities() //Authorities=Yetkiler
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                /**Spring'in anlayacağı kimlik kartı oluşturmak için UserDeatails yaratmak gerek !!!!!
                 * bunun için Secrurity pakeine gidip JwtUserDetails class'ı oluşturduk !*/
            }else{
                throw new AuthMicroserviceException(ErrorType.INVALID_JWT_TOKEN_ERROR);/**18.01->1:36 özet!!!*/
            }


        }filterChain.doFilter(request,response);

    }
}
