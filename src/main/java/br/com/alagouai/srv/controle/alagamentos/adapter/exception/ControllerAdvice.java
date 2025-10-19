package br.com.alagouai.srv.controle.alagamentos.adapter.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @Data
    @Builder
    static class ErrorResponse {
        private String code;
        private String message;
        private Map<String, Object> details;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fe -> fe.getField(),
                        fe -> fe.getDefaultMessage(),
                        (a, b) -> a,
                        HashMap::new
                ));

        log.warn("Validation error: {}", details);
        return ErrorResponse.builder()
                .code(BAD_REQUEST.name())
                .message("Parâmetros inválidos no corpo da requisição")
                .details(details)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, Object> details = new HashMap<>();
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            details.put(v.getPropertyPath().toString(), v.getMessage());
        }
        log.warn("Constraint violation: {}", details);
        return ErrorResponse.builder()
                .code(BAD_REQUEST.name())
                .message("Parâmetros de requisição inválidos")
                .details(details)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleNotReadable(HttpMessageNotReadableException ex) {
        log.warn("JSON parse error: {}", ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage());
        return ErrorResponse.builder()
                .code(BAD_REQUEST.name())
                .message("Payload inválido ou malformado")
                .details(Map.of("hint", "Verifique o JSON e os tipos dos campos"))
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse handleMissingParam(MissingServletRequestParameterException ex) {
        log.warn("Missing request param: {}", ex.getParameterName());
        return ErrorResponse.builder()
                .code(BAD_REQUEST.name())
                .message("Parâmetro obrigatório ausente")
                .details(Map.of("param", ex.getParameterName()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.warn("Type mismatch: param={}, value={}, requiredType={}",
                ex.getName(), ex.getValue(), ex.getRequiredType());
        return ErrorResponse.builder()
                .code(BAD_REQUEST.name())
                .message("Tipo inválido para parâmetro")
                .details(Map.of(
                        "param", ex.getName(),
                        "value", String.valueOf(ex.getValue()),
                        "requiredType", ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido"
                ))
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataBaseException.class)
    public ErrorResponse handleDataBaseException(DataBaseException e) {
        log.error("Erro ao acessar o banco de dados: {}", e.getMessage(), e);
        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.name())
                .message("Falha ao acessar o banco de dados")
                .details(Map.of("reason", e.getMessage()))
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IntegrationException.class)
    public ErrorResponse handleIntegrationException(IntegrationException e) {
        log.error("Erro ao acessar serviço externo: {}", e.getMessage(), e);
        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.name())
                .message("Falha ao acessar serviço externo")
                .details(Map.of("reason", e.getMessage()))
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneric(Exception e) {
        log.error("Erro inesperado: {}", e.getMessage(), e);
        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.name())
                .message("Erro interno não mapeado")
                .details(Map.of("reason", e.getClass().getSimpleName()))
                .build();
    }
}
