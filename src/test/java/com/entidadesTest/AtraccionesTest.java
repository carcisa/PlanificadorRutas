package com.entidadesTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.entidades.Atraccion;
import com.entidades.Categoria;
import com.entidades.Destino;
import com.repositorio.AtraccionRepository;
import com.repositorio.DestinoRepository;
import com.servicio.AtraccionService;
import com.servicio.DestinoService;


@SpringBootTest
public class AtraccionesTest {

    @MockBean
    private AtraccionRepository atraccionRepository;

    @InjectMocks
    private AtraccionService atraccionService;

    @Test
    public void testAgregarAtraccion() {
        Atraccion atraccion = new Atraccion();
        atraccion.setNombre("AtraccionTest");
        atraccion.setCategoria(Categoria.NATURALEZA);
        atraccion.setDescripcion("Descripcion Test");
        atraccion.setDireccion("Dirección test");

        when(atraccionRepository.save(any(Atraccion.class))).thenReturn(atraccion);

        Atraccion creado = atraccionService.save(atraccion);

        verify(atraccionRepository).save(atraccion);
        assert(creado.getNombre().equals(atraccion.getNombre()));
    }
    }
