package com.Controller.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.controlador.DestinoController;
import com.entidades.Atraccion;
import com.entidades.Destino;
import com.servicio.DestinoService;

@WebMvcTest(DestinoController.class)
public class DestinoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinoService destinoService;

    @Test
    public void testGetAllDestinos() throws Exception {
        mockMvc.perform(get("/api/destinos/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDestinoById() throws Exception {
        when(destinoService.findById(anyInt())).thenReturn(Optional.of(new Destino()));
        mockMvc.perform(get("/api/destinos/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDestinoByIdNotFound() throws Exception {
        when(destinoService.findById(anyInt())).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/destinos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAtraccionesGetDestinoById() throws Exception {
        List<Atraccion> atracciones = new ArrayList<>();
        when(destinoService.findById(anyInt())).thenReturn(Optional.of(new Destino()));
        mockMvc.perform(get("/api/destinos/1/atracciones"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateDestino() throws Exception {
        mockMvc.perform(post("/api/destinos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nombre\": \"Destino Test\", \"descripcion\": \"Descripción de prueba\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddAtraccionToDestino() throws Exception {
        when(destinoService.findById(anyInt())).thenReturn(Optional.of(new Destino()));
        mockMvc.perform(post("/api/destinos/1/atracciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nombre\": \"Atracción Test\", \"descripcion\": \"Descripción de prueba\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateDestino() throws Exception {
        when(destinoService.findById(anyInt())).thenReturn(Optional.of(new Destino()));
        mockMvc.perform(put("/api/destinos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nombre\": \"Nuevo Nombre\", \"descripcion\": \"Nueva Descripción\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDestino() throws Exception {
        when(destinoService.findById(anyInt())).thenReturn(Optional.of(new Destino()));
        doNothing().when(destinoService).deleteById(anyInt());
        mockMvc.perform(delete("/api/destinos/1"))
                .andExpect(status().isNoContent());
        verify(destinoService).deleteById(1);
    }
}
