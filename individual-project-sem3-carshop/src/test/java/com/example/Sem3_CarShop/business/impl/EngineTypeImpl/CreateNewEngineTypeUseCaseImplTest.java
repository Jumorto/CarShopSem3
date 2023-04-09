package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;


import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeRequest;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeResponse;
import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CreateNewEngineTypeUseCaseImplTest {

    @Mock
    private EngineTypeRepository engineTypeRepositoryMock;

    @InjectMocks
    private CreateNewEngineTypeUseCaseImpl createNewEngineTypeUseCase;

    @Test
    void createNewEngineType() {
        EngineTypeEntity testEng = EngineTypeEntity.builder().id(null).engineType("Diesel").build();
        EngineTypeEntity testEng1 = EngineTypeEntity.builder().id(1L).engineType("Diesel").build();
        when(engineTypeRepositoryMock.save(testEng))/*.thenThrow(new RuntimeException())*/
                .thenReturn(testEng1);

        CreateNewEngineTypeRequest request = CreateNewEngineTypeRequest.builder().engineType("Diesel").build();
        CreateNewEngineTypeResponse actualResponse = createNewEngineTypeUseCase.createNewEngineType(request);

        assertEquals(testEng1.getId(), actualResponse.getEngineTypeId());
        verify(engineTypeRepositoryMock).save(testEng);
    }
}