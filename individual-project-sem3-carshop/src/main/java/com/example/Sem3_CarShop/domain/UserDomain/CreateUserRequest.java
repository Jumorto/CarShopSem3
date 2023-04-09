package com.example.Sem3_CarShop.domain.UserDomain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotNull
    private Integer type;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String description;

    private String phone;

    private String photoStr;

    private byte[] photo;
}
