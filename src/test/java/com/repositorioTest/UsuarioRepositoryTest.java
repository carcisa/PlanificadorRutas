package com.repositorioTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void pruebaBuscarUsuarioPorNombreUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("UsuarioPrueba");
        usuario.setEmail("usuario@example.com");
        usuario.setPassword("contraseña");
        usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findByNombreUsuario("UsuarioPrueba");

        assertTrue(resultado.isPresent());
        assertEquals("UsuarioPrueba", resultado.get().getNombreUsuario());
    }

    @Test
    public void pruebaExistenciaPorNombreUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("UsuarioUnico");
        usuario.setEmail("unico@example.com");
        usuario.setPassword("contraseña123");
        usuarioRepository.save(usuario);

        Boolean existe = usuarioRepository.existsByNombreUsuario("UsuarioUnico");

        assertTrue(existe);
    }

    @Test
    public void pruebaBuscarUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("UsuarioEmail");
        usuario.setEmail("email@example.com");
        usuario.setPassword("passwordEmail");
        usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findByEmail("email@example.com");

        assertTrue(resultado.isPresent());
        assertEquals("email@example.com", resultado.get().getEmail());
    }

    @Test
    public void pruebaExistenciaPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("UsuarioEmailUnico");
        usuario.setEmail("emailunico@example.com");
        usuario.setPassword("passwordUnico");
        usuarioRepository.save(usuario);

        Boolean existe = usuarioRepository.existsByEmail("emailunico@example.com");

        assertTrue(existe);
    }
}
