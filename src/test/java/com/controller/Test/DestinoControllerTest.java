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

import com.controlador.DestinoController;
import com.entidades.Atraccion;
import com.entidades.Destino;
import com.servicio.DestinoService;

@SpringBootTest
class DestinoControllerTest {

    @Mock
    private DestinoService destinoService;

    @InjectMocks
    private DestinoController destinoController;

    @Test
    void getAllDestinosTest() {
        List<Destino> destinoList = new ArrayList<>();
        when(destinoService.findAll()).thenReturn(destinoList);

        List<Destino> response = destinoController.getAllDestinos();

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    void getDestinoByIdTest() {
        Integer id = 1;
        Destino destino = new Destino();
        destino.setId(id);
        destino.setNombre("Destino 1");
        when(destinoService.findById(id)).thenReturn(Optional.of(destino));

        ResponseEntity<Destino> response = destinoController.getDestinoById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destino, response.getBody());
    }

//    @Test
//    void AtraccionesGetDestinoByIdTest() {
//        Integer id = 1;
//        Destino destino = new Destino();
//        destino.setId(id);
//        Atraccion atraccion = new Atraccion();
//        atraccion.setNombre("Atraccion 1");
//        destino.agregarAtraccion(atraccion);
//        when(destinoService.findById(id)).thenReturn(Optional.of(destino));
//
//        ResponseEntity<List<Atraccion>> response = destinoController.AtraccionesGetDestinoById(id);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        assertEquals(atraccion, response.getBody().get(0));
//    }

    @Test
    void createDestinoTest() {
        Destino destino = new Destino();
        when(destinoService.save(any(Destino.class))).thenReturn(destino);

        Destino response = destinoController.createDestino(destino);

        assertNotNull(response);
    }

    @Test
    void addAtraccionToDestinoTest() {
        Integer destinoId = 1;
        Destino destino = new Destino();
        List<Atraccion> atracciones = new ArrayList<>();
        destino.setAtracciones(atracciones);
        when(destinoService.findById(destinoId)).thenReturn(Optional.of(destino));

        Atraccion atraccion = new Atraccion();
        ResponseEntity<Atraccion> response = destinoController.addAtraccionToDestino(destinoId, atraccion);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(atraccion, response.getBody());
    }

    @Test
    void updateDestinoTest() {
        Integer id = 1;
        Destino destinoDetails = new Destino();
        when(destinoService.findById(id)).thenReturn(Optional.of(new Destino()));
        when(destinoService.save(any(Destino.class))).thenReturn(destinoDetails);

        ResponseEntity<Destino> response = destinoController.updateDestino(id, destinoDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destinoDetails, response.getBody());
    }

    @Test
    void deleteDestinoTest() {
        Integer id = 1;
        when(destinoService.findById(id)).thenReturn(Optional.of(new Destino()));

        ResponseEntity<Void> response = destinoController.deleteDestino(id);

        verify(destinoService, times(1)).deleteById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
