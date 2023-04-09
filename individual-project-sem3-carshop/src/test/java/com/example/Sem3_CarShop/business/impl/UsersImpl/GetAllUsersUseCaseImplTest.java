package com.example.Sem3_CarShop.business.impl.UsersImpl;

import com.example.Sem3_CarShop.business.impl.UserImpl.GetAllUsersUseCaseImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Sem3_CarShop.domain.UserDomain.GetAllUsersResponse;
import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;

import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllUsersUseCaseImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetAllUsersUseCaseImpl getAllUsersUseCase;

    @Test
    void getAllExistingUsers() {
        UserEntity testUser1En = UserEntity.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        UserEntity testUser2En = UserEntity.builder().id(2L).username("John2").type(1).email("john2@doe.com").password("jhidhjkas2").build();
        UserEntity testUser3En = UserEntity.builder().id(3L).username("John3").type(1).email("john3@doe.com").password("jhidhjkas3").build();
        when(userRepositoryMock.findAll())
                .thenReturn(List.of(testUser1En, testUser2En, testUser3En));

        GetAllUsersResponse actual = getAllUsersUseCase.getUsers();

        User testUser1 = User.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        User testUser2 = User.builder().id(2L).username("John2").type(1).email("john2@doe.com").password("jhidhjkas2").build();
        User testUser3 = User.builder().id(3L).username("John3").type(1).email("john3@doe.com").password("jhidhjkas3").build();
        GetAllUsersResponse expected = GetAllUsersResponse
                .builder()
                .users(List.of(testUser1, testUser2, testUser3))
                .build();

        assertEquals(expected, actual);
        verify(userRepositoryMock).findAll();
    }
}