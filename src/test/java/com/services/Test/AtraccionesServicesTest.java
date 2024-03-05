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

import com.entidades.Atraccion;
import com.entidades.Categoria;
import com.entidades.Destino;
import com.repositorio.AtraccionRepository;
import com.serviceImpl.AtraccionServiceImpl;

public class AtraccionesServicesTest {

    @Mock
    private AtraccionRepository atraccionRepository;

    @InjectMocks
    private AtraccionServiceImpl atraccionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        Categoria categoriaTest = Categoria.CULTURA; 
        Destino destinoTest = new Destino(); 

        Atraccion atraccion1 = new Atraccion("Montaña Rusa", "Una emocionante montaña rusa.", categoriaTest, "Calle 123", destinoTest);
        Atraccion atraccion2 = new Atraccion("Casa Embrujada", "Una casa con sorpresas escalofriantes.", categoriaTest, "Calle 456", destinoTest);

        when(atraccionRepository.findAll()).thenReturn(Arrays.asList(atraccion1, atraccion2));

        List<Atraccion> atracciones = atraccionService.findAll();

        assertNotNull(atracciones);
        assertEquals(2, atracciones.size(), "Debería haber 2 atracciones en la lista");
        assertEquals("Montaña Rusa", atracciones.get(0).getNombre(), "El nombre de la primera atracción debe coincidir");
        assertEquals("Casa Embrujada", atracciones.get(1).getNombre(), "El nombre de la segunda atracción debe coincidir");

        verify(atraccionRepository).findAll();
    }


    @Test
    public void testFindById() {
        Long idTest = 1L;
        Categoria categoriaTest = Categoria.DEPORTES;
        Destino destinoTest = new Destino();
        Optional<Atraccion> atraccionOpt = Optional.of(new Atraccion("Parque Acuático", "Diversión acuática para toda la familia.", categoriaTest, "Calle 789", destinoTest));

        when(atraccionRepository.findById(idTest)).thenReturn(atraccionOpt);

        Optional<Atraccion> result = atraccionService.findById(idTest);

        assertTrue(result.isPresent(), "La atracción debe ser encontrada");
        assertEquals("Parque Acuático", result.get().getNombre(), "El nombre de la atracción debe coincidir");

        verify(atraccionRepository).findById(idTest);
    }

    @Test
    public void testSave() {
        Categoria categoriaTest = Categoria.GASTRONOMIA;
        Destino destinoTest = new Destino();
        Atraccion atraccion = new Atraccion("Feria de Comida", "Sabores del mundo en un solo lugar.", categoriaTest, "Plaza Central", destinoTest);

        when(atraccionRepository.save(any(Atraccion.class))).thenReturn(atraccion);

        Atraccion savedAtraccion = atraccionService.save(atraccion);

        assertNotNull(savedAtraccion, "La atracción guardada no debe ser nula");
        assertEquals("Feria de Comida", savedAtraccion.getNombre(), "El nombre de la atracción guardada debe coincidir");

        verify(atraccionRepository).save(atraccion);
    }

    @Test
    public void testDeleteById() {
        Long idTest = 1L;
        atraccionService.deleteById(idTest);
        verify(atraccionRepository).deleteById(idTest);
    }
    
}
