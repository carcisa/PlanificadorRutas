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

import com.entidades.Destino;
import com.repositorio.DestinoRepository;
import com.serviceImpl.DestinoServiceImpl;


@SpringBootTest
public class DestinoServiceTest {

    @Mock
    private DestinoRepository destinoRepository;

    @InjectMocks
    private DestinoServiceImpl destinoServicio;

    private Destino destino;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        destino = new Destino();
    }

    @Test
    public void testGuardarDestino() {
        when(destinoRepository.save(any(Destino.class))).thenReturn(destino);
        Destino guardado = destinoServicio.save(new Destino());
        assertNotNull(guardado);
        verify(destinoRepository).save(any(Destino.class));
    }

    @Test
    public void testEliminarDestino() {
        doNothing().when(destinoRepository).deleteById(anyLong());
        destinoServicio.deleteById(1);
        verify(destinoRepository).deleteById(anyLong());
    }

    @Test
    public void testBuscarDestinoPorId() {
        when(destinoRepository.findById(anyLong())).thenReturn(Optional.of(destino));
        Optional<Destino> encontrado = destinoServicio.findById(1);
        assertTrue(encontrado.isPresent());
        verify(destinoRepository).findById(anyLong());
    }

    @Test
    public void testListarTodosLosDestinos() {
        when(destinoRepository.findAll()).thenReturn(Collections.singletonList(destino));
        List<Destino> destinos = destinoServicio.findAll();
        assertEquals(1, destinos.size());
        verify(destinoRepository).findAll();
    }
}
