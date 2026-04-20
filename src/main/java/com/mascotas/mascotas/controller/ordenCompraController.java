package com.mascotas.mascotas.controller;

import com.mascotas.mascotas.model.ordenCompra;
import com.mascotas.mascotas.service.ordenCompraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class ordenCompraController {

    private final ordenCompraService service;

    public ordenCompraController(ordenCompraService service) {
        this.service = service;
    }

    @GetMapping
    public List<ordenCompra> listarTodas() {
        return service.getAllOrdenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ordenCompra> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrdenById(id));
    }

    @PostMapping
    public ResponseEntity<ordenCompra> crear(@Valid @RequestBody ordenCompra orden) {
        return new ResponseEntity<>(service.createOrden(orden), org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ordenCompra> actualizar(@PathVariable Long id, @Valid @RequestBody ordenCompra orden) {
        return ResponseEntity.ok(service.updateOrden(id, orden));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.deleteOrden(id);
        return ResponseEntity.ok("La orden con ID " + id + " ha sido eliminada correctamente de la base de datos.");
    }
}