package com.example.Sem3_CarShop.domain.BrandDomain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandRequest {

    private Long id;
    @NotBlank
    private String brandName;
}
