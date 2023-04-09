package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.AccessTokenEncoder;
import com.example.Sem3_CarShop.business.exceptions.InvalidCredentialsException;
import com.example.Sem3_CarShop.domain.UserDomain.AccessToken;
import com.example.Sem3_CarShop.domain.UserDomain.LoginRequest;
import com.example.Sem3_CarShop.domain.UserDomain.LoginResponse;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    public void testLoginSuccessNormal() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("test@example.com")
                .password("encodedpassword")
                .username("testuser")
                .description("Test Description")
                .phone("123-456-7890")
                .type(0)
                .build();

        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@example.com")
                .password("rawpassword")
                .build();

        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(any(AccessToken.class))).thenReturn("accesstoken");

        LoginResponse loginResponse = loginUseCase.login(loginRequest);

        assertNotNull(loginResponse);
        assertNotNull(loginResponse.getAccessToken());
    }

    @Test
    public void testLoginSuccessAdmin() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("test@example.com")
                .password("encodedpassword")
                .username("testuser")
                .description("Test Description")
                .phone("123-456-7890")
                .type(1)
                .build();

        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@example.com")
                .password("rawpassword")
                .build();

        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(any(AccessToken.class))).thenReturn("accesstoken");

        LoginResponse loginResponse = loginUseCase.login(loginRequest);

        assertNotNull(loginResponse);
        assertNotNull(loginResponse.getAccessToken());
    }

    @Test
    public void testLoginUserNotFound() {
        try{
            LoginRequest loginRequest = LoginRequest.builder()
                    .email("test@example.com")
                    .password("rawpassword")
                    .build();

            when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(null);
            loginUseCase.login(loginRequest);
        }catch (InvalidCredentialsException ex){
            //The exception was thrown as expected
        }

    }

    @Test
    public void testLoginUserWrongCredentials() {
        try{
            UserEntity user = UserEntity.builder()
                    .id(1L)
                    .email("test@example.com")
                    .password("encodedpassword")
                    .username("testuser")
                    .description("Test Description")
                    .phone("123-456-7890")
                    .type(0)
                    .build();

            LoginRequest loginRequest = LoginRequest.builder()
                    .email("test@example.com")
                    .password("rawpassword")
                    .build();

            when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(user);
            when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(false);
            loginUseCase.login(loginRequest);
        }catch (InvalidCredentialsException ex){
            //The exception was thrown as expected
        }

    }
}