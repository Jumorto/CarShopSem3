package com.example.Sem3_CarShop.business.impl.StatisticsImpl;

import com.example.Sem3_CarShop.domain.StatisticsDomain.GetAdvertsByKilometersResponse;
import com.example.Sem3_CarShop.persistence.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetChartAdvertsByKilometersImplTest {

    @Mock
    private StatisticsRepository repository;
    @InjectMocks
    private GetChartAdvertsByKilometersImpl getChartAdvertsByKilometers;

    @Test
    public void testGetChartAdvertsByKilometers() {
        List<Object[]> adverts = new ArrayList<>();
        adverts.add(new Object[0]);
        adverts.add(new Object[0]);
        adverts.add(new Object[0]);
        adverts.add(new Object[0]);


        when(repository.advertsByKilometers()).thenReturn(adverts);

        GetAdvertsByKilometersResponse response = getChartAdvertsByKilometers.getChartAdvertsByKilometers();
        assertEquals(adverts, response.getAdvertsByKilometers());
        verify(repository).advertsByKilometers();
    }
}