package com.emre.service;

import com.emre.dto.request.DoLoginRequestDto;
import com.emre.dto.request.RegisterRequestDto;
import com.emre.dto.response.RegisterResponseDto;
import com.emre.exception.AuthMicroserviceException;
import com.emre.exception.ErrorType;
import com.emre.manager.IUserProfileManager;
import com.emre.mapper.IAuthMapper;
import com.emre.rabbitmq.model.CreateUser;
import com.emre.rabbitmq.producer.CreateUserProducer;
import com.emre.repository.IAuthRepository;
import com.emre.repository.entity.Auth;
import com.emre.utillity.JwtTokenManager;
import com.emre.utillity.ServiceManager;
;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final IUserProfileManager iUserProfileManager; /**enjekte ettik feignclient kurduktan sonra*/
    private final JwtTokenManager jwtTokenManager;

    private final CreateUserProducer createUserProducer;

    public AuthService(IAuthRepository repository, IUserProfileManager iUserProfileManager, JwtTokenManager jwtTokenManager, CreateUserProducer createUserProducer) {
        super(repository);
        this.repository = repository;
        this.iUserProfileManager = iUserProfileManager;
        this.jwtTokenManager = jwtTokenManager;
        this.createUserProducer = createUserProducer;
    }

    public RegisterResponseDto save(RegisterRequestDto dto){ //03.01.2022  39:22
        if(!dto.getPassword().equals(dto.getRepassword())) //gelen veri ile eşleşmiyorsa false
            throw  new AuthMicroserviceException(ErrorType.REGISTER_REPASSWORD_ERROR);
         /**Burada elle dönüşüm yapmak lazım mapper kullanmak daha mantıklıdır.*/
        /**save(Auth.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build()
        );**/
        /**auth 'a kaydetmeden önce IAuthRepository'e optional olarak kullanıcı adının olup olmadığını sorgulayan
         * bir sorgu yaptık, eğer varsa bir hata fırlatmamız gerekecek. Ve kayıtı gerçekleştirmememiz gerek**/
        if(repository.findOptionalByUsername(dto.getUsername()).isPresent())//isPresent varsa demek !
            throw new AuthMicroserviceException(ErrorType.REGISTER_KULLANICI_ADI_KAYITLI);
        /**yoksa zaten yukarıda ki bloğa girmiyor ve kayıt oluyor.*/
        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterRequestDto(dto));
        //String token= tokenGenerator.createToken(auth.getId());

        /**Auth da register işlemi yaparken feignClient 'ını yaptığımız  private final IUserProfileManager iUserProfileManager;
         * 'ı enjetekte ettikten sonra userProfileManager sayesinde User oluşturuyoruz. ! 04.01.20223 -3:13:16
         * Sonrasında RabbitMQ öğrenip burayı aşağıda ki gibi gücelledik.*/

        /**Yukarı da auth kaydı,yapıldı
         UserProfili oluşturması gerekiyor,diyelim ki tam o sıra UserProfileService çöktü. İşte bunu
         düşünürek tam olarak burası RabbitMQ'ya devredilmeli ki auth oluşturulduğun da UserProfili oluşturma garantisi
         verilmiş olsun. Ayağa kalktığı an oluşturacak*/

        /**iUserProfileManager kısmını kapattık! neden çünkü iUserProfileManager feignclient'ın manager'ı
         * artık bu kısmı configrasyonunu yaptığımız rabbitmq'ya devrediyoruz.*/
       // iUserProfileManager.createProfile(CreateProfileRequestDto.builder()
        //       .token("")
        //       .authid(auth.getId())
        //       .email(auth.getEmail())
        //       .build());
        createUserProducer.converdAndSendMessageCreateUser(CreateUser.builder()
                .authid(auth.getId())
                .username(auth.getUsername())
                .email(auth.getEmail())
                .build()); /***artık user oluşturma mesajı rabbitMQ ile gidiyor !!! yukarıyı iptal ettik feignclient'ı*/
        RegisterResponseDto result = IAuthMapper.INSTANCE.fromAuth(auth);
        result.setRegisterstate(100);
        result.setContent(auth.getEmail()+" ile başarı şekilde kayıt oldunuz.");
        return  result;


        /**Yukarı da elle yaptığımız işlemi kendi otomatik yaptı.*/

    }
    public String doLogin(DoLoginRequestDto dto){
        /**veritabına kullanıcı adı şifreyi sorguladık (optional olarak) */
        Optional<Auth > auth= repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty())
            throw new AuthMicroserviceException(ErrorType.LOGIN_ERROR);
        /**Login olan kişiler için özel bir token üretmek mantıklıdır.*/
         Optional<String> token = jwtTokenManager.createToken(auth.get().getId()); //ıd yi verdim generator token üretti.
        if(token.isEmpty())
            throw new AuthMicroserviceException(ErrorType.JWT_TOKEN_CREATE_ERROR);
        return token.get();
    }

}
