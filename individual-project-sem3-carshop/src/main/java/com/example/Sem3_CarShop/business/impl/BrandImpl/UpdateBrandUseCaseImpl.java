package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.business.BrandUseCases.UpdateBrandUseCase;
import com.example.Sem3_CarShop.business.exceptions.NotFoundBrandException;
import com.example.Sem3_CarShop.domain.BrandDomain.UpdateBrandRequest;
import com.example.Sem3_CarShop.persistence.BrandRepository;
import com.example.Sem3_CarShop.persistence.entity.BrandEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateBrandUseCaseImpl implements UpdateBrandUseCase {

    private final BrandRepository brandRepository;

    @Override
    public void updateBrand(UpdateBrandRequest request){
        Optional<BrandEntity> brandOptional = brandRepository.findById(request.getId());
        BrandEntity brand;
        if(brandOptional.isPresent()){
            brand = brandOptional.get();
        }else{
            throw new NotFoundBrandException("BRAND_ID_INVALID");
        }

        if (!request.getBrandName().isEmpty() && request.getBrandName() != "" && request.getBrandName() != null) {
            brand.setBrandName(request.getBrandName());
        }

        brandRepository.save(brand);
    }
}
