package com.roman.converter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> generateBadRequestException(BadRequestException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setStatus(String.valueOf(ex.getStatus().value()));
        errorDTO.setTime(new Date().toString());

        return new ResponseEntity<ErrorDTO>(errorDTO, ex.getStatus());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorDTO> generateBadRequestException(NumberFormatException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Query input must be a valid number.");
        errorDTO.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDTO.setTime(new Date().toString());

        return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}