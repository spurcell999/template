package com.project.template.api.rest.controllers;

import com.project.template.api.rest.response.ErrorResponse;
import com.project.template.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *  
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> serviceExceptionHandler(ServiceException ex){
        log.info("Responding Service Exception with status {} and errorList {}", ex.getStatus(), ex.getErrorList());
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorList()),ex.getStatus());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse generationExceptionHandler(Exception e){
        log.info("Responding INTERNAL SERVER ERROR Exception");
        return new ErrorResponse(ServiceException.getSystemError());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServiceException se = new ServiceException();
        return new ResponseEntity<>(se.generateErrorList(ex.getBindingResult().getFieldErrors(),ex.getBindingResult().getObjectName()),status);


    }
}
