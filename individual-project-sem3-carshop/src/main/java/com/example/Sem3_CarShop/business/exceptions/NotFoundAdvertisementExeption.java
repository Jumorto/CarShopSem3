package com.example.Sem3_CarShop.business.exceptions;

import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Generated
public class NotFoundAdvertisementExeption extends ResponseStatusException {
    public NotFoundAdvertisementExeption(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

}
