package com.project.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason ="Este projeto não existe")
public class ProjetoNotFoundException extends RuntimeException{

    public ProjetoNotFoundException(){

    }

}
