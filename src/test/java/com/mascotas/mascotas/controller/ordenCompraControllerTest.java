package com.mascotas.mascotas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mascotas.mascotas.model.ordenCompra;
import com.mascotas.mascotas.service.ordenCompraServiceImpl;

import tools.jackson.databind.ObjectMapper;




@WebMvcTest(ordenCompraController.class)
class ordenCompraControllerTest {

        @Autowired
        private MockMvc mockMvc;
        @MockitoBean
        private ordenCompraServiceImpl ordenCompraService;
        @Autowired
        private ObjectMapper objectMapper;
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
        void testgetOrdenCompraById() throws Exception {
                when(ordenCompraService.getOrdenById(1L)).thenReturn(orden);
                mockMvc.perform(get("/ordenes/1"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1L))
                        .andExpect(jsonPath("$.codigo").value("AA001"))
                        .andExpect(jsonPath("$.precio").value(100))
                        .andExpect(jsonPath("$.unidad").value(2))
                        .andExpect(jsonPath("$.producto").value("Producto A"))
                        .andExpect(jsonPath("$.enviado").value(false));
        }

        @Test
        void testCreateOrdenCompra() throws Exception {
                when(ordenCompraService.createOrden(any(ordenCompra.class))).thenReturn(orden);
                mockMvc.perform(post("/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orden)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1L))
                        .andExpect(jsonPath("$.codigo").value("AA001"))
                        .andExpect(jsonPath("$.precio").value(100))
                        .andExpect(jsonPath("$.unidad").value(2))
                        .andExpect(jsonPath("$.producto").value("Producto A"))
                        .andExpect(jsonPath("$.enviado").value(false));
        }

        @Test
        void testdeleteOrden() throws Exception {
                doNothing().when(ordenCompraService).deleteOrden(1L);
                mockMvc.perform(delete("/ordenes/1"))
                        .andExpect(status().isOk());
                verify(ordenCompraService, times(1)).deleteOrden(1L);
        }

        @Test
        void testupdateOrden() throws Exception {
                when(ordenCompraService.updateOrden(eq(1L), any(ordenCompra.class))).thenReturn(orden);
                mockMvc.perform(put("/ordenes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orden)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1L))
                        .andExpect(jsonPath("$.codigo").value("AA001"))
                        .andExpect(jsonPath("$.precio").value(100))
                        .andExpect(jsonPath("$.unidad").value(2))
                        .andExpect(jsonPath("$.producto").value("Producto A"))
                        .andExpect(jsonPath("$.enviado").value(false));
                verify(ordenCompraService, times(1)).updateOrden(eq(1L), any(ordenCompra.class));
        }












    
}
