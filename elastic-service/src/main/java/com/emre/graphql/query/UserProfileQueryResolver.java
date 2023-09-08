package com.emre.graphql.query;

import com.emre.repository.entity.UserProfile;
import com.emre.service.UserProfileService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileQueryResolver implements GraphQLQueryResolver {

    private final UserProfileService userProfileService;

    public Iterable<UserProfile> findall(){
        return userProfileService.findAll();
    }

    public UserProfile findByUsername(String username){
        return userProfileService.findByUsername(username);
    }
}
