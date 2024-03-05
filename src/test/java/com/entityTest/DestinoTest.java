package com.entityTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.entidades.Atraccion;
import com.entidades.Destino;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class DestinoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nombreNoPuedeEstarVacio() {
        Destino destino = new Destino("", "Descripción válida");
        var violations = validator.validate(destino);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El nombre del destino no puede estar vacío")));
    }

    @Test
    public void descripcionLargaInvalida() {
        String descripcionLarga = "a".repeat(501); // Crea una cadena de 501 caracteres
        Destino destino = new Destino("Destino Válido", descripcionLarga);
        var violations = validator.validate(destino);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("La descripción no puede superar los 500 caracteres")));
    }

    @Test
    public void destinoValido() {
        Destino destino = new Destino("Destino Válido", "Descripción válida");
        var violations = validator.validate(destino);
        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación cuando todos los campos son válidos");
    }

   
}

