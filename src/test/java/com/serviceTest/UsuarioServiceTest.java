package com.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;
import com.serviceImpl.UsuarioServiceImpl;


@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioServicio;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
    }

    @Test
    public void testGuardarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario guardado = usuarioServicio.save(new Usuario());
        assertNotNull(guardado);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    public void testEliminarUsuario() {
        doNothing().when(usuarioRepository).deleteById(anyInt());
        usuarioServicio.deleteById(1);
        verify(usuarioRepository).deleteById(anyInt());
    }

    @Test
    public void testBuscarUsuarioPorId() {
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        Optional<Usuario> encontrado = usuarioServicio.findById(1);
        assertTrue(encontrado.isPresent());
        verify(usuarioRepository).findById(anyInt());
    }

    @Test
    public void testListarTodosLosUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Collections.singletonList(usuario));
        List<Usuario> usuarios = usuarioServicio.findAll();
        assertEquals(1, usuarios.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    public void testBuscarUsuarioPorNombreUsuario() {
        when(usuarioRepository.findByNombreUsuario(any(String.class))).thenReturn(Optional.of(usuario));
        Optional<Usuario> encontrado = usuarioServicio.findByNombreUsuario("nombreUsuario");
        assertTrue(encontrado.isPresent());
        assertEquals("nombreUsuario", encontrado.get().getNombreUsuario());
        verify(usuarioRepository).findByNombreUsuario(any(String.class));
    }
}
