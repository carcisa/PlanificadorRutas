package com.repositorio;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entidades.Destino;


@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {

	Optional<Destino> findById(Integer id);

	void deleteById(Integer id);
	
}
