package com.mascotas.mascotas.service;


import com.mascotas.mascotas.model.ordenCompra;
import java.util.List;

public interface ordenCompraService {
    List<ordenCompra> getAllOrdenes();
    ordenCompra getOrdenById(Long id);
    ordenCompra createOrden(ordenCompra orden);
    ordenCompra updateOrden(Long id, ordenCompra orden);
    void deleteOrden(Long id);
    
}
