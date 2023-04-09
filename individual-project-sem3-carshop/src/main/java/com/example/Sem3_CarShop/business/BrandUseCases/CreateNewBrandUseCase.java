package com.example.Sem3_CarShop.business.BrandUseCases;

import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandRequest;
import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandResponse;

public interface CreateNewBrandUseCase {
    CreateNewBrandResponse createNewBrand(CreateNewBrandRequest request);
}
