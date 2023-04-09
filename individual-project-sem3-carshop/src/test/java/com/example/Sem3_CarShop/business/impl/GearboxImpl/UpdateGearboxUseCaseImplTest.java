package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.business.exceptions.NotFoundBrandException;
import com.example.Sem3_CarShop.business.exceptions.NotFoundGearboxException;
import com.example.Sem3_CarShop.domain.BrandDomain.UpdateBrandRequest;
import com.example.Sem3_CarShop.domain.GearboxDomain.UpdateGearboxRequest;
import com.example.Sem3_CarShop.persistence.GearboxRepository;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateGearboxUseCaseImplTest {
    @Mock
    private GearboxRepository gearboxRepositoryMock;
    @InjectMocks
    private UpdateGearboxUseCaseImpl gearboxUseCase;

    @Test
    void updateGearbox() {
        GearboxEntity testGearbox1 = GearboxEntity.builder().id(1L).gearboxType("Manual").build();
        when(gearboxRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(testGearbox1));
        UpdateGearboxRequest request = UpdateGearboxRequest.builder().id(1L).gearboxType("Manual").build();
        when(gearboxRepositoryMock.save(testGearbox1)).thenReturn(testGearbox1);
        gearboxUseCase.updateGearbox(request);
        verify(gearboxRepositoryMock, times(1)).save(testGearbox1);
    }

    @Test
    public void testUpdateGearbox_throwsNotFoundGearboxException_whenGearboxIdIsInvalid() {
        UpdateGearboxRequest request = new UpdateGearboxRequest();
        request.setId(-1l);
        request.setGearboxType("Test Gearbox type");
        when(gearboxRepositoryMock.findById(any())).thenReturn(Optional.empty());
        try{
            gearboxUseCase.updateGearbox(request);
            fail("Expected NotFoundGearboxException to be thrown");
        } catch (NotFoundGearboxException e) {
            //Expected exception, test passes.
        } catch (Exception e) {
            fail("Expected NotFoundGearboxException but got a different exception: " + e.getClass());
        }
    }
}