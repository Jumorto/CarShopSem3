package com.example.Sem3_CarShop.persistence.entity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Generated
@Entity
@Table(name = "engine")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(max = 45)
    @Column(name = "engine_type")
    private String engineType;
}
