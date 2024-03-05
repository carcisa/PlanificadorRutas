
package com.controller.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.controlador.UsuarioController;
import com.entidades.Usuario;
import com.servicio.UsuarioService;
import com.usuarioResponse.UsuarioDto;

@SpringBootTest
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

  
    @Test
    void getAllUsuariosTest() {
        List<Usuario> usuarioList = new ArrayList<>();
        when(usuarioService.findAll()).thenReturn(usuarioList);

        ResponseEntity<List<Usuario>> response = usuarioController.getAllUsuarios();

        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getUsuarioByIdTest() {
        Integer id = 1;
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("sdfs@sdf.com");
        when(usuarioService.findById(id)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void createUsuarioTest() {
        UsuarioDto usuarioDto = new UsuarioDto();
        Usuario usuario = new Usuario();
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.createUsuario(usuarioDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateUsuarioTest() {
        Integer id = 1;
        Usuario usuarioDetails = new Usuario();
        when(usuarioService.findById(id)).thenReturn(Optional.of(new Usuario()));
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioDetails);

        ResponseEntity<Usuario> response = usuarioController.updateUsuario(id, usuarioDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDetails, response.getBody());
    }

    @Test
    void deleteUsuarioTest() {
        Integer id = 1;
        when(usuarioService.findById(id)).thenReturn(Optional.of(new Usuario()));

        ResponseEntity<Void> response = usuarioController.deleteUsuario(id);

        verify(usuarioService, times(1)).deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}



