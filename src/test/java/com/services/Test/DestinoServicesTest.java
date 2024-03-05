package com.services.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.entidades.Destino;
import com.repositorio.DestinoRepository;
import com.serviceImpl.DestinoServiceImpl;

public class DestinoServicesTest {

    @Mock
    private DestinoRepository destinoRepository;

    @InjectMocks
    private DestinoServiceImpl destinoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        Destino destino1 = new Destino("Playa Blanca", "Una hermosa playa de arena blanca.");
        Destino destino2 = new Destino("Montañas Azules", "Cadenas montañosas con vistas impresionantes.");
        when(destinoRepository.findAll()).thenReturn(Arrays.asList(destino1, destino2));

        List<Destino> destinos = destinoService.findAll();

        assertNotNull(destinos);
        assertEquals(2, destinos.size());
        verify(destinoRepository).findAll();
    }

    @Test
    public void testFindById() {
        Optional<Destino> destino = Optional.of(new Destino(1, "Playa Blanca", "Una hermosa playa de arena blanca.", null));
        when(destinoRepository.findById(1)).thenReturn(destino);

        Optional<Destino> result = destinoService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(destino.get().getNombre(), result.get().getNombre());
        verify(destinoRepository).findById(1);
    }

    @Test
    public void testSave() {
        Destino destino = new Destino("Ciudad Antigua", "Ruinas históricas con mucho por explorar.");
        when(destinoRepository.save(any(Destino.class))).thenReturn(destino);

        Destino savedDestino = destinoService.save(destino);

        assertNotNull(savedDestino);
        assertEquals("Ciudad Antigua", savedDestino.getNombre());
        verify(destinoRepository).save(destino);
    }

    @Test
    public void testDeleteById() {
        destinoService.deleteById(1);

        verify(destinoRepository).deleteById(1);
    }
}
