package com.entidadesTest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.entidades.Usuario;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UsuarioTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nombreUsuarioNoPuedeEstarVacio() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("");
        usuario.setEmail("usuario@example.com");
        usuario.setPassword("ContraseñaSegura123");

        var violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El nombre de usuario no puede estar vacío")));
    }

    @Test
    public void emailDebeSerValido() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuario");
        usuario.setEmail("emailNoValido");
        usuario.setPassword("ContraseñaSegura123");

        var violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Formato de correo electrónico inválido")));
    }

    @Test
    public void passwordDebeTenerMinimo8Caracteres() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuario");
        usuario.setEmail("usuario@example.com");
        usuario.setPassword("Corta");

        var violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("La contraseña debe tener al menos 8 caracteres")));
    }

    @Test
    public void usuarioValido() {
        Usuario usuario = new Usuario("usuario", "usuario@example.com", "ContraseñaSegura123");
        var violations = validator.validate(usuario);
        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación cuando todos los campos son válidos");
    }
}
