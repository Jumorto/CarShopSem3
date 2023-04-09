package com.example.Sem3_CarShop.business.impl.StatisticsImpl;

import com.example.Sem3_CarShop.business.StatisticsUseCases.GetChartAdvertsByKilometersUseCase;
import com.example.Sem3_CarShop.domain.StatisticsDomain.GetAdvertsByKilometersResponse;
import com.example.Sem3_CarShop.persistence.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetChartAdvertsByKilometersImpl implements GetChartAdvertsByKilometersUseCase {
    private StatisticsRepository repository;

    @Override
    public GetAdvertsByKilometersResponse getChartAdvertsByKilometers(){
        return GetAdvertsByKilometersResponse.builder().advertsByKilometers(repository.advertsByKilometers()).build();
    }
}
