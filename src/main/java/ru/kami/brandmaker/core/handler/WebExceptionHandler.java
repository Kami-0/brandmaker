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
import ru.kami.brandmaker.core.api.exceptions.EntityNotFoundException;

import javax.validation.ConstraintViolationException;

/**
 * @author Daniil.Makarov
 */
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * При не валидном id
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionResponse("Некорректный запрос", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    /**
     * При некорректном запросе
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionResponse("Некорректный запрос", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    /**
     * При неправильном json формате
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionResponse("Некорректный JSON запрос", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * При невалидных параметрах запроса
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ExceptionResponse> handlePathVariableException(ConstraintViolationException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * При невалидности переменой в пути запроса
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> handleIncorrectPath(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse("Некорректный " + ex.getParameter().getParameterName(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ExceptionResponse {
        private String message;
        private String debugMessage;
    }

}
