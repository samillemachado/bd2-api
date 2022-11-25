package com.growdev.crud.services.exception;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException (String msg){
        super(msg);
    }
}
