package com.example.Sem3_CarShop.domain.AddDomain;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewAddRequest {

    @NotNull
    private Long idUserCreate;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Integer vehicleType;

    @NotNull
    private Integer brand;

    @NotNull
    private Integer engineType;

    @NotNull
    private Integer gearbox;

    @NotNull
    private Integer numOfDoors;

    private Date dateManufacture;

    private Long kilometers;

    private Double price;
}
