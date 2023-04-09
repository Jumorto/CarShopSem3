package com.example.Sem3_CarShop.business.UserUseCases;

import com.example.Sem3_CarShop.domain.UserDomain.CreateUserRequest;
import com.example.Sem3_CarShop.domain.UserDomain.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
