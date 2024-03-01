package com.serviceTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.entidades.Atraccion;
import com.repositorio.AtraccionRepository;
import com.serviceImpl.AtraccionServiceImpl;

@SpringBootTest
public class AtraccionesServiceTest {

    @Mock
    private AtraccionRepository atraccionRepository;

    @InjectMocks
    private AtraccionServiceImpl atraccionServicio;

    private Atraccion atraccion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        atraccion = new Atraccion();
    }

    @Test
    public void testGuardarAtraccion() {
        when(atraccionRepository.save(any(Atraccion.class))).thenReturn(atraccion);
        Atraccion guardado = atraccionServicio.save(new Atraccion());
        assertNotNull(guardado);
        verify(atraccionRepository).save(any(Atraccion.class));
    }

    @Test
    public void testEliminarAtraccion() {
        doNothing().when(atraccionRepository).deleteById(anyLong());
        atraccionServicio.deleteById(1L);
        verify(atraccionRepository).deleteById(anyLong());
    }

    @Test
    public void testBuscarAtraccionPorId() {
        when(atraccionRepository.findById(anyLong())).thenReturn(Optional.of(atraccion));
        Optional<Atraccion> encontrado = atraccionServicio.findById(1L);
        assertTrue(encontrado.isPresent());
        verify(atraccionRepository).findById(anyLong());
    }

    @Test
    public void testListarTodasLasAtracciones() {
        when(atraccionRepository.findAll()).thenReturn(Collections.singletonList(atraccion));
        List<Atraccion> atracciones = atraccionServicio.findAll();
        assertEquals(1, atracciones.size());
        verify(atraccionRepository).findAll();
    }
}