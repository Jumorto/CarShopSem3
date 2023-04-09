package com.example.Sem3_CarShop.business.impl.UsersImpl;

import com.example.Sem3_CarShop.business.impl.UserImpl.DeleteUserUseCaseImpl;
import com.example.Sem3_CarShop.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void deleteExistingUser() {
        long id = 42;
        deleteUserUseCase.deleteUser(id);

        verify(userRepositoryMock, times(1)).deleteById(id);
    }
}