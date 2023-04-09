package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.GetAllAddsUseCase;
import com.example.Sem3_CarShop.domain.AddDomain.*;
import com.example.Sem3_CarShop.persistence.AddRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllAddsUseCaseImpl implements GetAllAddsUseCase {
    private AddRepository addRepository;

    @Override
    public GetAllAdvertisementsSpecialResponse getAllAdds(GetAllAddsRequest request) {
        List<Object[]> results;
        results = addRepository.findAdvertsByGiveParameters(
                request.getIdUserCreate(),
                request.getDescription(),
                request.getIdVehicleType(),
                request.getIdBrand(),
                request.getIdEngineType(),
                request.getIdGearbox(),
                request.getNum_doors(),
                request.getDate_manufacture(),
                request.getKilometers(),
                request.getPrice(),
                request.getDate_publish(),
                request.getPriceMin(),
                request.getPriceMax());


        final GetAllAdvertisementsSpecialResponse response = new GetAllAdvertisementsSpecialResponse();
        List<AdvertisementSpecial> adds = results
                .stream()
                .map(AdvertisementConverterSpecial::convert)
                .toList();
        response.setAdds(adds);

        return response;
    }
}
