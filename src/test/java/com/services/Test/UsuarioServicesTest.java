
package com.services.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;
import com.serviceImpl.UsuarioServiceImpl;

public class UsuarioServicesTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Usuario usuario1 = new Usuario(); 
        Usuario usuario2 = new Usuario();
        List<Usuario> expectedUsuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(expectedUsuarios);
        List<Usuario> result = usuarioService.findAll();
        assertEquals(expectedUsuarios.size(), result.size(), "El número de usuarios no coincide");
    }

    @Test
    public void testFindById() {
        Integer userId = 1;
        Optional<Usuario> expectedUsuario = Optional.of(new Usuario()); 

        when(usuarioRepository.findById(userId)).thenReturn(expectedUsuario);
        Optional<Usuario> result = usuarioService.findById(userId);
        assertTrue(result.isPresent(), "El usuario no fue encontrado");
    }

    @Test
    public void testDeleteById() {
        Integer userId = 1;
        usuarioService.deleteById(userId);

        verify(usuarioRepository).deleteById(userId);
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(); 
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario result = usuarioService.save(usuario);

        assertEquals(usuario, result, "El usuario guardado no coincide");
    }

}
