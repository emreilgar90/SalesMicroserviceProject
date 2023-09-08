package com.emre.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  /**Uygulama ayağa kalkarken configrationlar varsa bu anatasyonla biliyor*/
public class RabbitConfig {
    /**Oluşturacağımız kuyruk sistemi için temel olan parametrelerin isimlendirilmesini yapıyoruz**/
    private String exchangeAuth = "exchange-auth";
    private String keyAuth = "key-auth";
    private String queueAuth = "queue-auth-create-user";  //auth'un kuyruğu fonsiyonu user oluşturmak


    /**Burada ki directExchange diyelim ki auth oluşturuldu ve bu dinleyen replika birden çok userprofileservice
     * olsun sadece bir tanesinin yakalaması ve UserProfile oluşturması yeterli. Bundan dolayı DirectExchange */
    /**1-Exchange Nesnesi-> Direct,Fanout,Topic
     * 2-Queue Nesnesi -> kuyruk
     * DİKKAT !! yazılan metotlardan spring'in nesne oluşturması için @Bean anatasyonunu eklemek gerekiyor*/


    /**Aşağıda bean ile exchange oluşturduk, türü DirectExchange olan 17.01-->42:00*/
    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(exchangeAuth);
    }

    /**Aynı şekilde queue nesnesini de bean ile nesnesini oluşturduk. 17.01-->42:00*/
    @Bean
    Queue queueCreateUser(){
        return new Queue(queueAuth);
    }

    /**Oluşturduğumuz iki nesneyi (Exchange,Queue) bir birine belli bir key üzerinden
     * bağlamak için Binding nesnesi yaratıyoruz. Bu işlem rabbitMQ üzerinden bir kuyruk oluşturacaktır.
     * 17.01->51:00  (binding=bağlayıcı) */
    @Bean
    public Binding bindingCreateUser(final Queue queueCreateUser,final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueCreateUser).to(exchangeAuth).with(keyAuth);

    }

}
