package com.mascotas.mascotas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.mascotas.mascotas.exception.ordenCompraNotFoundException;
import com.mascotas.mascotas.model.ordenCompra;
import com.mascotas.mascotas.repository.ordenCompraRepository;


@Service
public class ordenCompraServiceImpl implements ordenCompraService {

    @Autowired
    private ordenCompraRepository ordenCompraRepository;

    @Override
    public List<ordenCompra> getAllOrdenes() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public ordenCompra getOrdenById(Long id) {
        return ordenCompraRepository.findById(id)
                .orElseThrow(() -> new ordenCompraNotFoundException(id));
        
    }

    @Override
    public ordenCompra createOrden(ordenCompra orden) {
        if (ordenCompraRepository.existsByCodigo(orden.getCodigo())) {
            throw new RuntimeException("Ya existe una orden con el código: " + orden.getCodigo());
        }
            return ordenCompraRepository.save(orden);
    }

    @Override
    public ordenCompra updateOrden(Long id, ordenCompra orden) {
        return ordenCompraRepository.findById(id).map(existingOrden -> {

            if (!existingOrden.getCodigo().equals(orden.getCodigo()) && 
                ordenCompraRepository.existsByCodigo(orden.getCodigo())) {
                
                throw new RuntimeException("El código '" + orden.getCodigo() + "' ya está registrado en otra orden.");
            }

            existingOrden.setCodigo(orden.getCodigo());
            existingOrden.setProducto(orden.getProducto());
            existingOrden.setPrecio(orden.getPrecio());
            existingOrden.setUnidad(orden.getUnidad());
            existingOrden.setEnviado(orden.getEnviado());

            return ordenCompraRepository.save(existingOrden);

        }).orElseThrow(() -> new ordenCompraNotFoundException(id));
    }

    @Override
    public void deleteOrden(Long id) {
        if (!ordenCompraRepository.existsById(id)) {
            throw new ordenCompraNotFoundException(id);
        }
        ordenCompraRepository.deleteById(id);
    }

















}