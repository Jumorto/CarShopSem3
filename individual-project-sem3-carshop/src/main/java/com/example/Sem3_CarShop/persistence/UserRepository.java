package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
