package com.serviceImpl;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.entidades.Atraccion;
import com.entidades.Destino;
import com.repositorio.DestinoRepository;
import com.servicio.DestinoService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoServiceImpl implements DestinoService {

    private final DestinoRepository destinoRepository;


    public DestinoServiceImpl(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    @Override
    public List<Destino> findAll() {
        return destinoRepository.findAll();
    }

    @Override
    public Optional<Destino> findById(Integer id) {
        return destinoRepository.findById(id);
    }


    public void deleteById(Integer id) {
        destinoRepository.deleteById(id);
    }

	@Override
	public Destino save(Destino destino) {
		return destinoRepository.save(destino);
	}

	
}
