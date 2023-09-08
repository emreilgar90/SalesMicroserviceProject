package com.emre.graphql.model;

import lombok.Data;

@Data
public class UserProfileInput {
    Long authid;
    String username;
    String profileimage;

}
