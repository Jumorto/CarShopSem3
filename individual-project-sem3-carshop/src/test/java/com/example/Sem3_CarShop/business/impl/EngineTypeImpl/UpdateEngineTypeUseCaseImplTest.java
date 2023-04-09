package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;
import com.example.Sem3_CarShop.business.exceptions.NotFoundEngineTypeException;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.UpdateEngineTypeRequest;
import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateEngineTypeUseCaseImplTest {
    @Mock
    private EngineTypeRepository engineTypeRepositoryMock;

    @InjectMocks
    private UpdateEngineTypeUseCaseImpl engineTypeUseCase;
    @Test
    void updateEngineType_Successful() {
        EngineTypeEntity testEng = EngineTypeEntity.builder().id(1L).engineType("Diesel").build();
        when(engineTypeRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(testEng));
        UpdateEngineTypeRequest request = UpdateEngineTypeRequest.builder().build().builder().id(1L).engineType("Diesel").build();
        when(engineTypeRepositoryMock.save(testEng)).thenReturn(testEng);
        engineTypeUseCase.updateEngineType(request);
        verify(engineTypeRepositoryMock, times(1)).save(testEng);
    }

    @Test
    public void testUpdateEngineType_throwsNotFoundEngineTypeException_whenEngineTypeIdIsInvalid() {
        UpdateEngineTypeRequest request = new UpdateEngineTypeRequest();
        request.setId(-1l);
        request.setEngineType("Test Engine Type");
        when(engineTypeRepositoryMock.findById(any())).thenReturn(Optional.empty());
        try{
            engineTypeUseCase.updateEngineType(request);
            fail("Expected NotFoundEngineTypeException to be thrown");
        } catch (NotFoundEngineTypeException e) {
            //Expected exception, test passes.
        } catch (Exception e) {
            fail("Expected NotFoundEngineTypeException but got a different exception: " + e.getClass());
        }
    }
}