package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.CreateUserUseCase;
import com.example.Sem3_CarShop.business.exceptions.EmailTakenException;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.domain.UserDomain.CreateUserRequest;
import com.example.Sem3_CarShop.domain.UserDomain.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        CreateUserResponse response;

            if(checkIfEmailTaken(request.getEmail())){
                throw new EmailTakenException();
            }else{
                UserEntity savedUser = saveNewUser(request);

                response =  CreateUserResponse.builder().userId(savedUser.getId()).build();
            }

            return response;
    }

    private Boolean checkIfEmailTaken(String email){
        Boolean exists = false;
        if(userRepository.findByEmail(email) != null){
            exists = true;
        }
        return exists;
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUserEntity = UserEntity.builder()
                .username(request.getUsername())
                .type(request.getType())
                .email(request.getEmail())
                .password(encodedPassword)
                .description(request.getDescription())
                .phone(request.getPhone())
                .build();
        UserEntity savedUser = userRepository.save(newUserEntity);
        return savedUser;
    }
}
