package com.example.demo.exception;
import lombok.Getter;

@Getter
public class ManagerException extends RuntimeException{
    private final ErrorType errorType;

    public ManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}