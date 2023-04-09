package com.example.Sem3_CarShop.business.impl.StatisticsImpl;

import com.example.Sem3_CarShop.business.StatisticsUseCases.GetKilometersByBrandUseCase;
import com.example.Sem3_CarShop.domain.StatisticsDomain.GetKilometersByBrandResponse;
import com.example.Sem3_CarShop.persistence.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetKilometersByBrandImpl implements GetKilometersByBrandUseCase {

    private StatisticsRepository repository;

    @Override
    public GetKilometersByBrandResponse getKilometersByBrand(long idBrand){
        List<Object[]> result = repository.brandsByKilometers(idBrand);
        Object[] resultData = new Object[5];
        Object[] tmp = result.get(0);
            resultData[0] = (String)tmp[1];
            resultData[1] = 0;
            resultData[2] = 0;
            resultData[3] = 0;
            resultData[4] = 0;

        for (Object[] obj: result) {
            if(((String)obj[0]).equals("0 - 100000")){
                resultData[1] = obj[2];
            } else if (((String)obj[0]).equals("100001 - 150000")) {
                resultData[2] = obj[2];
            }else if (((String)obj[0]).equals("150001 - 200000")) {
                resultData[3] = obj[2];
            }else if (((String)obj[0]).equals("200000")) {
                resultData[4] = obj[2];
            }
        }

        return GetKilometersByBrandResponse.builder().kilometersByBrand(resultData).build();
    }
}
