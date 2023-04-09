package com.example.Sem3_CarShop.domain.VehicleTypeDomain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewVehicleTypeRequest {
    @NotBlank
    private String vehicleType;
}
