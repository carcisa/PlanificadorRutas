package com.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entidades.Opinion;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
	
}
