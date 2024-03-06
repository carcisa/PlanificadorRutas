package com.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.entidades.Atraccion;
import com.entidades.Destino;

import jakarta.validation.Valid;

public interface DestinoService {
    List<Destino> findAll();
    Optional<Destino> findById(Integer id);
    Destino save(Destino destino);
    void deleteById(Integer id);
   
}
