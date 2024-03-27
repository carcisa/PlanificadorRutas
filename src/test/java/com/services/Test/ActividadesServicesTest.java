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

import com.entidades.Actividad;
import com.entidades.Categoria;
import com.entidades.Destino;
import com.repositorio.ActividadRepository;
import com.serviceImpl.ActividadServiceImpl;

public class ActividadesServicesTest {

    @Mock
    private ActividadRepository actividadRepository;

    @InjectMocks
    private ActividadServiceImpl actividadService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        Categoria categoriaTest = Categoria.CULTURA; 
        Destino destinoTest = new Destino(); 

        Actividad actividad1 = new Actividad("Montaña Rusa", "Una emocionante montaña rusa.", categoriaTest, "Calle 123", destinoTest);
        Actividad actividad2 = new Actividad("Casa Embrujada", "Una casa con sorpresas escalofriantes.", categoriaTest, "Calle 456", destinoTest);

        when(actividadRepository.findAll()).thenReturn(Arrays.asList(actividad1, actividad2));

        List<Actividad> actividades = actividadService.findAll();

        assertNotNull(actividades);
        assertEquals(2, actividades.size(), "Debería haber 2 actividades en la lista");
        assertEquals("Montaña Rusa", actividades.get(0).getNombre(), "El nombre de la primera actividad debe coincidir");
        assertEquals("Casa Embrujada", actividades.get(1).getNombre(), "El nombre de la segunda actividad debe coincidir");

        verify(actividadRepository).findAll();
    }


    @Test
    public void testFindById() {
        Long idTest = 1L;
        Categoria categoriaTest = Categoria.DEPORTES;
        Destino destinoTest = new Destino();
        Optional<Actividad> actividadOpt = Optional.of(new Actividad("Parque Acuático", "Diversión acuática para toda la familia.", categoriaTest, "Calle 789", destinoTest));

        when(actividadRepository.findById(idTest)).thenReturn(actividadOpt);

        Optional<Actividad> result = actividadService.findById(idTest);

        assertTrue(result.isPresent(), "La actividad debe ser encontrada");
        assertEquals("Parque Acuático", result.get().getNombre(), "El nombre de la actividad debe coincidir");

        verify(actividadRepository).findById(idTest);
    }

    @Test
    public void testSave() {
        Categoria categoriaTest = Categoria.GASTRONOMIA;
        Destino destinoTest = new Destino();
        Actividad actividad = new Actividad("Feria de Comida", "Sabores del mundo en un solo lugar.", categoriaTest, "Plaza Central", destinoTest);

        when(actividadRepository.save(any(Actividad.class))).thenReturn(actividad);

        Actividad savedActividad = actividadService.save(actividad);

        assertNotNull(savedActividad, "La actividad guardada no debe ser nula");
        assertEquals("Feria de Comida", savedActividad.getNombre(), "El nombre de la actividad guardada debe coincidir");

        verify(actividadRepository).save(actividad);
    }

    @Test
    public void testDeleteById() {
        Long idTest = 1L;
        actividadService.deleteById(idTest);
        verify(actividadRepository).deleteById(idTest);
    }
    
}
