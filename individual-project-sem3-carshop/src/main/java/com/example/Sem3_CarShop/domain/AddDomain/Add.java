package com.example.Sem3_CarShop.domain.AddDomain;
import lombok.*;

import java.util.Date;
@Generated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Add {
    private Long id;
    private Long idUserCreate;
    private String name;
    private String description;
    private Integer vehicleType;
    private Integer brand;
    private Integer engine_type;
    private Integer gearbox;
    private Integer num_doors;
    private Date date_manufacture;
    private Long kilometers;
    private Double price;
    private Date date_publish;
}
