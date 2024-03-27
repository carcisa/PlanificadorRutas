package com.repository.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.entidades.Destino;
import com.repositorio.DestinoRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DestinoRepositoryTest {

    @Autowired
    private DestinoRepository destinoRepository;

    @Test
    public void pruebaCrearYBuscarDestinoPorId() {
        Destino destino = new Destino();
        destino.setId(1002);
        destino.setNombre("Baleares");
        destino.setDescripcion("Isla muy bonitas en el mediterráneo");
        destino.setActividades(new ArrayList<>());
        destino = destinoRepository.save(destino);
        
        Optional<Destino> destinoEncontrado = destinoRepository.findById(destino.getId());
        
        assertTrue(destinoEncontrado.isPresent());
        assertNotNull(destinoEncontrado.get());
    }

    @Test
    public void pruebaEliminarDestinoPorId() {
        Destino destino = new Destino();
        destino.setId(1003);
        destino.setNombre("Baleares");
        destino.setDescripcion("Isla muy bonitas en el mediterráneo");
        destino.setActividades(new ArrayList<>());
        destino = destinoRepository.save(destino);
        
        destinoRepository.deleteById(destino.getId());
        
        Optional<Destino> destinoEliminado = destinoRepository.findById(destino.getId());
        assertFalse(destinoEliminado.isPresent());
    }
}