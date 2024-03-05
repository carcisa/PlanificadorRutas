package com.repository.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void pruebaBuscarUsuarioPorNombreUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuarioPrueba");
        usuario.setEmail("usuario@example.com");
        usuario.setPassword("password000");
        usuarioRepository.save(usuario);

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByNombreUsuario("usuarioPrueba");

        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("usuarioPrueba", usuarioEncontrado.get().getNombreUsuario());
    }

    @Test
    public void pruebaExistenciaUsuarioPorNombreUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuarioExistente");
        usuario.setEmail("usuarioexistente@example.com");
        usuario.setPassword("password000");
        usuarioRepository.save(usuario);

        Boolean existe = usuarioRepository.existsByNombreUsuario("usuarioExistente");

        assertTrue(existe);
    }

    @Test
    public void pruebaBuscarUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuarioEmail");
        usuario.setEmail("usuarioemail@example.com");
        usuario.setPassword("password000");
        usuarioRepository.save(usuario);

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail("usuarioemail@example.com");

        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("usuarioemail@example.com", usuarioEncontrado.get().getEmail());
    }

    @Test
    public void pruebaExistenciaUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuarioEmailExistente");
        usuario.setEmail("existenteemail@example.com");
        usuario.setPassword("password000");
        usuarioRepository.save(usuario);

        Boolean existe = usuarioRepository.existsByEmail("existenteemail@example.com");

        assertTrue(existe);
    }

    @Test
    public void pruebaBuscarUsuarioPorNombreUsuarioNoExistente() {
        // Buscar un usuario por un nombre de usuario que no existe en la base de datos
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByNombreUsuario("NoExistente");

        // Verifica que el usuario no haya sido encontrado
        assertFalse(usuarioEncontrado.isPresent());
    }

    @Test
    public void pruebaExistenciaUsuarioPorEmailNoExistente() {
        // Verificar la existencia de un usuario por un email que no existe en la base de datos
        Boolean existe = usuarioRepository.existsByEmail("noexistente@example.com");

        // Verifica que el usuario no exista
        assertFalse(existe);
    }
    
}
