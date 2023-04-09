package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;

public class UserConverter {

    private UserConverter(){

    }

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .type(user.getType())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .description(user.getDescription())
                .phone(user.getPhone())
                .build();
    }
}
