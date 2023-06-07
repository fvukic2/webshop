package com.fvukic.webshop.util;

import com.fvukic.webshop.exception.BadRequestException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class Helper {

    public static void validateRequest(BindingResult result) {
        if (result.hasFieldErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            throw new BadRequestException(ErrorResponse.ERROR_MISSING_REQUEST_PARAMETERS + errorsToString(errors));
        }
    }

    private static String errorsToString(List<FieldError> errors) {
        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (FieldError error : errors) {
            sb.append(delim).append(error.getField()).append(" ").append(error.getDefaultMessage());
            delim = ", ";
        }
        return " (" + sb + ")";
    }

}
