package com.mascotas.mascotas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ordenCompraNotFoundException extends RuntimeException {
    public ordenCompraNotFoundException(Long id) {
        super("Orden de compra no encontrada con ID: " + id);
    }
    
}
