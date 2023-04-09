package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.GetUserForAdvertUseCase;
import com.example.Sem3_CarShop.domain.UserDomain.CustomUserForAdvert;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserForAdvertUseCaseImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    public GetUserForAdvertUseCaseImpl getUserForAdvertUseCase;

    @Test
    public void testGetUserForAdvertById() {
        long idUser = 1L;
        UserEntity entity = new UserEntity(idUser, 1,"testUsername", "testEmail", "password", "description", "7128231");
        when(repository.findById(idUser)).thenReturn(Optional.of(entity));

        CustomUserForAdvert userForAdvert = getUserForAdvertUseCase.getUserForAdvertById(idUser);

        assertEquals(idUser, userForAdvert.getId());
        assertEquals("testUsername", userForAdvert.getUsername());
        assertEquals("testEmail", userForAdvert.getEmail());
        assertEquals("7128231", userForAdvert.getPhone());
    }
}