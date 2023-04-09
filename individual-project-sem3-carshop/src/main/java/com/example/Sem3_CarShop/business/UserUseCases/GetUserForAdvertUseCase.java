package com.example.Sem3_CarShop.business.UserUseCases;

import com.example.Sem3_CarShop.domain.UserDomain.CustomUserForAdvert;


public interface GetUserForAdvertUseCase {
    CustomUserForAdvert getUserForAdvertById (long idUser);
}
