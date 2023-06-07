package com.fvukic.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongNewEntityId extends RuntimeException {

    public WrongNewEntityId(String message) {
        super(message);
    }
}
