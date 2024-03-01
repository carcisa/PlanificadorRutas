package com.Controller.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.controlador.UsuarioController;
import com.entidades.Role;
import com.entidades.Usuario;
import com.servicio.UsuarioService;
import com.usuarioResponse.UsuarioDto;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    public void testGetAllUsuarios() throws Exception {
        mockMvc.perform(get("/api/usuarios/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        when(usuarioService.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsuarioByIdNotFound() throws Exception {
        when(usuarioService.findById(anyInt())).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUsuario() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail("test@example.com");
        usuarioDto.setNombreUsuario("testuser");
        usuarioDto.setPassword("password");

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"test@example.com\", \"nombreUsuario\": \"testuser\", \"password\": \"password\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("test@example.com");
        usuario.setNombreUsuario("testuser");
        usuario.setPassword("password");
        usuario.setRoles(Collections.singleton(Role.ROLE_USER));

        when(usuarioService.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"email\": \"test@example.com\", \"nombreUsuario\": \"testuser\", \"password\": \"password\", \"roles\": [\"ROLE_USER\"] }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).deleteById(anyInt());
        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());

        verify(usuarioService).deleteById(1);
    }
}
