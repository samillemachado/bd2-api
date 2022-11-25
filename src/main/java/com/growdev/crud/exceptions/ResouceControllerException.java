package com.growdev.crud.exceptions;

import com.growdev.crud.services.exception.BadRequestException;
import com.growdev.crud.services.exception.DatabaseException;
import com.growdev.crud.services.exception.EntityNotFoundIdException;
import com.growdev.crud.services.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResouceControllerException {

    @ExceptionHandler(EntityNotFoundIdException.class)
    public ResponseEntity<StandardError> entityNotFoundIdException(EntityNotFoundIdException e, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("Recurso não encontrado");
        return ResponseEntity.status(statusError).body(error);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequestException(BadRequestException e, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("Requisição inválida");
        return ResponseEntity.status(statusError).body(error);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<StandardError> internalServerErrorException(InternalServerErrorException e, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.INTERNAL_SERVER_ERROR;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("Erro no servidor");
        return ResponseEntity.status(statusError).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(InternalServerErrorException e, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("Database exception");
        return ResponseEntity.status(statusError).body(error);
    }
}
