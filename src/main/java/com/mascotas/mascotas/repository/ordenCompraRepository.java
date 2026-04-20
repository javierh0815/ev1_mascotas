package com.mascotas.mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.mascotas.model.ordenCompra;

public interface ordenCompraRepository extends JpaRepository<ordenCompra, Long> {
    boolean existsByCodOrden(String codOrden);

}
