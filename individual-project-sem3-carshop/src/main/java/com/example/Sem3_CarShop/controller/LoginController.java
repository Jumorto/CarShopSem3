package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.business.UserUseCases.LoginUseCase;
import com.example.Sem3_CarShop.domain.UserDomain.LoginRequest;
import com.example.Sem3_CarShop.domain.UserDomain.LoginResponse;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Generated
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
