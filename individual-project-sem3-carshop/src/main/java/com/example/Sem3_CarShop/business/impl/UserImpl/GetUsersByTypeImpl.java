package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.GetUsersByTypeUseCase;
import com.example.Sem3_CarShop.domain.UserDomain.GetAllUsersResponse;
import com.example.Sem3_CarShop.domain.UserDomain.GetUsersByTypeRequest;
import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.CustomUsersRepository;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersByTypeImpl implements GetUsersByTypeUseCase {
    private UserRepository userRepository;

    private CustomUsersRepository customUsersRepository;

    @Override
    public GetAllUsersResponse getUsersByType(GetUsersByTypeRequest request) {
        List<UserEntity> results;

        if (request.getType() != null) {
            results = customUsersRepository.findAllByType(request.getType());
        } else {
            results = userRepository.findAll();
        }

        final GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> users = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setUsers(users);

        return response;
    }
}


