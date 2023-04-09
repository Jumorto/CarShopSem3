package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.domain.BrandDomain.Brand;
import com.example.Sem3_CarShop.domain.BrandDomain.GetAllBrandsResponse;
import com.example.Sem3_CarShop.persistence.BrandRepository;
import com.example.Sem3_CarShop.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllBrandsUseCaseImplTest {

    @Mock
    private BrandRepository brandRepositoryMock;

    @InjectMocks
    private GetAllBrandsUseCaseImpl getAllBrandsUseCase;

    @Test
    void getAllBrands() {
        BrandEntity testBrand1En = BrandEntity.builder().id(1L).brandName("Ferrari").build();
        BrandEntity testBrand2En = BrandEntity.builder().id(2L).brandName("Opel").build();
        BrandEntity testBrand3En = BrandEntity.builder().id(3L).brandName("Toyota").build();
        when(brandRepositoryMock.findAll())
                .thenReturn(List.of(testBrand1En, testBrand2En, testBrand3En));

        GetAllBrandsResponse actual = getAllBrandsUseCase.getAllBrands();

        Brand testBrand1 = Brand.builder().id(1L).brandName("Ferrari").build();
        Brand testBrand2 = Brand.builder().id(2L).brandName("Opel").build();
        Brand testBrand3 = Brand.builder().id(3L).brandName("Toyota").build();
        GetAllBrandsResponse expected = GetAllBrandsResponse.builder().brands(List.of(testBrand1, testBrand2, testBrand3)).build();

        assertEquals(expected, actual);
        verify(brandRepositoryMock).findAll();

    }
}