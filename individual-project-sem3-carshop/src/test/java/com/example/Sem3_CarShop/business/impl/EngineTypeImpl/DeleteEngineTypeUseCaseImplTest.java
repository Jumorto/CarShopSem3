package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteEngineTypeUseCaseImplTest {

    @Mock
    private EngineTypeRepository engineTypeRepositoryMock;

    @InjectMocks
    private DeleteEngineTypeUseCaseImpl deleteEngineTypeUseCase;

    @Test
    void deleteEngineType() {
        long id = 42;
        deleteEngineTypeUseCase.deleteEngineType(id);

        verify(engineTypeRepositoryMock, times(1)).deleteById(id);
    }
}