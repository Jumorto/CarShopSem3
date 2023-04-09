package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.business.BrandUseCases.GetAllBrandsUseCase;
import com.example.Sem3_CarShop.domain.BrandDomain.Brand;
import com.example.Sem3_CarShop.domain.BrandDomain.GetAllBrandsResponse;
import com.example.Sem3_CarShop.persistence.BrandRepository;

import java.util.List;

import com.example.Sem3_CarShop.persistence.entity.BrandEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAllBrandsUseCaseImpl implements GetAllBrandsUseCase {
    private final BrandRepository brandRepository;

    @Override
    public GetAllBrandsResponse getAllBrands(){
        List<BrandEntity> results;

        results = brandRepository.findAll();

        final GetAllBrandsResponse response = new GetAllBrandsResponse();
        List<Brand> brands = results
                .stream()
                .map(BrandConverter::convert)
                .toList();
        response.setBrands(brands);

        return response;
    }
}
