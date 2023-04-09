package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.domain.GearboxDomain.Gearbox;
import com.example.Sem3_CarShop.domain.GearboxDomain.GetAllGearboxesResponse;
import com.example.Sem3_CarShop.persistence.GearboxRepository;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllGearboxesUseCaseImplTest {

    @Mock
    private GearboxRepository gearboxRepositoryMock;
    @InjectMocks
    private GetAllGearboxesUseCaseImpl getAllGearboxesUseCase;
    @Test
    void getAllGearboxes() {
        GearboxEntity testGearbox1 = GearboxEntity.builder().id(1L).gearboxType("Manual").build();
        GearboxEntity testGearbox2 = GearboxEntity.builder().id(2L).gearboxType("Automatic").build();
        when(gearboxRepositoryMock.findAll())
                .thenReturn(List.of(testGearbox1, testGearbox2));

        GetAllGearboxesResponse actual = getAllGearboxesUseCase.getAllGearboxes();

        Gearbox testGbx1 = Gearbox.builder().id(1L).gearboxType("Manual").build();
        Gearbox testGbx2 = Gearbox.builder().id(2L).gearboxType("Automatic").build();
        GetAllGearboxesResponse expected = GetAllGearboxesResponse.builder().gearboxes(List.of(testGbx1, testGbx2)).build();

        assertEquals(expected, actual);
        verify(gearboxRepositoryMock).findAll();
    }
}