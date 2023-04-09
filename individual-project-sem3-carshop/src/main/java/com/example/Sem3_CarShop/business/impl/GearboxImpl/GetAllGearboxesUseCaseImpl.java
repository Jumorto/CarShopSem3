package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.business.GearboxUseCases.GetAllGearboxesUseCase;
import com.example.Sem3_CarShop.domain.GearboxDomain.Gearbox;
import com.example.Sem3_CarShop.domain.GearboxDomain.GetAllGearboxesResponse;
import com.example.Sem3_CarShop.persistence.GearboxRepository;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllGearboxesUseCaseImpl implements GetAllGearboxesUseCase {

    private final GearboxRepository gearboxRepository;

    @Override
    public GetAllGearboxesResponse getAllGearboxes(){
        List<GearboxEntity> results;

        results = gearboxRepository.findAll();

        final GetAllGearboxesResponse response = new GetAllGearboxesResponse();
        List<Gearbox> gearboxes = results
                .stream()
                .map(GearboxConverter::convert)
                .toList();
        response.setGearboxes(gearboxes);

        return response;
    }
}
