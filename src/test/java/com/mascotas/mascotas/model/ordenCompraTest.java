package com.mascotas.mascotas.model;

import org.junit.jupiter.api.Test;

class ordenCompraTest {
    
    @Test
    void testordenCompraCreation() {
        ordenCompra orden = new ordenCompra(1L, "AA001", 100, 2, "Producto A", false);
        assert orden.getId().equals(1L);
        assert orden.getCodigo().equals("AA001");
        assert orden.getPrecio().equals(100);
        assert orden.getUnidad().equals(2);
        assert orden.getProducto().equals("Producto A");
        assert !orden.getEnviado();
    }









    
}
