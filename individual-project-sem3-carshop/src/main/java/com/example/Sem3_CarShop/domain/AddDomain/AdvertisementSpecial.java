package com.example.Sem3_CarShop.domain.AddDomain;

import lombok.*;

import java.text.DateFormat;
import java.util.Date;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementSpecial {
        private Long id;
        private Long idUserCreate;
        private String name;
        private String description;
        private String vehicleType;
        private String brand;
        private String engine_type;
        private String gearbox;
        private Integer num_doors;
        private Date date_manufacture;
        private Long kilometers;
        private Double price;
        private Date date_publish;
        private Long idVehicleType;
        private Long idBrand;
        private Long idEngineType;
        private Long idGearbox;
}
