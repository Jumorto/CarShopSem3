package com.example.Sem3_CarShop.domain.UserDomain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Email
    @NotBlank
    String email;

    @NotBlank
    String password;
}
