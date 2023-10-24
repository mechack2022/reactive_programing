package com.fragile.reactive_programing.exceptions;

public class CustomBadRequestException extends  RuntimeException {

    public CustomBadRequestException(String message) {
        super(message);
    }
}
