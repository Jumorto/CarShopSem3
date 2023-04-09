package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.domain.EngineTypeDomain.EngineType;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.GetAllEngineTypesResponse;
import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllEngineTypesUseCaseImplTest {
    @Mock
    private EngineTypeRepository engineTypeRepositoryMock;
    @InjectMocks
    private GetAllEngineTypesUseCaseImpl getAllEngineTypesUseCase;
    @Test
    void getAllEngineTypes() {
        EngineTypeEntity testEng1 = EngineTypeEntity.builder().id(1L).engineType("Diesel").build();
        EngineTypeEntity testEng2 = EngineTypeEntity.builder().id(2L).engineType("Gas").build();
        EngineTypeEntity testEng3 = EngineTypeEntity.builder().id(3L).engineType("Electric").build();

        when(engineTypeRepositoryMock.findAll())
                .thenReturn(List.of(testEng1, testEng2, testEng3));

        GetAllEngineTypesResponse actual = getAllEngineTypesUseCase.getAllEngineTypes();

        EngineType testEngT1 = EngineType.builder().id(1L).engineType("Diesel").build();
        EngineType testEngT2 = EngineType.builder().id(2L).engineType("Gas").build();
        EngineType testEngT3 = EngineType.builder().id(3L).engineType("Electric").build();
        GetAllEngineTypesResponse expected = GetAllEngineTypesResponse.builder().engineTypes(List.of(testEngT1, testEngT2, testEngT3)).build();

        assertEquals(expected, actual);
        verify(engineTypeRepositoryMock).findAll();
    }
}