package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidades.Destino;
import com.repositorio.DestinoRepository;
import com.servicio.DestinoService;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoServiceImpl implements DestinoService {

    private final DestinoRepository destinoRepository;

    @Autowired
    public DestinoServiceImpl(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    @Override
    public List<Destino> findAll() {
        return destinoRepository.findAll();
    }

    @Override
    public Optional<Destino> findById(Long id) {
        return destinoRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        destinoRepository.deleteById(id);
    }

	@Override
	public Destino save(Destino destino) {
		return destinoRepository.save(destino);
	}
}
