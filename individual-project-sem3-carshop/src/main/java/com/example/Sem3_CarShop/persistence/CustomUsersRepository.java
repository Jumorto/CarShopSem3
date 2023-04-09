package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomUsersRepository extends JpaRepository<UserEntity, Long>  {
    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT id, type, name, email, password, description, phone, user_photo FROM users WHERE id = ?1", nativeQuery = true)
    Optional<UserEntity> findById(long userId);

    @Query(value = "SELECT id, type, name, email, password, description, phone, user_photo FROM users WHERE type = ?1", nativeQuery = true)
    List<UserEntity> findAllByType(Integer type);
}
