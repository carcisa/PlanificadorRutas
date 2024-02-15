package com.servicio;

import java.util.List;
import java.util.Optional;

import com.entidades.Atraccion;

public interface AtraccionService {
    List<Atraccion> findAll();
    Optional<Atraccion> findById(Long id);
    Atraccion save(Atraccion atraccion);
    void deleteById(Long id);
}
