package com.emre.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginRequestDto {
    @NotBlank(message = "Kullanıcı adı boş geçilemez")
    @Size(min=3,max=32)
    String username;
    @NotBlank
    @Size(min=8,max=64)
    String password;

}
