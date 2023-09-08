package com.emre.graphql.mutation;

import com.emre.graphql.model.UserProfileInput;
import com.emre.repository.entity.UserProfile;
import com.emre.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput input){
        userProfileService.save(UserProfile.builder()
                .username(input.getUsername())
                .authid(input.getAuthid())
                .profileimg(input.getProfileimage())
                .build());
        return true;
    }
}
