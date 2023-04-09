package com.example.Sem3_CarShop.domain.GearboxDomain;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewGearboxRequest {
    @NotBlank
    private String gearboxType;
}
