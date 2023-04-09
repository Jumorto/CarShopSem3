package com.example.Sem3_CarShop.business.UserUseCases;

import com.example.Sem3_CarShop.domain.UserDomain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
