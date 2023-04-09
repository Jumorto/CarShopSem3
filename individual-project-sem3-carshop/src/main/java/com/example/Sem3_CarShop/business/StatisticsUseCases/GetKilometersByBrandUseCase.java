package com.example.Sem3_CarShop.business.StatisticsUseCases;

import com.example.Sem3_CarShop.domain.StatisticsDomain.GetKilometersByBrandResponse;

public interface GetKilometersByBrandUseCase {
    GetKilometersByBrandResponse getKilometersByBrand(long idBrand);
}
