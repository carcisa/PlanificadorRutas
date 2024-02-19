package com.serviceImpl;


import org.springframework.stereotype.Service;

import com.entidades.Atraccion;
import com.repositorio.AtraccionRepository;
import com.servicio.AtraccionService;

import java.util.List;
import java.util.Optional;

@Service
public class AtraccionServiceImpl implements AtraccionService {

    private final AtraccionRepository atraccionRepository;

   
    public AtraccionServiceImpl(AtraccionRepository atraccionRepository) {
        this.atraccionRepository = atraccionRepository;
    }

    @Override
    public List<Atraccion> findAll() {
        return atraccionRepository.findAll();
    }

    @Override
    public Optional<Atraccion> findById(Long id) {
        return atraccionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        atraccionRepository.deleteById(id);
    }

	@Override
	public Atraccion save(Atraccion atraccion) {
		return atraccionRepository.save(atraccion);
	}
}
