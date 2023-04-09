package com.example.Sem3_CarShop.business.impl.UsersImpl;

import com.example.Sem3_CarShop.business.impl.UserImpl.GetUserUseCaseImpl;
import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.CustomUsersRepository;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {

    @Mock
    private CustomUsersRepository customUsersRepositoryMock;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void getExistingUser() {
        UserEntity testUser = UserEntity.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        Optional<User> expected = Optional.of(User.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build());
        when(customUsersRepositoryMock.findById(1L))
                .thenReturn(Optional.of(testUser));

        Optional<User> actual = getUserUseCase.getUser(1L);
        assertEquals(expected.hashCode(), actual.hashCode());
        verify(customUsersRepositoryMock).findById(1L);
    }
}