package com.example.Sem3_CarShop.persistence.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Generated
@Entity
@Table(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "type")
    private Integer type;

    @NotBlank
    @Length(max = 45)
    @Column(name = "name")
    private String username;

    @NotBlank
    @Length(max = 45)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Length(max = 255)
    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String description;

    @Column(name = "phone")
    private String phone;
}
