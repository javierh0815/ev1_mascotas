package com.mascotas.mascotas.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ordenCompra")
public class ordenCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank(message = "El código de orden es obligatorio")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{3}$", message = "Formato inválido (Ej: AA001)")
    @Column(name = "codOrden", unique = true)
    private String codOrden;

    @Min(value = 1, message = "Error de formato: El precio debe ser mayor a 0")
    @Column(name = "precio")
    private int precio;

    @NotBlank(message = "Error de formato: debe ingresar un precio de producto")
    @Min(value = 1, message = "Error de formato: La unidad debe ser mayor a 0")
    @Column(name = "unidad")
    private int unidad;
    
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "Error de formato: Caracteres inválidos detectados")
    @Size(min = 2, max = 50, message = "Error de formato: Nombre del producto debe tener entre 2 y 50 caracteres")
    @NotBlank(message = "Error de formato: debe ingresar un nombre de producto")
    @Column(name = "producto")
    private String producto;

    @Column(name = "enviada")
    private boolean enviada; 



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCodOrden() {
        return codOrden;
    }

    public void setCodOrden(String codOrden) {
        this.codOrden = codOrden;
    }




    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getUnidad() {
        return unidad;
    }
    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }
    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public boolean isEnviada() {
        return enviada;
    }
    public void setEnviada(boolean enviada) {
        this.enviada = enviada;
    }

    public ordenCompra() {
    
    }

    public ordenCompra(Long id, String codOrden, int precio, int unidad, String producto, boolean enviada) {
        this.id = id;
        this.codOrden = codOrden;
        this.precio = precio;
        this.unidad = unidad;
        this.producto = producto;
        this.enviada = enviada;
    }




    
}
