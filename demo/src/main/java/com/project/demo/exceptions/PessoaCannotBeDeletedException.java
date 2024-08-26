package com.project.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason ="Esta pessoa está em um projeto")
public class PessoaCannotBeDeletedException extends RuntimeException {
    public PessoaCannotBeDeletedException() {

    }
}
