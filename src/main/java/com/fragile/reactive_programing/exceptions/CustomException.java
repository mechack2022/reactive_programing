package com.fragile.reactive_programing.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    public CustomException(String message){
       super(message);
//        this.message = message;

    }
}
