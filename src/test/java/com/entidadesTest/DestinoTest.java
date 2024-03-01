package com.entidadesTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.entidades.Destino;
import com.repositorio.DestinoRepository;
import com.servicio.DestinoService;

@SpringBootTest
public class DestinoTest {

	@MockBean
	private DestinoRepository destinoRepository;

	@InjectMocks
	private DestinoService destinoService;

	@Test
	public void testAgregarDestino() {
		Destino destino = new Destino();
		destino.setNombre("DestinoTest");
		destino.setDescripcion("Descripción test");
		destino.setAtracciones(new ArrayList<>());

		when(destinoRepository.save(any(Destino.class))).thenReturn(destino);

		Destino creado = destinoService.save(destino);

		verify(destinoRepository).save(destino);
		assert (creado.getNombre().equals(destino.getNombre()));
	}

}
