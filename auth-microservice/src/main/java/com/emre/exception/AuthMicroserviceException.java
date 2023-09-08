package com.emre.exception;

import lombok.Getter;

@Getter
public class AuthMicroserviceException extends RuntimeException{
    private final ErrorType errorType;

    /**constructor da AuthmicroserviceExcepiton'ı ister errorType dan direkt getMessage ile super kullanarak
     * alabiliriz.*/
    public AuthMicroserviceException (ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }
    /**Veyahut Message'ı constructor oluştururken doğrudan verebiliriz.**/
    public AuthMicroserviceException(ErrorType errorType,String message){
        super(message);
        this.errorType=errorType;
    }
}
