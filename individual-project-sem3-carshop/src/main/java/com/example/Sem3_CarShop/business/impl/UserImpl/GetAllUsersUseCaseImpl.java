package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.GetAllUsersUseCase;
import com.example.Sem3_CarShop.domain.UserDomain.GetAllUsersResponse;
import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase{
    private UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers() {
        List<UserEntity> results;

            results = userRepository.findAll();


        final GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> users = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setUsers(users);

        return response;
    }
}
