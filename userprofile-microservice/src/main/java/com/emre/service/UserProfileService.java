package com.emre.service;

import com.emre.dto.request.BaseRequestDto;
import com.emre.exception.ErrorType;
import com.emre.exception.UserProfileMicroserviceException;
import com.emre.repository.IUserProfileRepository;
import com.emre.repository.entity.UserProfile;
import com.emre.utillity.JwtTokenManager;
import com.emre.utillity.ServiceManager;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;
    private final JwtTokenManager jwtTokenManager;
    public UserProfileService(IUserProfileRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository= repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    /**List<UserProfile> findAll(Long userid) bu metotu neden oluşturduğumuzu pek anlamadım*/

    /**Token bilgisi olmayan;GET,PUT,POST isteğini direkt reddeceğiz, token varsa doğrulayacağız.
     * o da geçersizse invalid_token hatasını fırlatacağız.**/
    public List<UserProfile> findAll(BaseRequestDto dto){
        Optional<Long> authid= jwtTokenManager.getByIdFromToken(dto.getToken()); //gelen token'ı kontrol ediyor.
        if(authid.isEmpty())  //isEmpty ise yani sorun varsa.
            throw new UserProfileMicroserviceException(ErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile= repository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty())
            throw new UserProfileMicroserviceException(ErrorType.UNAUTHRIZED_REQUEST);
        return findAll();

    }
    @Cacheable(value = "getuppercase")
    public String getUpperCase(String name){
        try{
            Thread.sleep(3000L);
        }catch (Exception exception){

        }
        return name.toUpperCase();
    }


    @CacheEvict(value = "getuppercase", allEntries = true )
    public void clearCache(){
        System.out.println("Belleği temizledim.");
    }
}
