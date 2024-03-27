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

import com.controlador.ActividadController;
import com.entidades.Actividad;
import com.servicio.ActividadService;

@SpringBootTest
class ActividadControllerTest {

    @Mock
    private ActividadService actividadService;

    @InjectMocks
    private ActividadController actividadController;

    @Test
    void getAllActividadesTest() {
        List<Actividad> actividadList = new ArrayList<>();
        when(actividadService.findAll()).thenReturn(actividadList);

        List<Actividad> response = actividadController.getAllActividades();

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    void getActividadByIdTest() {
        Long id = 1L;
        Actividad actividad = new Actividad();
        actividad.setId(id);
        actividad.setNombre("Actividad 1");
        when(actividadService.findById(id)).thenReturn(Optional.of(actividad));

        ResponseEntity<Actividad> response = actividadController.getActividadById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actividad, response.getBody());
    }

    @Test
    void createActividadTest() {
        Actividad actividad = new Actividad();
        when(actividadService.save(any(Actividad.class))).thenReturn(actividad);

        Actividad response = actividadController.createActividad(actividad);

        assertNotNull(response);
    }

    @Test
    void updateActividadTest() {
        Long id = 1L;
        Actividad actividadDetails = new Actividad();
        when(actividadService.findById(id)).thenReturn(Optional.of(new Actividad()));
        when(actividadService.save(any(Actividad.class))).thenReturn(actividadDetails);

        ResponseEntity<Actividad> response = actividadController.updateActividad(id, actividadDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actividadDetails, response.getBody());
    }

    @Test
    void deleteActividadTest() {
        Long id = 1L;
        when(actividadService.findById(id)).thenReturn(Optional.of(new Actividad()));

        ResponseEntity<Void> response = actividadController.deleteActividad(id);

        verify(actividadService, times(1)).deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
