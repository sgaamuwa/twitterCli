package com.gaamuwa.twitter.exceptions;

public class InvalidInputException extends RuntimeException {

    private String message;

    public InvalidInputException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
