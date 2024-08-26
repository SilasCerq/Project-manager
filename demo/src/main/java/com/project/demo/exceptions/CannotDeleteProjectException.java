package com.project.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason ="Este projeto já foi iniciado")
public class CannotDeleteProjectException extends RuntimeException{

    public CannotDeleteProjectException() {
    }
}
