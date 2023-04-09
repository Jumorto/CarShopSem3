package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.business.BrandUseCases.CreateNewBrandUseCase;
import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandRequest;
import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandResponse;
import com.example.Sem3_CarShop.persistence.BrandRepository;
import com.example.Sem3_CarShop.persistence.entity.BrandEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateBrandUseCaseImpl implements CreateNewBrandUseCase {
    private final BrandRepository brandRepository;

    @Override
    public CreateNewBrandResponse createNewBrand(CreateNewBrandRequest request) {
        BrandEntity savedBrand = saveNewBrand(request);

        return CreateNewBrandResponse.builder()
                .brandId(savedBrand.getId())
                .brandName(savedBrand.getBrandName())
                .build();
    }

    private BrandEntity saveNewBrand(CreateNewBrandRequest request) {

        BrandEntity newBrandEntity = BrandEntity.builder()
                .brandName(request.getBrandName())
                .build();
        BrandEntity savedBrandEntity = brandRepository.save(newBrandEntity);
        return savedBrandEntity;
    }
}
