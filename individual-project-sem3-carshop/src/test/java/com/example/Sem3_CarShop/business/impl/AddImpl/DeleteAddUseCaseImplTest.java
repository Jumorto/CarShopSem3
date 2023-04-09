package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.persistence.AddRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteAddUseCaseImplTest {

    @Mock
    private AddRepository addRepositoryMock;

    @InjectMocks
    private DeleteAddUseCaseImpl deleteAddUseCase;

    @Test
    void deleteAdd() {
        long id = 42;
        deleteAddUseCase.deleteAdd(id);

        verify(addRepositoryMock, times(1)).deleteById(id);
    }
}