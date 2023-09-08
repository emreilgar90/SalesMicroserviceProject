package com.emre.rabbitmq.producer;

import com.emre.rabbitmq.model.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    /**RabbitMQ üzerinden bir mesaj göndermek için özel bir template e ihtiyacımız var*/
    private final RabbitTemplate rabbitTemplate;

    /**Eğer bir mesaj gönderen method yazacaksanız mutlaka void olmalı*/


    /**CreateUser karşı tarafa göndermek için yarattığımız model, 17.01->1:04*/
    public void converdAndSendMessageCreateUser(CreateUser createUser){
        rabbitTemplate.convertAndSend("exchange-auth", "key-auth","queue-auth-create-user");

    }

}
