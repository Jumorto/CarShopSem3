package com.example.Sem3_CarShop.business.exceptions;

import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Generated
public class EmailTakenException extends ResponseStatusException {
    public EmailTakenException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_TAKEN");
    }
}
