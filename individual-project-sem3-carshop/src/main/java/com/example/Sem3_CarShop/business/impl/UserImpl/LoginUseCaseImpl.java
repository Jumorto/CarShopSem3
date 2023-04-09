package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.AccessTokenEncoder;
import com.example.Sem3_CarShop.business.UserUseCases.LoginUseCase;
import com.example.Sem3_CarShop.business.exceptions.InvalidCredentialsException;
import com.example.Sem3_CarShop.domain.UserDomain.AccessToken;
import com.example.Sem3_CarShop.domain.UserDomain.LoginRequest;
import com.example.Sem3_CarShop.domain.UserDomain.LoginResponse;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long userId = user != null ? user.getId() : null;

//        String photoEncodeToString = null;
//        if(user.getPhoto() != null){
//             photoEncodeToString = Base64.getEncoder().encodeToString(user.getPhoto());
//        }
        List<String> role = new ArrayList<>();
        if(user.getType() == 1){
            String adminR = "admin";
            role.add(adminR);
        }else{
            String normalR = "normal";
            role.add(normalR);
        }
        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .email(user.getEmail())
                        .userId(userId)
                        .username(user.getUsername())
                        .description(user.getDescription())
                        .phone(user.getPhone())
                        //.photo(photoEncodeToString)
                        .role(role)
                        .build());
    }


}
