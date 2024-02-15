package com.servicio;

import java.util.List;
import java.util.Optional;

import com.entidades.Destino;

public interface DestinoService {
    List<Destino> findAll();
    Optional<Destino> findById(Long id);
    Destino save(Destino destino);
    void deleteById(Long id);
}
