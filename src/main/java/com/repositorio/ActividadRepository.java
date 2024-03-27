package com.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entidades.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
	
}
