package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.DeleteAllPhotosByAdvertIdUseCase;
import com.example.Sem3_CarShop.persistence.AdvertPhotosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteAllPhotosByAdvertIdImpl implements DeleteAllPhotosByAdvertIdUseCase {

    private final AdvertPhotosRepository repository;

    @Transactional
    @Override
    public void deletePhotosByAdvertId(Long id){
        repository.deleteAllById_advert(id);
    }

}
