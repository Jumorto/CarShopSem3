package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.DeleteAddUseCase;
import com.example.Sem3_CarShop.persistence.AddRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAddUseCaseImpl implements DeleteAddUseCase {
    private final AddRepository addRepository;

    @Override
    public void deleteAdd(long addId) {
        this.addRepository.deleteById(addId);
    }
}
