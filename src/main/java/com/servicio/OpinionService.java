package com.servicio;

import java.util.List;
import java.util.Optional;

import com.entidades.Opinion;

public interface OpinionService {
    List<Opinion> findAll();
    Optional<Opinion> findById(Long id);
    Opinion save(Opinion opinion);
    void deleteById(Long id);
}
