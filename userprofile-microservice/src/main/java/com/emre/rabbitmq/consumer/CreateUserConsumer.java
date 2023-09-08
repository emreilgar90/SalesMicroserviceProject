package com.emre.rabbitmq.consumer;

import com.emre.rabbitmq.model.CreateUser;
import com.emre.repository.entity.UserProfile;
import com.emre.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;
    /**Dinlemek istediğimiz kuyruğu Handle etmemiz gerekiyor(Listener kullanılacak).
     * Bu sayede, kuyrukta base64 formatında olan metin okunarak Deserialize edilir
     * ve CreateUser nesnesine dönüşür*/
    @RabbitListener(queues = "queue-auth-crate-user") //metot olarak şu kuruğu sürekli dinliyoruz
    public void createUserFromHandleQueue(CreateUser createUser){
        userProfileService.save(UserProfile.builder()
                .authid(createUser.getAuthid())
                .username(createUser.getUsername())
                .email(createUser.getEmail())
                .build());


    }
}
