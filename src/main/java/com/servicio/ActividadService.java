package com.servicio;

import java.util.List;
import java.util.Optional;

import com.entidades.Actividad;

public interface ActividadService {
    List<Actividad> findAll();
    Optional<Actividad> findById(Long id);
    Actividad save(Actividad actividad);
    void deleteById(Long id);
}
