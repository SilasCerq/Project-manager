package com.project.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason ="Esta pessoa não é funcionario")
public class AcessDeniedException extends RuntimeException{

    public AcessDeniedException() {
    }
}
