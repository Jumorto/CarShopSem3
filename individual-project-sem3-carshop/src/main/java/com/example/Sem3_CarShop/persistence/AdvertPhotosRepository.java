package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.AdvertPhotosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertPhotosRepository extends JpaRepository<AdvertPhotosEntity, Long> {
    @Query("SELECT advPE.id, advPE.id_advert, advPE.photo FROM AdvertPhotosEntity advPE where advPE.id_advert = :idAdvert")
    List<Object[]> findAllById_advert(@Param("idAdvert") Long idAdvert);

    @Modifying
    @Query("DELETE FROM AdvertPhotosEntity WHERE id_advert = :idAdvert")
    void deleteAllById_advert(@Param("idAdvert") Long idAdvert);
}
