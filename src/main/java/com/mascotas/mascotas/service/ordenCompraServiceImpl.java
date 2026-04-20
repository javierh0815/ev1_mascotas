package com.mascotas.mascotas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));
        
    }

    @Override
    public ordenCompra createOrden(ordenCompra orden) {
        if (ordenCompraRepository.existsByCodOrden(orden.getCodOrden())) {
            throw new RuntimeException("Ya existe una orden con el código: " + orden.getCodOrden());
        }
            return ordenCompraRepository.save(orden);
    }

    @Override
    public ordenCompra updateOrden(Long id, ordenCompra orden) {
        return ordenCompraRepository.findById(id).map(existingOrden -> {

            if (!existingOrden.getCodOrden().equals(orden.getCodOrden()) && 
                ordenCompraRepository.existsByCodOrden(orden.getCodOrden())) {
                
                throw new RuntimeException("El código '" + orden.getCodOrden() + "' ya está registrado en otra orden.");
            }

            existingOrden.setCodOrden(orden.getCodOrden());
            existingOrden.setProducto(orden.getProducto());
            existingOrden.setPrecio(orden.getPrecio());
            existingOrden.setUnidad(orden.getUnidad());
            existingOrden.setEnviado(orden.getEnviado());

            return ordenCompraRepository.save(existingOrden);

        }).orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));
    }

    @Override
    public void deleteOrden(Long id) {
        if (!ordenCompraRepository.existsById(id)) {
            throw new RuntimeException("Orden no encontrada con ID: " + id);
        }
        ordenCompraRepository.deleteById(id);
    }

















}