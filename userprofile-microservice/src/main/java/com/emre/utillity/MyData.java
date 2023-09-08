package com.emre.utillity;

import com.emre.dto.request.UserProfileSaveRequestDto;
import com.emre.manager.IUserProfileElasticService;
import com.emre.repository.IUserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MyData {
    /**Repodan veri çekecek*/
    private final IUserProfileRepository repository;

    /**manager üzerinden Elasticservice e erişim sağlayacak*/
    private final IUserProfileElasticService manager;


    @PostConstruct
    public void init(){
        repository.findAll().forEach(u->{
            manager.save(UserProfileSaveRequestDto.builder()
                    .username(u.getUsername())
                    .authid(u.getAuthid())
                    .profileimage(u.getProfileimage())
                    .userid(u.getId())
                    .build());
        });
    }
}
