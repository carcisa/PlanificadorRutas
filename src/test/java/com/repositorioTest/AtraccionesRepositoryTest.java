package com.repositorioTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.entidades.Atraccion;
import com.repositorio.AtraccionRepository;

@DataJpaTest
public class AtraccionesRepositoryTest {

    @Autowired
    private AtraccionRepository atraccionRepository;

    @Test
    public void pruebaGuardarYBuscarAtraccion() {
        Atraccion atraccion = new Atraccion();
        atraccion.setNombre("Atracción de Prueba");
        atraccion.setDescripcion("Descripción de la atracción de prueba");
        atraccion = atraccionRepository.save(atraccion);

        List<Atraccion> atracciones = atraccionRepository.findAll();
        assertTrue(atracciones.contains(atraccion));
    }
}
