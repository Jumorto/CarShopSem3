package com.example.Sem3_CarShop.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Generated
@Entity
@Table(name = "vehicle_type")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "vehicle_type")
    private String vehicleType;
}
