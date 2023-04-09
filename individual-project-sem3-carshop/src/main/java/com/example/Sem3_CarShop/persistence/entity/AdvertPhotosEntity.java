package com.example.Sem3_CarShop.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Generated
@Entity
@Table(name = "advertisement_photos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertPhotosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_advert")
    private Long id_advert;

    @Column(name = "photo")
    private byte[] photo;
}
