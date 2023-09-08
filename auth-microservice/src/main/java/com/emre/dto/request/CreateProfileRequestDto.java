package com.emre.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateProfileRequestDto { //UserProfileMicroservice'in deki dto yu buraya kopyaladık.
    /**NotNull sayılsal ifadeler de NotBlank String*/
    @NotNull
    Long authid;
    @NotBlank
    String username;
    @NotBlank
    @Email
    String email;
    String token;

}
