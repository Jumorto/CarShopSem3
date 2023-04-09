package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.*;
import com.example.Sem3_CarShop.persistence.AddRepository;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllAddsUseCaseImplTest {

    @Mock
    private AddRepository addRepositoryMock;

    @InjectMocks
    private GetAllAddsUseCaseImpl getAllAddsUseCase;

    @Test
    void getAllAdds() {
        Object[] testAdd1En = new Object[]{1L, 1L, "ALAbala1", "desc",
                "car", "Ferrari", "Gas", "Automatic", 3, null, 150L, 1000.80, null, 1L, 1L, 1L, 1L};
        Object[] testAdd2En = new Object[]{2L, 1L, "ALAbala2", "desc",
                "car", "Ferrari", "Gas", "Automatic", 3, null, 150L, 1000.80, null, 1L, 1L, 1L, 1L};
        Object[] testAdd3En = new Object[]{3L, 1L, "ALAbala3", "desc",
                "car", "Ferrari", "Gas", "Automatic", 3, null, 150L, 1000.80, null, 1L, 1L, 1L, 1L};


        when(addRepositoryMock.findAdvertsByGiveParameters(1L, "desc",
                1, 1, 1, 1, 3, null, 150L, 1000.80, null, 10D, 10000D))
                .thenReturn(List.of(testAdd1En, testAdd2En, testAdd3En));

        GetAllAddsRequest request = GetAllAddsRequest.builder()
                .idUserCreate(1L)
                .description("desc")
                .idVehicleType(1)
                .idBrand(1)
                .idEngineType(1)
                .idGearbox(1)
                .num_doors(3)
                .date_manufacture(null)
                .kilometers(150L)
                .price(1000.80)
                .date_publish(null)
                .priceMin(10D)
                .priceMax(10000D)
                .build();

        GetAllAdvertisementsSpecialResponse actual = getAllAddsUseCase.getAllAdds(request);

        AdvertisementSpecial testAdd1 = AdvertisementSpecial.builder()
                .id(1L)
                .idUserCreate(1L)
                .name("ALAbala1")
                .description("desc")
                .vehicleType("car")
                .brand("Ferrari")
                .engine_type("Gas")
                .gearbox("Automatic")
                .num_doors(3)
                .date_manufacture(null)
                .kilometers(150L)
                .price(1000.80)
                .date_publish(null)
                .idVehicleType(1L)
                .idBrand(1L)
                .idEngineType(1L)
                .idGearbox(1L)
                .build();

        AdvertisementSpecial testAdd2 = AdvertisementSpecial.builder()
                .id(2L)
                .idUserCreate(1L)
                .name("ALAbala2")
                .description("desc")
                .vehicleType("car")
                .brand("Ferrari")
                .engine_type("Gas")
                .gearbox("Automatic")
                .num_doors(3)
                .date_manufacture(null)
                .kilometers(150L)
                .price(1000.80)
                .date_publish(null)
                .idVehicleType(1L)
                .idBrand(1L)
                .idEngineType(1L)
                .idGearbox(1L)
                .build();

        AdvertisementSpecial testAdd3 = AdvertisementSpecial.builder()
                .id(3L)
                .idUserCreate(1L)
                .name("ALAbala3")
                .description("desc")
                .vehicleType("car")
                .brand("Ferrari")
                .engine_type("Gas")
                .gearbox("Automatic")
                .num_doors(3)
                .date_manufacture(null)
                .kilometers(150L)
                .price(1000.80)
                .date_publish(null)
                .idVehicleType(1L)
                .idBrand(1L)
                .idEngineType(1L)
                .idGearbox(1L)
                .build();

        GetAllAdvertisementsSpecialResponse expected = GetAllAdvertisementsSpecialResponse.builder().adds(List.of(testAdd1, testAdd2, testAdd3)).build();

        assertEquals(expected, actual);
        verify(addRepositoryMock).findAdvertsByGiveParameters(1L, "desc",
                1, 1, 1, 1, 3, null, 150L, 1000.80, null, 10D, 10000D);
    }
}