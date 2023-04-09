package com.example.Sem3_CarShop.business.impl.UsersImpl;

import com.example.Sem3_CarShop.business.exceptions.EmailTakenException;
import com.example.Sem3_CarShop.business.exceptions.InvalidUserException;
import com.example.Sem3_CarShop.business.impl.UserImpl.CreateUserUseCaseImpl;
import com.example.Sem3_CarShop.domain.UserDomain.UpdateUserRequest;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import com.example.Sem3_CarShop.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import com.example.Sem3_CarShop.domain.UserDomain.CreateUserResponse;
import com.example.Sem3_CarShop.domain.UserDomain.CreateUserRequest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;
    @Mock
    private PasswordEncoder encoder;

    @Test
    void createUserShouldCreateNewUser() {
        UserEntity testUser = UserEntity.builder().id(null).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        UserEntity testUser1 = UserEntity.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        when(encoder.encode("jhidhjkas"))
                .thenReturn("jhidhjkas");
        when(userRepositoryMock.save(testUser))
                .thenReturn(testUser1);

        CreateUserRequest request = CreateUserRequest.builder().username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        CreateUserResponse actualResponse = createUserUseCase.createUser(request);

        assertEquals(testUser1.getId(), actualResponse.getUserId());
        verify(userRepositoryMock).save(testUser);
    }

    @Test
    public void testUpdateUser_throwsEmailTakenException_whenEmailIsInvalid() {
        UserEntity testUser1 = UserEntity.builder().id(1L).username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        CreateUserRequest request = CreateUserRequest.builder().username("John").type(1).email("john@doe.com").password("jhidhjkas").build();
        when(userRepositoryMock.findByEmail(any())).thenReturn(testUser1);
        try{
            createUserUseCase.createUser(request);
            fail("Expected EmailTakenException to be thrown");
        } catch (EmailTakenException e) {
            //Expected exception, test passes.
        } catch (Exception e) {
            fail("Expected EmailTakenException but got a different exception: " + e.getClass());
        }
    }
}