package com.fvukic.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityWithIdNotFoundException extends RuntimeException{
    public EntityWithIdNotFoundException(String message,Integer id) {
        super(message + id);
    }
}
