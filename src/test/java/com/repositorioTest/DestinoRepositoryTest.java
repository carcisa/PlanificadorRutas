package com.repositorioTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.entidades.Destino;
import com.repositorio.DestinoRepository;

@DataJpaTest
public class DestinoRepositoryTest {

    @Autowired
    private DestinoRepository destinoRepository;

    @Test
    public void pruebaGuardarYBuscarDestinoPorId() {
        Destino destino = new Destino();
        destino.setNombre("Destino de Prueba");
        destino.setDescripcion("Descripción del destino de prueba");
        destino = destinoRepository.save(destino);

        Optional<Destino> encontrado = destinoRepository.findById(destino.getId().intValue());

        assertTrue(encontrado.isPresent());
        assertEquals("Destino de Prueba", encontrado.get().getNombre());
    }

    @Test
    public void pruebaEliminarDestinoPorId() {
        Destino destino = new Destino();
        destino.setNombre("Destino a Eliminar");
        destino.setDescripcion("Este destino será eliminado");
        destino = destinoRepository.save(destino);

        destinoRepository.deleteById(destino.getId().intValue());

        Optional<Destino> resultado = destinoRepository.findById(destino.getId().intValue());
        assertFalse(resultado.isPresent());
    }
}
