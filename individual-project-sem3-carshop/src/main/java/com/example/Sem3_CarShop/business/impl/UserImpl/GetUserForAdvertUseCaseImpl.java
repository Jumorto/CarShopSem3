package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.GetUserForAdvertUseCase;
import com.example.Sem3_CarShop.domain.UserDomain.CustomUserForAdvert;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserForAdvertUseCaseImpl implements GetUserForAdvertUseCase {

    private UserRepository repository;

    @Override
    public CustomUserForAdvert getUserForAdvertById (long idUser){
        Optional<UserEntity> entity= repository.findById(idUser);

        CustomUserForAdvert userForAdvert = CustomUserForAdvert.builder()
                .id(entity.get().getId())
                .username(entity.get().getUsername())
                .email(entity.get().getEmail())
                .phone(entity.get().getPhone()).build();


        return userForAdvert;
    }
}
