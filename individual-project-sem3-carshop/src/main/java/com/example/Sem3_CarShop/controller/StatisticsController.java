package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.business.StatisticsUseCases.GetChartAdvertsByKilometersUseCase;
import com.example.Sem3_CarShop.business.StatisticsUseCases.GetKilometersByBrandUseCase;
import com.example.Sem3_CarShop.business.StatisticsUseCases.GetTop5BrandsUseCase;
import com.example.Sem3_CarShop.configuration.security.isauthenticated.IsAuthenticated;
import com.example.Sem3_CarShop.domain.StatisticsDomain.GetAdvertsByKilometersResponse;
import com.example.Sem3_CarShop.domain.StatisticsDomain.GetKilometersByBrandResponse;
import com.example.Sem3_CarShop.domain.StatisticsDomain.GetTop5BrandsResponse;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Generated
@IsAuthenticated
@RolesAllowed({"ROLE_admin"})
@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class StatisticsController {

    private final GetTop5BrandsUseCase getTop5Brands;

    private final GetChartAdvertsByKilometersUseCase getChartAdvertsByKilometers;

    private final GetKilometersByBrandUseCase getKilometersByBrand;

    @GetMapping("/brands")
    public ResponseEntity<GetTop5BrandsResponse> getTop5Brands(){
        return ResponseEntity.ok(GetTop5BrandsResponse.builder().top5Brands(getTop5Brands.getTop5Brands().getTop5Brands()).build());
    }

    @GetMapping("/advertKilometers")
    public ResponseEntity<GetAdvertsByKilometersResponse> getChartAdvertsByKilometers(){
        return ResponseEntity.ok(GetAdvertsByKilometersResponse.builder().advertsByKilometers(getChartAdvertsByKilometers.getChartAdvertsByKilometers().getAdvertsByKilometers()).build());
    }

    @GetMapping("/kilometersByBrand/{id}")
    public ResponseEntity<GetKilometersByBrandResponse> getKilometersByBrand(@PathVariable("id") long id){
        return ResponseEntity.ok(GetKilometersByBrandResponse.builder().kilometersByBrand(getKilometersByBrand.getKilometersByBrand(id).getKilometersByBrand()).build());
    }
}
