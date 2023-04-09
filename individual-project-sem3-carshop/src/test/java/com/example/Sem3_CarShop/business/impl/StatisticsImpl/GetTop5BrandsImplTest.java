package com.example.Sem3_CarShop.business.impl.StatisticsImpl;

import com.example.Sem3_CarShop.domain.StatisticsDomain.GetTop5BrandsResponse;
import com.example.Sem3_CarShop.persistence.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTop5BrandsImplTest {

    @Mock
    private StatisticsRepository repository;
    @InjectMocks
    private GetTop5BrandsImpl getTop5Brands;

    @Test
    void testGetTop5Brands() {
        Object[] data1 = {"brandName1", 10};
        Object[] data2 = {"brandName2", 15};
        Object[] data3 = {"brandName3", 20};
        Object[] data4 = {"brandName4", 42};
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(data1);
        mockResult.add(data2);
        mockResult.add(data3);
        mockResult.add(data4);
        when(repository.getTop5Brands()).thenReturn(mockResult);

        GetTop5BrandsResponse response = getTop5Brands.getTop5Brands();
        List<Object[]> top5Brands = response.getTop5Brands();

        assertEquals(mockResult, top5Brands);
        verify(repository).getTop5Brands();
    }
}