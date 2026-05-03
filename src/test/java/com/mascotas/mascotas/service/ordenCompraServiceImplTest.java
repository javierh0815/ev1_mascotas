package com.mascotas.mascotas.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mascotas.mascotas.model.ordenCompra;
import com.mascotas.mascotas.repository.ordenCompraRepository;


@ExtendWith(MockitoExtension.class)
class ordenCompraServiceImplTest {
    
    @Mock
    private ordenCompraRepository ordenCompraRepository;

    @InjectMocks
    private ordenCompraServiceImpl ordenCompraService;

    private ordenCompra orden;

    
    @BeforeEach
    void setUp() {
        orden = new ordenCompra();
        orden.setId(1L);
        orden.setCodigo("AA001");
        orden.setPrecio(100);
        orden.setUnidad(2);
        orden.setProducto("Producto A");
        orden.setEnviado(false);
    }

    @Test
    void testGetOrdenById() {
        when(ordenCompraRepository.findById(1L)).thenReturn(Optional.of(orden));
        ordenCompra foundOrden = ordenCompraService.getOrdenById(1L);
        assertEquals("AA001", foundOrden.getCodigo());
        assertEquals("Producto A", foundOrden.getProducto());
    }

    @Test
    void testCreateOrden() {
        when(ordenCompraRepository.existsByCodigo(orden.getCodigo())).thenReturn(false);
        when(ordenCompraRepository.save(orden)).thenReturn(orden);
        assertEquals(orden, ordenCompraService.createOrden(orden));
    }

    @Test
    void testUpdateOrdenExists(){
        when(ordenCompraRepository.findById(1L)).thenReturn(Optional.of(orden));
        ordenCompra cambios = new ordenCompra();
        cambios.setCodigo("AA002");
        cambios.setPrecio(150);
        cambios.setUnidad(3);
        cambios.setProducto("Producto B");
        cambios.setEnviado(true);
        when(ordenCompraRepository.existsByCodigo("AA002")).thenReturn(false);
        when(ordenCompraRepository.save(org.mockito.ArgumentMatchers.any(ordenCompra.class))).thenReturn(cambios); 
        ordenCompra updatedOrden = ordenCompraService.updateOrden(1L, cambios);
        assertEquals("AA002", updatedOrden.getCodigo());
    }

    @Test
    void testUpdateOrdenNotExists(){
        when(ordenCompraRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            ordenCompraService.updateOrden(1L, orden);
        });
        assertEquals("Orden de compra no encontrada con ID: 1", exception.getMessage());
    }

    @Test
    void testDeleteOrden() {
        when(ordenCompraRepository.existsById(1L)).thenReturn(true);
        ordenCompraService.deleteOrden(1L);
        org.mockito.Mockito.verify(ordenCompraRepository).deleteById(1L);
    }
















}
