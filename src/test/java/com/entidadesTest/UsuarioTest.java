package com.entidadesTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;
import com.servicio.UsuarioService;


@SpringBootTest
public class UsuarioTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testAgregarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("UsuarioTest");
        usuario.setEmail("usuariotest@example.com");
        usuario.setPassword("password000");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario creado = usuarioService.save(usuario);

        verify(usuarioRepository).save(usuario);
        assert(creado.getNombreUsuario().equals(usuario.getNombreUsuario()));
    }
    }
