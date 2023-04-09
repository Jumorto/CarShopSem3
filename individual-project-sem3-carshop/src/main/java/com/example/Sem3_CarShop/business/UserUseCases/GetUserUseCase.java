package com.example.Sem3_CarShop.business.UserUseCases;

import com.example.Sem3_CarShop.domain.UserDomain.User;
import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long userId);
}
