package br.com.alagouai.srv.controle.alagamentos.adapter.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static br.com.alagouai.srv.controle.alagamentos.core.common.constant.Constants.ERROR_DATABASE;
import static br.com.alagouai.srv.controle.alagamentos.core.common.constant.Constants.ERROR_INTEGRATION;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataBaseException.class)
    public String handleDataBaseException(DataBaseException e) {
        log.error("Erro ao acessar o banco de dados: {}", e.getMessage());
        return ERROR_DATABASE;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IntegrationException.class)
    public String handleIntegrationException(IntegrationException e) {
        log.error("Erro ao acessar serviço externo: {}", e.getMessage());
        return ERROR_INTEGRATION;
    }


}
