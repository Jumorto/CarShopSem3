package com.example.Sem3_CarShop.business.exceptions;

import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Generated
public class InvalidAccessTokenException extends ResponseStatusException {
    public InvalidAccessTokenException(String errorCause) {
        super(HttpStatus.UNAUTHORIZED, errorCause);
    }
}
