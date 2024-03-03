package com.entidadesTest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.entidades.Atraccion;
import com.entidades.Categoria;
import com.entidades.Destino;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class AtraccionesTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nombreNoPuedeEstarVacio() {
        Atraccion atraccion = new Atraccion();
        atraccion.setNombre("");
        atraccion.setDescripcion("Descripción válida");
        atraccion.setCategoria(Categoria.CULTURA); 
        atraccion.setDireccion("Dirección válida");
        atraccion.setDestino(new Destino("Destino Válido", "Descripción válida"));

        var violations = validator.validate(atraccion);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El nombre no puede estar vacío")));
    }

    @Test
    public void descripcionLargaInvalida() {
        String descripcionLarga = "a".repeat(1001); // Crea una cadena de 1001 caracteres
        Atraccion atraccion = new Atraccion("Atracción Válida", descripcionLarga, Categoria.CULTURA, "Dirección válida", new Destino());
        var violations = validator.validate(atraccion);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("La descripción no puede superar los 1000 caracteres")));
    }

    @Test
    public void categoriaNoPuedeSerNula() {
        Atraccion atraccion = new Atraccion("Atracción Válida", "Descripción válida", null, "Dirección válida", new Destino());
        var violations = validator.validate(atraccion);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("La categoría no puede ser nula")));
    }

    @Test
    public void destinoNoPuedeSerNulo() {
        Atraccion atraccion = new Atraccion("Atracción Válida", "Descripción válida", Categoria.CULTURA, "Dirección válida", null);
        var violations = validator.validate(atraccion);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Debe existir un destino asociado")));
    }

    @Test
    public void atraccionValida() {
        Atraccion atraccion = new Atraccion("Atracción Válida", "Descripción válida", Categoria.CULTURA, "Dirección válida", new Destino("Destino Válido", "Descripción válida"));
        var violations = validator.validate(atraccion);
        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación cuando todos los campos son válidos");
    }
}

