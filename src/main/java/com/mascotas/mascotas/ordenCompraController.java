package com.mascotas.mascotas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ordenCompraController {

    //lista
    private List<ordenCompra> listaOrdenes = new ArrayList<>();

    public ordenCompraController(){


        //registros iniciales
        listaOrdenes.add(new ordenCompra(1, 15000, 2, "Saco Arena Sanitaria 5kg", true));
        listaOrdenes.add(new ordenCompra(2, 4500, 1, "Juguete Ratón con Catnip", false));
        listaOrdenes.add(new ordenCompra(3, 32000, 1, "Cama Ortopédica Perro Grande", true));
        listaOrdenes.add(new ordenCompra(4, 8500, 3, "Lata Alimento Húmedo Premium", true));
        listaOrdenes.add(new ordenCompra(5, 12000, 1, "Rascador de Cartón Reforzado", false));





    }


        //consultar estado de orden por id
        @GetMapping("/consultar/{id}")
        public ResponseEntity<?> consultarOrdenes(@PathVariable int id) {
            for (ordenCompra ocid : listaOrdenes) {
                if(ocid.getId() == id) {
                    return ResponseEntity.ok(ocid);
                }
            }
            errorDatos error = new errorDatos(id, "La orden no existe en el sistema");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }


        //consultar estado de la orden, muestra valores true
        @GetMapping("/disponibles")
        public List<ordenCompra> verEstados() {
            List<ordenCompra> estOK = new ArrayList<>();
            for (ordenCompra oces : listaOrdenes) {
                if(oces.isEstado()){
                    estOK.add(oces);
                }
            }

            return estOK;
        }

        //verificacion y cambio de estado pendiente a procesado
        @GetMapping("/verificar/{id}")
        public ResponseEntity<?> verifOrden(@PathVariable int id) {
            for (ordenCompra ocen : listaOrdenes) {
                if (ocen.getId() == id){
                    if (ocen.isEstado()) {
                        errorDatos errorYaProcesada = new errorDatos(id, "Error: el pedido ya fue procesado");
                        return new ResponseEntity<>(errorYaProcesada, HttpStatus.BAD_REQUEST);
                    }

                    ocen.setEstado(true);
                    return ResponseEntity.ok(ocen);
                }
            }

            errorDatos errorNoExiste = new errorDatos(id, "La orden no existe en el sistema");
            return new ResponseEntity<>(errorNoExiste,HttpStatus.NOT_FOUND);
        }

        //crear una nueva - con parametros a partir de GET
        //url de prueba ok http://localhost:8080/nueva/15000/Rascador/2
        //error precio http://localhost:8080/nueva/0/Juguete/1
        //error nombre http://localhost:8080/nueva/5000/null/3
        //error unidad http://localhost:8080/nueva/2500/Pelota/-1

        @GetMapping ("/nueva/{precio}/{producto}/{unidad}")
        public ResponseEntity<?> nuevaOrden (
                                                @PathVariable int precio,
                                                @PathVariable String producto,
                                                @PathVariable int unidad
                                                ) {
            if (precio <= 0){
                errorDatos errorPrecio = new errorDatos(0, "Error: Precio insertado de forma errónea");
                return new ResponseEntity<>(errorPrecio, HttpStatus.BAD_REQUEST);
            }

            if (producto == null || producto.trim().isEmpty() || producto.equals("null")){
                errorDatos errorNombre = new errorDatos(0, "Error: Nombre del producto insertado de forma errónea");
                return new ResponseEntity<>(errorNombre, HttpStatus.BAD_REQUEST);
            }

            if (unidad <= 0) {
                errorDatos errorUnidad = new errorDatos(0, "Error: Unidad(es) ingresadas de forma errónea");
                return new ResponseEntity<>(errorUnidad, HttpStatus.BAD_REQUEST);
            }

            int nuevoId = listaOrdenes.size() + 1;
            ordenCompra nueva = new ordenCompra(nuevoId, precio, unidad, producto, true);
            listaOrdenes.add(nueva);

            return ResponseEntity.ok(nueva);

        }

        //eliminar una orden de compra
        @GetMapping("/eliminar/{id}")
        public ResponseEntity<?> eliminarOrden(@PathVariable int id) {
            if (id < 1 || id > listaOrdenes.size()){
                errorDatos errorId = new errorDatos (id, "Error: No existe la orden a eliminar");
                return new ResponseEntity<>(errorId, HttpStatus.NOT_FOUND);
            }

            ordenCompra eliminada = listaOrdenes.remove(id - 1);
            return ResponseEntity.ok(eliminada);
        }


    
}
