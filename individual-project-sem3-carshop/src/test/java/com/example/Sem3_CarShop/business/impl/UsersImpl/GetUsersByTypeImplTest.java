package com.example.Sem3_CarShop.business.impl.UsersImpl;

import com.example.Sem3_CarShop.business.impl.UserImpl.GetUsersByTypeImpl;
import com.example.Sem3_CarShop.domain.UserDomain.GetAllUsersResponse;
import com.example.Sem3_CarShop.domain.UserDomain.GetUsersByTypeRequest;
import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.CustomUsersRepository;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUsersByTypeImplTest {

    @Mock
    private CustomUsersRepository customUsersRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUsersByTypeImpl getUsersByType;

    @Test
    void getUsersByType() {
        UserEntity testUser1En = UserEntity.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        UserEntity testUser2En = UserEntity.builder().id(2L).username("John2").type(1).email("john2@doe.com").password("jhidhjkas2").build();
        UserEntity testUser3En = UserEntity.builder().id(3L).username("John3").type(1).email("john3@doe.com").password("jhidhjkas3").build();
        when(customUsersRepository.findAllByType(1))
                .thenReturn(List.of(testUser1En, testUser2En, testUser3En));

        GetUsersByTypeRequest request = GetUsersByTypeRequest.builder().type(1).build();

        GetAllUsersResponse actual = getUsersByType.getUsersByType(request);

        User testUser1 = User.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        User testUser2 = User.builder().id(2L).username("John2").type(1).email("john2@doe.com").password("jhidhjkas2").build();
        User testUser3 = User.builder().id(3L).username("John3").type(1).email("john3@doe.com").password("jhidhjkas3").build();
        GetAllUsersResponse expected = GetAllUsersResponse
                .builder()
                .users(List.of(testUser1, testUser2, testUser3))
                .build();

        assertEquals(expected, actual);
        verify(customUsersRepository).findAllByType(1);
    }

    @Test
    void getAllUsersIfTypeIsNull() {
        UserEntity testUser1En = UserEntity.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        UserEntity testUser2En = UserEntity.builder().id(2L).username("John2").type(1).email("john2@doe.com").password("jhidhjkas2").build();
        UserEntity testUser3En = UserEntity.builder().id(3L).username("John3").type(1).email("john3@doe.com").password("jhidhjkas3").build();
        when(userRepository.findAll())
                .thenReturn(List.of(testUser1En, testUser2En, testUser3En));

        GetUsersByTypeRequest request = GetUsersByTypeRequest.builder().type(null).build();

        GetAllUsersResponse actual = getUsersByType.getUsersByType(request);

        User testUser1 = User.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        User testUser2 = User.builder().id(2L).username("John2").type(1).email("john2@doe.com").password("jhidhjkas2").build();
        User testUser3 = User.builder().id(3L).username("John3").type(1).email("john3@doe.com").password("jhidhjkas3").build();
        GetAllUsersResponse expected = GetAllUsersResponse
                .builder()
                .users(List.of(testUser1, testUser2, testUser3))
                .build();

        assertEquals(expected, actual);
        verify(userRepository).findAll();
    }
}