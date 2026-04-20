package com.mascotas.mascotas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "orden_compra")
public class ordenCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El código de orden es obligatorio")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{3}$", message = "Formato inválido (Ej: AA001)")
    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @NotNull(message = "Error: El precio es obligatorio y no puede estar vacío")
    @Min(value = 1, message = "Error de formato: El precio debe ser mayor a 0")
    @Column(name = "precio", nullable = false)
    private Integer precio;

    @NotNull(message = "Error de formato: debe ingresar la cantidad de unidades")
    @Min(value = 1, message = "Error de formato: La unidad debe ser mayor a 0")
    @Column(name = "unidad", nullable = false)
    private Integer unidad;
    
    @NotBlank(message = "Error de formato: debe ingresar un nombre de producto")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "Error de formato: Caracteres inválidos detectados")
    @Size(min = 2, max = 50, message = "Error de formato: Nombre del producto debe tener entre 2 y 50 caracteres")
    @Column(name = "producto", nullable = false)
    private String producto;

    @NotNull(message = "Error de formato: El estado de envío es obligatorio")
    @Column(name = "enviado", nullable = false)
    private Boolean enviado = false; 

    public ordenCompra() {
    }

    public ordenCompra(Long id, String codigo, Integer precio, Integer unidad, String producto, Boolean enviado) {
        this.id = id;
        this.codigo = codigo;
        this.precio = precio;
        this.unidad = unidad;
        this.producto = producto;
        this.enviado = enviado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }
}