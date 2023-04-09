package com.example.Sem3_CarShop.business.UserUseCases;

import com.example.Sem3_CarShop.domain.UserDomain.GetAllUsersResponse;
import com.example.Sem3_CarShop.domain.UserDomain.GetUsersByTypeRequest;

public interface GetUsersByTypeUseCase {
    GetAllUsersResponse getUsersByType(GetUsersByTypeRequest request);
}
