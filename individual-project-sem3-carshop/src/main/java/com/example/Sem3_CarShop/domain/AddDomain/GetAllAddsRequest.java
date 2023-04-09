package com.example.Sem3_CarShop.domain.AddDomain;

import lombok.*;

import java.util.Date;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAddsRequest {
    private Long idUserCreate;
    private String description;
    private Integer idVehicleType;
    private Integer idBrand;
    private Integer idEngineType;
    private Integer idGearbox;
    private Integer num_doors;
    private Date date_manufacture;
    private Long kilometers;
    private Double price;
    private Date date_publish;
    private Double priceMin;
    private Double priceMax;
}
