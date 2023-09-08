package com.emre.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) /**@PreAuthorize ve @PostAuthorize Anotasyonları: @PreAuthorize ve @PostAuthorize gibi metod düzeyinde güvenlik anotasyonlarını kullanmanıza olanak tanır.*/
public class AuthMicroServiceSecurityConfig {
   @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }
    /**Bu konfigrasyonu yaparak diyoruz ki default ayarları ez benim kofigre ettiğim gibi çalış*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        /***csrf kapatma ve açma işlemlerinde kullanılır. Sisteminizin güvende kalmasını
         * sağlamak için kullanılır
         * antMatchers-> izin verilecek ya da verilmeyecek end pointlerin belirlendiği alan
         * permitAll-> gelen tüm isteklere izin ver
         * anyRequest-> her hangi bir istek
         * authenticated-> oturum açmaya tabi tut. Kimlik doğrulama yap*/
        httpSecurity.csrf().disable();
        /**Öncelikle gelen tüm isteklere doğrulama uygulayacağımızı bildiriyoruz*/
        httpSecurity.authorizeHttpRequests()
               // .antMatchers("/v3/api-docs/**","/swagger-ui/**","/v1/dev/**").permitAll() //bunlara izin ver diyoruz
                .anyRequest().authenticated(); //yukarıdakiler hariç oturum açma kimlik doğrulamayı zorunlu kıl
        /**Bir filtre uygulanacak bunun nasıl yapılacağını ve hangi şekilde yapılacağını belirtilmeye
         * ihtiyaç var.
         * 1-Filter nesnesi
         * 2-Filtre tipi/filter type class 18.01-->1:09*/
        httpSecurity
                .addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);







        /**
         *Gelen isteklere oturum açma zorunluluğu getirildikten sonra bir Login page'e
         * yönlendirme yapmak gereklidir. Bunun aktif edilmesi aşağıda ki gibi olur.
         * Ayrıca kendinize ait bir Login Page kullanmak isterseniz. Bu sayfanın uzantısını buraya
         * girmeniz gereklidir.
         * */
        //httpSecurity.formLogin();
        return httpSecurity.build();

    }
}
