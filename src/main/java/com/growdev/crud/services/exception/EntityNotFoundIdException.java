package com.growdev.crud.services.exception;

public class EntityNotFoundIdException extends RuntimeException {
    public EntityNotFoundIdException(String msg) {
        super(msg);
    }
}
