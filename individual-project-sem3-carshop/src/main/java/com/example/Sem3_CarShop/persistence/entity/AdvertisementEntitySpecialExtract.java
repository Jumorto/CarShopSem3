package com.example.Sem3_CarShop.persistence.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Generated
@Entity
@Table(name = "advertisement")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementEntitySpecialExtract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "user_create")
    private Long idUserCreate;

    @NotBlank
    @Length(max = 45)
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "add_photos")
    private byte[] add_photos; //за снимките???????????????

    @NotNull
    @Column(name = "vehicle_type")
    private String vehicleType;

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "engine_type")
    private String engine_type;

    @NotNull
    @Column(name = "gearbox")
    private String gearbox;

    @NotNull
    @Column(name = "num_doors")
    private Integer num_doors;

    @Column(name = "date_manufacture")
    private Date date_manufacture;

    @Column(name = "kilometers")
    private Long kilometers;

    @Column(name = "price")
    private Double price;

    @Column(name = "date_publish")
    private Date date_publish;

}
