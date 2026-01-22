package com.devsenior.andresquintero.catalogo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsenior.andresquintero.catalogo.exception.ProductNotFoundException;
import com.devsenior.andresquintero.catalogo.exception.ProductoIdNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
      var response = new ApiErrorResponse();
      response.setCode(HttpStatus.BAD_REQUEST.value());
      response.setMessage(ex.getMessage());

      var errors=new HashMap<String, String>();
      ex.getBindingResult().getFieldErrors()
      .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
      response.setErrors(errors);

      
      return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        var response = new ApiErrorResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductoIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProductoIdNotFoundException(ProductoIdNotFoundException ex) {
        var response = new ApiErrorResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        var response = new ApiErrorResponse();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static class ApiErrorResponse {
        private int code;
        private String message;
        private Map<String, String> errors;
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public Map<String, String> getErrors() {
            return errors;
        }
        public void setErrors(Map<String, String> errors) {
            this.errors = errors;
        }

        


        

        // Getters and setters
    }
    }
