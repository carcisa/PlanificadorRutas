package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entidades.Atraccion;
import com.servicio.AtraccionService;
import com.servicio.DestinoService;

import java.util.List;

@RestController
@RequestMapping("/api/atracciones")
public class AtraccionController {

	@Autowired
	private final AtraccionService atraccionService;

	@Autowired
	private DestinoService destinoService;

	public AtraccionController(AtraccionService atraccionService) {
		this.atraccionService = atraccionService;
	}

	@GetMapping
	public List<Atraccion> getAllAtracciones() {
		return atraccionService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Atraccion> getAtraccionById(@PathVariable Long id) {
		return atraccionService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

    @PostMapping
    public Atraccion createAtraccion(@RequestBody Atraccion atraccion) {
        return atraccionService.save(atraccion);
    }

//    @PostMapping("/{id}/atracciones")
//    public ResponseEntity<Atraccion> addAtraccionToDestino(@PathVariable Integer id, @RequestBody Atraccion atraccion) {
//        return destinoService.findById(id.intValue()) 
//                .map(destino -> {
//                    atraccion.setDestino(destino); 
//                    Atraccion nuevaAtraccion = atraccionService.save(atraccion); 
//                    return ResponseEntity.ok(nuevaAtraccion); 
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build()); 
//    }

	



	@PutMapping("/{id}")
	public ResponseEntity<Atraccion> updateAtraccion(@PathVariable Long id, @RequestBody Atraccion atraccionDetails) {
		return atraccionService.findById(id).map(atraccion -> {
			atraccion.setNombre(atraccionDetails.getNombre());
			atraccion.setDescripcion(atraccionDetails.getDescripcion());
			atraccion.setCategoria(atraccionDetails.getCategoria());
			atraccion.setDireccion(atraccionDetails.getDireccion());
			Atraccion updatedAtraccion = atraccionService.save(atraccion);
			return ResponseEntity.ok(updatedAtraccion);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAtraccion(@PathVariable Long id) {
		return atraccionService.findById(id).map(atraccion -> {
			atraccionService.deleteById(id);
			return ResponseEntity.ok().<Void>build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
