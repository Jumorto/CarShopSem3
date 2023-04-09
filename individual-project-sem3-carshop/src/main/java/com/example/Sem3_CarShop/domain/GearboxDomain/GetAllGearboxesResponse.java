package com.example.Sem3_CarShop.domain.GearboxDomain;
import lombok.*;

import java.util.List;

@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGearboxesResponse {
    List<Gearbox> gearboxes;
}
