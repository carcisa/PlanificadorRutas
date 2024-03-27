package com.entityTest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.entidades.Actividad;
import com.entidades.Categoria;
import com.entidades.Destino;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ActividadesTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nombreNoPuedeEstarVacio() {
        Actividad actividad = new Actividad();
        actividad.setNombre("");
        actividad.setDescripcion("Descripción válida");
        actividad.setCategoria(Categoria.CULTURA); 
        actividad.setDireccion("Dirección válida");
        actividad.setDestino(new Destino("Destino Válido", "Descripción válida"));

        var violations = validator.validate(actividad);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El nombre no puede estar vacío")));
    }

    @Test
    public void descripcionLargaInvalida() {
        String descripcionLarga = "a".repeat(1001); // Crea una cadena de 1001 caracteres
        Actividad actividad = new Actividad("Actividad Válida", descripcionLarga, Categoria.CULTURA, "Dirección válida", new Destino());
        var violations = validator.validate(actividad);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("La descripción no puede superar los 1000 caracteres")));
    }

    @Test
    public void categoriaNoPuedeSerNula() {
        Actividad actividad = new Actividad("Actividad Válida", "Descripción válida", null, "Dirección válida", new Destino());
        var violations = validator.validate(actividad);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("La categoría no puede ser nula")));
    }

    @Test
    public void destinoNoPuedeSerNulo() {
        Actividad actividad = new Actividad("Actividad Válida", "Descripción válida", Categoria.CULTURA, "Dirección válida", null);
        var violations = validator.validate(actividad);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Debe existir un destino asociado")));
    }

    @Test
    public void actividadValida() {
        Actividad actividad = new Actividad("Actividad Válida", "Descripción válida", Categoria.CULTURA, "Dirección válida", new Destino("Destino Válido", "Descripción válida"));
        var violations = validator.validate(actividad);
        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación cuando todos los campos son válidos");
    }
}

