package com.fragile.reactive_programing.exceptions;

public class CustomServerErrorException extends  RuntimeException {

    public CustomServerErrorException(String message){
        super(message);
    }
}
