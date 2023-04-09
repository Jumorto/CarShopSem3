package com.example.Sem3_CarShop.domain.UserDomain;

import lombok.*;

import java.util.List;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {
    private String subject;

    private Long userId;

    private String email;

    private String username;

    private String description;

    private String phone;

    private List<String> role;
}
