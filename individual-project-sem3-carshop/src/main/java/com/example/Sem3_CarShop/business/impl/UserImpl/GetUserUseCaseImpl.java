package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.GetUserUseCase;
import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.CustomUsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase{
    private CustomUsersRepository userRepository;

    @Override
    public Optional<User> getUser(long userId) {
        return userRepository.findById(userId).map(UserConverter::convert);
    }
}
