package com.example.Sem3_CarShop.domain.AddDomain;

import lombok.*;

import java.util.Date;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdvertRequest {

    private Long id;

    private String name;

    private String description;

    private byte[] photos;


    private Integer vehicleType;


    private Integer brand;


    private Integer engineType;


    private Integer gearbox;


    private Integer numOfDoors;

    private Date dateManufacture;

    private Long kilometers;

    private Double price;

    private Date date_publish;
}
