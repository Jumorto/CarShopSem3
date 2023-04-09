package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.business.BrandUseCases.DeleteBrandUseCase;
import com.example.Sem3_CarShop.persistence.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteBrandUseCaseImpl implements DeleteBrandUseCase {
    private final BrandRepository brandRepository;

    @Override
    public void deleteBrand(long brandId) {
        this.brandRepository.deleteById(brandId);
    }
}
