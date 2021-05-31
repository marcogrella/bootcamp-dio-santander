package com.project.bootcamp.exceptions;

public class ExceptionResponse {
    private String message;
    public ExceptionResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /* Como o construtor já faz a função de criar o atributo não é necessário o setMensagem */
}
