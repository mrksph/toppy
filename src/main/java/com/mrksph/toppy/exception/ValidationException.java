package com.mrksph.toppy.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private String message;


}
