package com.example.Sem3_CarShop.business.impl.UsersImpl;


import com.example.Sem3_CarShop.business.impl.UserImpl.UserConverter;
import com.example.Sem3_CarShop.domain.UserDomain.User;
import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {

    @Test
    void convert() {
        UserEntity userEntityMock = Mockito.mock(UserEntity.class);

        Mockito.when(userEntityMock.getId()).thenReturn(1L);
        Mockito.when(userEntityMock.getType()).thenReturn(1);
        Mockito.when(userEntityMock.getUsername()).thenReturn("Test Username");
        Mockito.when(userEntityMock.getEmail()).thenReturn("Email");
        Mockito.when(userEntityMock.getPassword()).thenReturn("Password");
        Mockito.when(userEntityMock.getDescription()).thenReturn("Description");
        Mockito.when(userEntityMock.getPhone()).thenReturn("023782781");

        User result = UserConverter.convert(userEntityMock);

        Mockito.verify(userEntityMock).getId();
        Mockito.verify(userEntityMock).getType();
        Mockito.verify(userEntityMock).getUsername();
        Mockito.verify(userEntityMock).getEmail();
        Mockito.verify(userEntityMock).getPassword();
        Mockito.verify(userEntityMock).getDescription();
        Mockito.verify(userEntityMock).getPhone();

        assertEquals(1L, result.getId());
        assertEquals(1, result.getType());
        assertEquals("Test Username", result.getUsername());
        assertEquals("Email", result.getEmail());
        assertEquals("Password", result.getPassword());
        assertEquals("Description", result.getDescription());
        assertEquals("023782781", result.getPhone());
        assertEquals(null, result.getPhoto());
    }
}