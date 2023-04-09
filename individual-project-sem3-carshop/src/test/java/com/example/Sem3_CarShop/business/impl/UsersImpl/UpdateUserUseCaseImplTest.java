package com.example.Sem3_CarShop.business.impl.UsersImpl;

import com.example.Sem3_CarShop.business.exceptions.InvalidUserException;
import com.example.Sem3_CarShop.business.exceptions.NotFoundEngineTypeException;
import com.example.Sem3_CarShop.business.impl.UserImpl.UpdateUserUseCaseImpl;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.UpdateEngineTypeRequest;
import com.example.Sem3_CarShop.domain.UserDomain.UpdateUserRequest;
import com.example.Sem3_CarShop.persistence.UserRepository;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void updateUser() {
        UserEntity testUser1 = UserEntity.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        when(userRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(testUser1));
        UpdateUserRequest request = UpdateUserRequest.builder().id(1L).username("John").description("ALABALA").phone("0987654321").photoStr("sjkajkas").build();
        when(userRepositoryMock.save(testUser1)).thenReturn(testUser1);
        updateUserUseCase.updateUser(request);
        verify(userRepositoryMock, times(1)).save(testUser1);
    }

    @Test
    public void testUpdateUser_throwsInvalidUserException_whenUserIdIsInvalid() {
        UpdateUserRequest request = UpdateUserRequest.builder().id(-1L).username("John").description("ALABALA").phone("0987654321").photoStr("sjkajkas").build();
        when(userRepositoryMock.findById(any())).thenReturn(Optional.empty());
        try{
            updateUserUseCase.updateUser(request);
            fail("Expected InvalidUserException to be thrown");
        } catch (InvalidUserException e) {
            //Expected exception, test passes.
        } catch (Exception e) {
            fail("Expected InvalidUserException but got a different exception: " + e.getClass());
        }
    }
}