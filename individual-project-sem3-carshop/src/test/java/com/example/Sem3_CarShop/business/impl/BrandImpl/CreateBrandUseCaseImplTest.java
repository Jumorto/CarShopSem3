package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandRequest;
import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandResponse;
import com.example.Sem3_CarShop.persistence.BrandRepository;
import com.example.Sem3_CarShop.persistence.entity.BrandEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepositoryMock;

    @InjectMocks
    private CreateBrandUseCaseImpl createNewBrandUseCase;

    @Test
    void createNewBrand() {
        BrandEntity testBrand = BrandEntity.builder().id(null).brandName("Ferrari").build();
        BrandEntity testBrand1 = BrandEntity.builder().id(1L).brandName("Ferrari").build();
        when(brandRepositoryMock.save(testBrand))/*.thenThrow(new RuntimeException())*/
                .thenReturn(testBrand1);

        CreateNewBrandRequest request = CreateNewBrandRequest.builder().brandName("Ferrari").build();
        CreateNewBrandResponse actualResponse = createNewBrandUseCase.createNewBrand(request);

        assertEquals(testBrand1.getId(), actualResponse.getBrandId());
        verify(brandRepositoryMock).save(testBrand);
    }
}