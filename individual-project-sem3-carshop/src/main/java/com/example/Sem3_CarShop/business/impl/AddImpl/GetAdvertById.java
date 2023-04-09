package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.GetAdvertByIdUseCase;
import com.example.Sem3_CarShop.business.exceptions.NotFoundAdvertisementExeption;
import com.example.Sem3_CarShop.domain.AddDomain.AdvertisementSpecial;
import com.example.Sem3_CarShop.domain.AddDomain.GetAdvertByIdResponse;
import com.example.Sem3_CarShop.persistence.AddRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAdvertById implements GetAdvertByIdUseCase {
    private AddRepository addRepository;

    @Override
    public GetAdvertByIdResponse getAdvertById(long id){
        List<Object[]> advert = addRepository.findAdvertById(id);
        List<AdvertisementSpecial> advertSpec = advert.stream().map(AdvertisementConverterSpecial::convert).toList();
        GetAdvertByIdResponse response = new GetAdvertByIdResponse();
        if(advertSpec == null || advertSpec.isEmpty()){
            throw new NotFoundAdvertisementExeption("ADVERT_ID_INVALID");
        }else{
            response.setAdvert(advertSpec.get(0));
        }
        return response;
    }
}
