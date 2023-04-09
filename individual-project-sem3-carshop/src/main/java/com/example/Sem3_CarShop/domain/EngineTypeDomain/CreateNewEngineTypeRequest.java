package com.example.Sem3_CarShop.domain.EngineTypeDomain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewEngineTypeRequest {
    @NotBlank
    private String engineType;
}
