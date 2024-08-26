package com.project.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason ="Esta pessoa não existe")
public class PessoaNotFoundException extends RuntimeException{
    public PessoaNotFoundException() {

    }
}
