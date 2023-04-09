package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.UpdateUserUseCase;
import com.example.Sem3_CarShop.business.exceptions.InvalidUserException;
import com.example.Sem3_CarShop.domain.UserDomain.UpdateUserRequest;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateUser(UpdateUserRequest request){
     Optional<UserEntity> userOptional = userRepository.findById(request.getId());
     if (userOptional.isEmpty()) {
         throw new InvalidUserException("USER_ID_INVALID");
     }
     UserEntity user = userOptional.get();

//     String encodedPassword = passwordEncoder.encode(request.getPassword());

        if(request.getUsername() != null && !request.getUsername().isEmpty()){
            user.setUsername(request.getUsername());
        }
        if(request.getDescription() != null && !request.getDescription().isEmpty()){
            user.setDescription(request.getDescription());
        }
        if(request.getPhone() != null && !request.getPhone().isEmpty()){
            user.setPhone(request.getPhone());
        }
//        if(request.getPhoto() != null && request.getPhoto().length > 0){
//            user.setPhoto(request.getPhoto());
//        }

//     user.setPassword(encodedPassword);

     userRepository.save(user);
    }
}
