package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.persistence.GearboxRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteGearboxUseCaseImplTest {
    @Mock
    private GearboxRepository gearboxRepositoryMock;
    @InjectMocks
    private DeleteGearboxUseCaseImpl deleteGearboxUseCase;
    @Test
    void deleteGearbox() {
        long id = 42;
        deleteGearboxUseCase.deleteGearbox(id);

        verify(gearboxRepositoryMock, times(1)).deleteById(id);
    }
}