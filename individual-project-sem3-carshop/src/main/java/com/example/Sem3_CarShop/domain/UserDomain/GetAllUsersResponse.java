package com.example.Sem3_CarShop.domain.UserDomain;

import lombok.*;

import java.util.List;

@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse {
    private List<User> users;
}
