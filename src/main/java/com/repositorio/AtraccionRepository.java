package com.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entidades.Atraccion;

@Repository
public interface AtraccionRepository extends JpaRepository<Atraccion, Long> {
	
}
