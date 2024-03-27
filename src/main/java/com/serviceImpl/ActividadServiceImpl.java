package com.serviceImpl;


import org.springframework.stereotype.Service;

import com.entidades.Actividad;
import com.repositorio.ActividadRepository;
import com.servicio.ActividadService;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;

   
    public ActividadServiceImpl(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    @Override
    public List<Actividad> findAll() {
        return actividadRepository.findAll();
    }

    @Override
    public Optional<Actividad> findById(Long id) {
        return actividadRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        actividadRepository.deleteById(id);
    }

	@Override
	public Actividad save(Actividad actividad) {
		return actividadRepository.save(actividad);
	}
}
