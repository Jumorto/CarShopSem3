package com.example.Sem3_CarShop.business.impl.StatisticsImpl;

import com.example.Sem3_CarShop.domain.StatisticsDomain.GetKilometersByBrandResponse;
import com.example.Sem3_CarShop.persistence.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetKilometersByBrandImplTest {

    @Mock
    private StatisticsRepository repository;
    @InjectMocks
    private GetKilometersByBrandImpl getKilometersByBrand;

    @Test
    void testGetKilometersByBrand() {
        long idBrand = 1;

        List<Object[]> mockResult = new ArrayList<>();
        Object[] data1 = {"0 - 100000", "brandName", 10};
        Object[] data2 = {"100001 - 150000", "brandName", 15};
        Object[] data3 = {"150001 - 200000", "brandName", 20};
        Object[] data4 = {"200000", "brandName", 25};
        mockResult.add(data1);
        mockResult.add(data2);
        mockResult.add(data3);
        mockResult.add(data4);

        when(repository.brandsByKilometers(anyLong())).thenReturn(mockResult);

        GetKilometersByBrandResponse response = getKilometersByBrand.getKilometersByBrand(idBrand);
        Object[] resultData = response.getKilometersByBrand();

        assertEquals("brandName", resultData[0]);
        assertEquals(10, resultData[1]);
        assertEquals(15, resultData[2]);
        assertEquals(20, resultData[3]);
        assertEquals(25, resultData[4]);

        verify(repository).brandsByKilometers(idBrand);
    }
}