package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.business.exceptions.NotFoundBrandException;
import com.example.Sem3_CarShop.business.exceptions.NotFoundEngineTypeException;
import com.example.Sem3_CarShop.domain.BrandDomain.UpdateBrandRequest;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.UpdateEngineTypeRequest;
import com.example.Sem3_CarShop.persistence.BrandRepository;
import com.example.Sem3_CarShop.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private UpdateBrandUseCaseImpl brandUseCase;

    @Test
    void updateBrand() {
        BrandEntity testBrand1 = BrandEntity.builder().id(1L).brandName("Ferrari").build();
        when(brandRepository.findById(1L))
                .thenReturn(Optional.ofNullable(testBrand1));
        UpdateBrandRequest request = UpdateBrandRequest.builder().id(1L).brandName("Ferrari").build();
        when(brandRepository.save(testBrand1)).thenReturn(testBrand1);
        brandUseCase.updateBrand(request);
        verify(brandRepository, times(1)).save(testBrand1);
    }

    @Test
    public void testUpdateBrand_throwsNotFoundBrandException_whenBrandIdIsInvalid() {
        UpdateBrandRequest request = new UpdateBrandRequest();
        request.setId(-1l);
        request.setBrandName("Test Brand Name");
        when(brandRepository.findById(any())).thenReturn(Optional.empty());
        try{
            brandUseCase.updateBrand(request);
            fail("Expected NotFoundBrandException to be thrown");
        } catch (NotFoundBrandException e) {
            //Expected exception, test passes.
        } catch (Exception e) {
            fail("Expected NotFoundBrandException but got a different exception: " + e.getClass());
        }
    }
}