package com.example.Sem3_CarShop.domain.GearboxDomain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGearboxRequest {

    private Long id;
    @NotBlank
    private String gearboxType;
}
