package com.mascotas.mascotas.controller;

import com.mascotas.mascotas.model.ordenCompra;
import com.mascotas.mascotas.service.ordenCompraService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;


@RestController
@RequestMapping("/ordenes")
public class ordenCompraController {

    @Autowired
    private ordenCompraService service;


    @GetMapping
    public CollectionModel<EntityModel<ordenCompra>> listarTodas() {
        List<ordenCompra> lista = service.getAllOrdenes();
        List<EntityModel<ordenCompra>> ordenesModels = lista.stream()
                .map(orden -> EntityModel.of(orden,
                        linkTo(methodOn(ordenCompraController.class).obtenerPorId(orden.getId())).withSelfRel(),
                        linkTo(methodOn(ordenCompraController.class).listarTodas()).withRel("ordenes")))
                .collect(Collectors.toList());
        return CollectionModel.of(ordenesModels,
                linkTo(methodOn(ordenCompraController.class).listarTodas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ordenCompra>> obtenerPorId(@PathVariable Long id) {
        ordenCompra orden = service.getOrdenById(id);
        EntityModel<ordenCompra> ordenModel = EntityModel.of(orden,
                linkTo(methodOn(ordenCompraController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(ordenCompraController.class).listarTodas()).withRel("ordenes"));
        return ResponseEntity.ok(ordenModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<ordenCompra>> crear(@Valid @RequestBody ordenCompra orden) {
        ordenCompra createdOrden = service.createOrden(orden);
        EntityModel<ordenCompra> ordenModel = EntityModel.of(createdOrden,
                linkTo(methodOn(ordenCompraController.class).obtenerPorId(createdOrden.getId())).withSelfRel(),
                linkTo(methodOn(ordenCompraController.class).listarTodas()).withRel("ordenes"));
        return new ResponseEntity<>(ordenModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ordenCompra>> actualizar(@PathVariable Long id, @Valid @RequestBody ordenCompra orden) {
        ordenCompra updatedOrden = service.updateOrden(id, orden);
        EntityModel<ordenCompra> ordenModel = EntityModel.of(updatedOrden,
                linkTo(methodOn(ordenCompraController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(ordenCompraController.class).listarTodas()).withRel("ordenes"));
        return ResponseEntity.ok(ordenModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.deleteOrden(id);
        return ResponseEntity.noContent().build();
    }
}