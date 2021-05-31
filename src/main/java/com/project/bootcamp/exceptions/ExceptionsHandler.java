package com.project.bootcamp.exceptions;

/* Esta notação diz ao spring que esta classe irá ser acionada quando algum erro/exceção ocorrer
 * os tipos de tratativa é a forma que fazemos no ResponseEntityExceptionHandler
 *  */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    /*
      Recebe a classe que está propagando o erro. Está no service, então quando ocorrer a instanciação
      do objeto no service (throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS)) o spring
      irá gerenciar e tratar utilizando o método aqui. Mas porque? Porque uma exceção simplemente não
      retorna um status, por isso cria-se um Exception Handler que faz esse processo de gerenciar as
      mensagens de erro assim como os status.

      Basicamente. No service se ocorrer a chamada new BusinessException. O spring irá verificar e acionar
      o método abaixo.

    */

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handleSecurity(BusinessException e){

        /* a mensagem aqui processada utiliza a classe ExceptionResponse só para gerernciá-la
        em específico. No caso, pega-se a mensagem que vem do BusinessExcepion e instancia no corpo
        * do ReponseEntity fazendo que faz o envio correto da mensagem e status */

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ExceptionResponse(e.getMessage()));

    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleSecurity(NotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(e.getMessage()));

    }


}