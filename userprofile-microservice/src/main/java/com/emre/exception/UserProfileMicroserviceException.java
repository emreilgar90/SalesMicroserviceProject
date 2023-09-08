package com.emre.exception;

import lombok.Getter;

@Getter
public class UserProfileMicroserviceException extends RuntimeException{
    private final ErrorType errorType;

    /**constructor da AuthmicroserviceExcepiton'ı ister errorType dan direkt getMessage ile super kullanarak
     * alabiliriz.*/
    public UserProfileMicroserviceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }
    /**Veyahut Message'ı constructor oluştururken doğrudan verebiliriz.**/
    public UserProfileMicroserviceException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
