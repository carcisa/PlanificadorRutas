package com.controller.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.controlador.AtraccionController;
import com.entidades.Atraccion;
import com.servicio.AtraccionService;

@SpringBootTest
class AtraccionControllerTest {

    @Mock
    private AtraccionService atraccionService;

    @InjectMocks
    private AtraccionController atraccionController;

    @Test
    void getAllAtraccionesTest() {
        List<Atraccion> atraccionList = new ArrayList<>();
        when(atraccionService.findAll()).thenReturn(atraccionList);

        List<Atraccion> response = atraccionController.getAllAtracciones();

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    void getAtraccionByIdTest() {
        Long id = 1L;
        Atraccion atraccion = new Atraccion();
        atraccion.setId(id);
        atraccion.setNombre("Atraccion 1");
        when(atraccionService.findById(id)).thenReturn(Optional.of(atraccion));

        ResponseEntity<Atraccion> response = atraccionController.getAtraccionById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atraccion, response.getBody());
    }

    @Test
    void createAtraccionTest() {
        Atraccion atraccion = new Atraccion();
        when(atraccionService.save(any(Atraccion.class))).thenReturn(atraccion);

        Atraccion response = atraccionController.createAtraccion(atraccion);

        assertNotNull(response);
    }

    @Test
    void updateAtraccionTest() {
        Long id = 1L;
        Atraccion atraccionDetails = new Atraccion();
        when(atraccionService.findById(id)).thenReturn(Optional.of(new Atraccion()));
        when(atraccionService.save(any(Atraccion.class))).thenReturn(atraccionDetails);

        ResponseEntity<Atraccion> response = atraccionController.updateAtraccion(id, atraccionDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atraccionDetails, response.getBody());
    }

    @Test
    void deleteAtraccionTest() {
        Long id = 1L;
        when(atraccionService.findById(id)).thenReturn(Optional.of(new Atraccion()));

        ResponseEntity<Void> response = atraccionController.deleteAtraccion(id);

        verify(atraccionService, times(1)).deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
