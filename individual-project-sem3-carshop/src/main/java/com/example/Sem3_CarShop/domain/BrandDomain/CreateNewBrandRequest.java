package com.example.Sem3_CarShop.domain.BrandDomain;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewBrandRequest {
    @NotBlank
    private String brandName;
}
