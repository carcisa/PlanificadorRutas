package com.servicio;

import java.util.List;
import java.util.Optional;

import com.entidades.Destino;

public interface DestinoService {
    List<Destino> findAll();
    Optional<Destino> findById(Integer id);
    Destino save(Destino destino);
    void deleteById(Integer id);
}
