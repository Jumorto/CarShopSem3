package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxRequest;
import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxResponse;
import com.example.Sem3_CarShop.persistence.GearboxRepository;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateNewGearboxUseCaseImplTest {

    @Mock
    private GearboxRepository gearboxRepositoryMock;

    @InjectMocks
    private CreateNewGearboxUseCaseImpl createNewGearboxUseCase;
    @Test
    void createNewGearbox() {
        GearboxEntity testGearbox = GearboxEntity.builder().id(null).gearboxType("Manual").build();
        GearboxEntity testGearbox1 = GearboxEntity.builder().id(1L).gearboxType("Manual").build();
        when(gearboxRepositoryMock.save(testGearbox))/*.thenThrow(new RuntimeException())*/
                .thenReturn(testGearbox1);

        CreateNewGearboxRequest request = CreateNewGearboxRequest.builder().gearboxType("Manual").build();
        CreateNewGearboxResponse actualResponse = createNewGearboxUseCase.createNewGearbox(request);

        assertEquals(testGearbox1.getId(), actualResponse.getGearboxId());
        verify(gearboxRepositoryMock).save(testGearbox);

    }
}