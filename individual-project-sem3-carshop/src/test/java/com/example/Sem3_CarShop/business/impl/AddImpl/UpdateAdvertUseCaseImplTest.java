package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.exceptions.NotFoundAdvertisementExeption;
import com.example.Sem3_CarShop.business.exceptions.NotFoundEngineTypeException;
import com.example.Sem3_CarShop.domain.AddDomain.CreateNewAddRequest;
import com.example.Sem3_CarShop.domain.AddDomain.UpdateAdvertRequest;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.UpdateEngineTypeRequest;
import com.example.Sem3_CarShop.persistence.AddRepository;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAdvertUseCaseImplTest {
    @Mock
    private AddRepository addRepositoryMock;

    @InjectMocks
    public UpdateAdvertUseCaseImpl updateAdvertUseCase;

    @Test
    void updateAdvert() {
    AddEntity testAdd = AddEntity.builder()
            .id(null)
            .idUserCreate(1L)
            .name("TestAdd")
            .description("ALAbala")
            .vehicleType(1)
            .brand(1)
            .engine_type(1)
            .gearbox(1).num_doors(3)
            .date_manufacture(null)
            .kilometers(150L)
            .price(1000.80)
            .date_publish(null)
            .build();

        when(addRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(testAdd));
        AddEntity saveAdv = AddEntity.builder()
                .id(null)
                .idUserCreate(1L)
                .name("TestAdd")
                .description("ALAbala")
                .vehicleType(1)
                .brand(1)
                .engine_type(1)
                .gearbox(1).num_doors(3)
                .date_manufacture(null)
                .kilometers(150L)
                .price(1000.80)
                .date_publish(null)
                .build();

        UpdateAdvertRequest request = UpdateAdvertRequest.builder().id(1L)
                .name("TestAdd")
                .description("ALAbala")
                .vehicleType(1)
                .brand(1)
                .engineType(1)
                .gearbox(1)
                .numOfDoors(3)
                .dateManufacture(null)
                .kilometers(150L)
                .price(1000.80)
                .date_publish(null)
                .build();
        when(addRepositoryMock.save(saveAdv))
                .thenReturn(saveAdv);

        updateAdvertUseCase.updateAdvert(request);

        verify(addRepositoryMock, times(1)).save(saveAdv);

    }

        @Test
    public void testUpdateAdvert_throwsNotFoundAdvertisementException_whenAdvertIdIsInvalid() throws Exception {
        UpdateAdvertRequest request = UpdateAdvertRequest.builder()
                .id(-1L)
                .name("TestAdd")
                .description("ALAbala")
                .vehicleType(1)
                .brand(1)
                .engineType(1)
                .gearbox(1).numOfDoors(3)
                .dateManufacture(new Date())
                .kilometers(150L)
                .price(1000D)
                .build();


        when(addRepositoryMock.findById(any())).thenReturn(Optional.empty());
        try{
            updateAdvertUseCase.updateAdvert(request);
            fail("Expected NotFoundAdvertisementException to be thrown");
        } catch (NotFoundAdvertisementExeption e) {
            //Expected exception, test passes.
        } catch (Exception e) {
            fail("Expected NotFoundAdvertisementException but got a different exception: " + e.getClass());
        }
    }
}