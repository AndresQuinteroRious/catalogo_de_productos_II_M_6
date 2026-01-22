package com.devsenior.andresquintero.catalogo.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        this("Producto no encontrado");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
