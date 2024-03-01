package com.Controller.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.controlador.AtraccionController;
import com.entidades.Atraccion;
import com.servicio.AtraccionService;

@WebMvcTest(AtraccionController.class)
public class AtraccionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtraccionService atraccionService;

    @Test
    public void testGetAllAtracciones() throws Exception {
        mockMvc.perform(get("/api/atracciones/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAtraccionById() throws Exception {
        when(atraccionService.findById(any())).thenReturn(Optional.of(new Atraccion()));
        mockMvc.perform(get("/api/atracciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAtraccionByIdNotFound() throws Exception {
        when(atraccionService.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/atracciones/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateAtraccion() throws Exception {
        mockMvc.perform(post("/api/atracciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nombre\": \"Atraccion Test\", \"descripcion\": \"Descripción de prueba\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAtraccion() throws Exception {
        when(atraccionService.findById(any())).thenReturn(Optional.of(new Atraccion()));
        mockMvc.perform(put("/api/atracciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nombre\": \"Nuevo Nombre\", \"descripcion\": \"Nueva Descripción\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAtraccion() throws Exception {
        when(atraccionService.findById(any())).thenReturn(Optional.of(new Atraccion()));
        doNothing().when(atraccionService).deleteById(any());
        mockMvc.perform(delete("/api/atracciones/1"))
                .andExpect(status().isOk());
        verify(atraccionService).deleteById(1L);
    }
}
