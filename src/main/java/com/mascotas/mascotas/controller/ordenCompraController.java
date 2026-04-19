package com.mascotas.mascotas.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.mascotas.mascotas.model.ordenCompra;
import com.mascotas.mascotas.repository.ordenCompraRepository;

@RestController
@RequestMapping("/ordenes")
public class ordenCompraController {

    @Autowired
    private ordenCompraRepository ordenCompraRepository;
    



    
}
