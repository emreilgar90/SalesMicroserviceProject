package com.emre.exception;

import lombok.Getter;

@Getter
public class SalesMicroserviceException extends RuntimeException{
    private final ErrorType errorType;

    /**constructor da AuthmicroserviceExcepiton'ı ister errorType dan direkt getMessage ile super kullanarak
     * alabiliriz.*/
    public SalesMicroserviceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }
    /**Veyahut Message'ı constructor oluştururken doğrudan verebiliriz.**/
    public SalesMicroserviceException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
