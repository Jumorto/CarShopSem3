package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.CreateNewAddRequest;
import com.example.Sem3_CarShop.domain.AddDomain.CreateNewAddResponse;
import com.example.Sem3_CarShop.persistence.AddRepository;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateNewAddUseCaseImplTest {

    @Mock
    private AddRepository addRepositoryMock;

    @InjectMocks
    private CreateNewAddUseCaseImpl createNewAddUseCase;

    @Test
    void createNewAdd() {

            AddEntity testAdd1 = AddEntity.builder()
                    .id(1L)
                    .idUserCreate(1L)
                    .name("TestAdd")
                    .description("ALAbala")
                    .vehicleType(1)
                    .brand(1)
                    .engine_type(1)
                    .gearbox(1).num_doors(3)
                    .date_manufacture(null)
                    .kilometers(150L)
                    .price(1000D)
                    .date_publish(Date.from(Instant.now()))
                    .build();

            when(addRepositoryMock.save(any(AddEntity.class))).thenReturn(testAdd1);

            CreateNewAddRequest request = CreateNewAddRequest.builder()
                    .idUserCreate(1L)
                    .name("TestAdd")
                    .description("ALAbala")
                    .vehicleType(1)
                    .brand(1)
                    .engineType(1)
                    .gearbox(1).numOfDoors(3)
                    .dateManufacture(null)
                    .kilometers(150L)
                    .price(1000D)
                    .build();

            CreateNewAddResponse actualResponse = createNewAddUseCase.createNewAdd(request);

            assertEquals(1L, actualResponse.getAddId());
            verify(addRepositoryMock).save(any(AddEntity.class));
    }
}