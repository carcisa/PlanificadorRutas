package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidades.Opinion;
import com.repositorio.OpinionRepository;
import com.servicio.OpinionService;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;

    @Autowired
    public OpinionServiceImpl(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    @Override
    public List<Opinion> findAll() {
        return opinionRepository.findAll();
    }

    @Override
    public Optional<Opinion> findById(Long id) {
        return opinionRepository.findById(id);
    }

  

    @Override
    public void deleteById(Long id) {
        opinionRepository.deleteById(id);
    }

	@Override
	public Opinion save(Opinion opinion) {
		 return opinionRepository.save(opinion);
	}
}
