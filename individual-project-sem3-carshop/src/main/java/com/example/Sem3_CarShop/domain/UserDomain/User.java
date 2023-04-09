package com.example.Sem3_CarShop.domain.UserDomain;

import lombok.*;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private Integer type;
    private String username;
    private String email;
    private String password;
    private String description;
    private String phone;
    private byte[] photo;
}
