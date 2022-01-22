package com.worzech.inventorymanagementsystem.exceptions;

import lombok.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest request) {
        String body;
        HttpStatus status;
        if (exception.getClass().equals(BadCredentialsException.class)) {
            body = "Złe poświadczenia";
            status = HttpStatus.UNAUTHORIZED;
        } else  if (exception.getClass().equals(DataIntegrityViolationException.class)){
            body = "Nazwa istnieje w bazie";
            status = HttpStatus.NOT_ACCEPTABLE;
        }else {
            body = "Zasób nie odnaleziony";
            status = HttpStatus.NOT_FOUND;
        }
        System.out.println(body);

        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }



    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();

        List<CustomFieldError> fieldErrors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            CustomFieldError fieldError = new CustomFieldError();
            fieldError.setField(((FieldError) error).getField());
            fieldError.setMessage(error.getDefaultMessage());
            fieldErrors.add(fieldError);
        });

        fieldErrorResponse.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(fieldErrorResponse, status);
    }
}
