package com.fvukic.webshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {

    private String message;

    private Integer status;

    private String timeStamp;
}
