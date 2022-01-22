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
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        HttpStatus status;
        FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
        List<CustomFieldError> fieldErrors = new ArrayList<>();
        CustomFieldError fieldError = new CustomFieldError();
        if (ex.getClass().equals(BadCredentialsException.class)) {
           fieldError.setField("credentials");
           fieldError.setMessage("Złe poświadczenia");
           fieldErrors.add(fieldError);
            status = HttpStatus.UNAUTHORIZED;
        } else  if (ex.getClass().equals(DataIntegrityViolationException.class)){
            fieldError.setField("unique");
            fieldError.setMessage("Nazwa istnieje w bazie");
            fieldErrors.add(fieldError);
            status = HttpStatus.NOT_ACCEPTABLE;
        }else {
            fieldError.setField("noData");
            fieldError.setMessage("Zasób nie odnaleziony");
            fieldErrors.add(fieldError);
            status = HttpStatus.NOT_FOUND;
        }
        fieldErrorResponse.setFieldErrors(fieldErrors);

        return new ResponseEntity<>(fieldErrorResponse, status);
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
