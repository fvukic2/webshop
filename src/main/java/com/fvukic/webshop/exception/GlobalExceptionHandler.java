package com.fvukic.webshop.exception;

import com.fvukic.webshop.domain.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityWithIdNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityWithIdNotFound(EntityWithIdNotFoundException ex) {
        return handleException(BAD_REQUEST,ex);
    }

    @ExceptionHandler(WrongNewEntityId.class)
    public ResponseEntity<ExceptionResponse> handleEntityWithNewIdNotFound(WrongNewEntityId ex) {
        return handleException(BAD_REQUEST,ex);
    }

    private ResponseEntity<ExceptionResponse> handleException(HttpStatus httpStatus, Exception exception){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        ExceptionResponse exc = new ExceptionResponse();

        exc.setStatus(httpStatus.value());
        exc.setMessage(exception.getMessage());
        exc.setTimeStamp(timeStamp);

        return ResponseEntity.status(httpStatus).body(exc);
    }

}
