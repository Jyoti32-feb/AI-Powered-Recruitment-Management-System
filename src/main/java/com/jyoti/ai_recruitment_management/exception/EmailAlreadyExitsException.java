package com.jyoti.ai_recruitment_management.exception;

public class EmailAlreadyExitsException extends RuntimeException{
    public EmailAlreadyExitsException(String message){
        super(message);
    }
}
