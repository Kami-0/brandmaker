package ru.kami.brandmaker.core.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kami.brandmaker.core.api.common.exceptions.EntityNotFoundException;
import ru.kami.brandmaker.core.api.common.exceptions.InvalidTicketRequestException;
import ru.kami.brandmaker.core.api.common.types.EntityType;

import javax.validation.ConstraintViolationException;

/**
 * @author Daniil.Makarov
 */
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * При некорректном запросе
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Некорректный запрос", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    /**
     * При неправильном json формате
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Некорректный JSON запрос", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * При невалидном id
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ExceptionResponseEntity> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ExceptionResponseEntity(HttpStatus.NOT_FOUND.value(),
                        ex.getEntityType(),
                        ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    /**
     * При невалидной попытке забронировать место
     */
    @ExceptionHandler(InvalidTicketRequestException.class)
    protected ResponseEntity<ExceptionResponse> handleNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    /**
     * При невалидных параметрах запроса
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ExceptionResponse> handlePathVariableException(ConstraintViolationException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * При невалидности переменой в пути запроса
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> handleIncorrectPath(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Некорректный " + ex.getParameter().getParameterName(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ExceptionResponseEntity {
        private int status;
        private EntityType entityType;
        private String message;
    }

    @Data
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ExceptionResponse {
        private int status;
        private String message;
        private String debugMessage;
    }

}
