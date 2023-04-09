package com.example.Sem3_CarShop.domain.VehicleTypeDomain;

import lombok.*;

import java.util.List;

@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllVehicleTypesResponse {
    private List<VehicleType> vehicleTypes;
}
