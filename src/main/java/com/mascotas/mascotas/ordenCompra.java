package com.mascotas.mascotas;

public class ordenCompra {
    private int id;
    private int precio;
    private int unidad;
    private String producto;
    private boolean estado; 



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ordenCompra(int id, int precio, int unidad, String producto, boolean estado) {
        this.id = id;
        this.precio = precio;
        this.unidad = unidad;
        this.producto = producto;
        this.estado = estado;
    }




    
}
