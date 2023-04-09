package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.persistence.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepositoryMock;

    @InjectMocks
    private DeleteBrandUseCaseImpl deleteBrandUseCase;

    @Test
    void deleteBrand() {
        long id = 42;
        deleteBrandUseCase.deleteBrand(id);

        verify(brandRepositoryMock, times(1)).deleteById(id);
    }
}