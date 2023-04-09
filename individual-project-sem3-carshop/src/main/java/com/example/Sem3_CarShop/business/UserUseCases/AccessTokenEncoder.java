package com.example.Sem3_CarShop.business.UserUseCases;

import com.example.Sem3_CarShop.domain.UserDomain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
