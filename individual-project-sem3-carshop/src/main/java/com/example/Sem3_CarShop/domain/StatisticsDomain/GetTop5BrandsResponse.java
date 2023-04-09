package com.example.Sem3_CarShop.domain.StatisticsDomain;

import lombok.*;

import java.util.List;

@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTop5BrandsResponse {
    List<Object[]> top5Brands;
}
