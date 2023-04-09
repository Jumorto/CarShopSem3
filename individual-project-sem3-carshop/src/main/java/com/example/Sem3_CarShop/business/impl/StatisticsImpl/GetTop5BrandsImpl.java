package com.example.Sem3_CarShop.business.impl.StatisticsImpl;

import com.example.Sem3_CarShop.business.StatisticsUseCases.GetTop5BrandsUseCase;
import com.example.Sem3_CarShop.domain.StatisticsDomain.GetTop5BrandsResponse;
import com.example.Sem3_CarShop.persistence.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTop5BrandsImpl implements GetTop5BrandsUseCase {

    private StatisticsRepository repository;

    @Override
    public GetTop5BrandsResponse getTop5Brands(){
        return GetTop5BrandsResponse.builder().top5Brands(repository.getTop5Brands()).build();
    }
}
