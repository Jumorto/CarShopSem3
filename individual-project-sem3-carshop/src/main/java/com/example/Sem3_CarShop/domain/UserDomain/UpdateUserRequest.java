package com.example.Sem3_CarShop.domain.UserDomain;

import lombok.*;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private Long id;
    private String username;
    private String description;
    private String phone;
    private String photoStr;
    private byte[] photo;
}
