package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.DeleteUserUseCase;
import com.example.Sem3_CarShop.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase{

    private final UserRepository userRepository;

    @Override
    public void deleteUser(long userId) {
        this.userRepository.deleteById(userId);
    }
}
