package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.business.EngineTypeUseCases.UpdateEngineTypeUseCase;
import com.example.Sem3_CarShop.business.exceptions.NotFoundEngineTypeException;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.UpdateEngineTypeRequest;
import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateEngineTypeUseCaseImpl implements UpdateEngineTypeUseCase {
    private final EngineTypeRepository engineTypeRepository;

    @Override
    public void updateEngineType(UpdateEngineTypeRequest request){
        Optional<EngineTypeEntity> engineOptional = engineTypeRepository.findById(request.getId());
        EngineTypeEntity engine;
                if(engineOptional.isPresent()){
                    engine = engineOptional.get();
                }else{
                    throw new NotFoundEngineTypeException("ENGINETYPE_ID_INVALID");
                }
                if(!request.getEngineType().isEmpty() && request.getEngineType() != null){
                    engine.setEngineType(request.getEngineType());
                }
        engineTypeRepository.save(engine);
    }
}
