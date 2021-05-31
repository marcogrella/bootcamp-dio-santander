package com.project.bootcamp.exceptions;

/* essa classe se comporta como uma RuntimeException e em recebe uma String */

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }


}
