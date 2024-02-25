package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entidades.Atraccion;
import com.entidades.Destino;
import com.servicio.AtraccionService;
import com.servicio.DestinoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

	@Autowired
    private final DestinoService destinoService;
	
	

   
    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @GetMapping("/")
    public List<Destino> getAllDestinos() {
        return destinoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> getDestinoById(@PathVariable Integer id) {
        return destinoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}/atracciones")
    public ResponseEntity< List<Atraccion>> AtraccionesGetDestinoById(@PathVariable Integer id) {
        Optional<Destino> destino = destinoService.findById(id);
        List<Atraccion> atracciones = destino.get().getAtracciones();
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
		return ResponseEntity.ok(atracciones);
    }

    @PostMapping
    public Destino createDestino(@RequestBody Destino destino) {
        return destinoService.save(destino);
    }
    
    @PostMapping("/{destino_Id}/atracciones")
	public Object addAtraccionToDestino(@PathVariable Integer destino_Id, @RequestBody Atraccion atraccion) {
		return destinoService.findById(destino_Id).map(destino -> {
			destino.agregarAtraccion(atraccion);
			destinoService.save(destino);
			return new ResponseEntity<>(atraccion, HttpStatus.CREATED);
		}).orElse(ResponseEntity.notFound().build());
	}
    
	
    @PutMapping("/{id}")
    public ResponseEntity<Destino> updateDestino(@PathVariable Integer id, @RequestBody Destino destinoDetails) {
        return destinoService.findById(id)
                .map(destino -> {
                    destino.setNombre(destinoDetails.getNombre());
                    destino.setDescripcion(destinoDetails.getDescripcion());
                    Destino updatedDestino = destinoService.save(destino);
                    return ResponseEntity.ok(updatedDestino);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestino(@PathVariable Integer id) {
        return destinoService.findById(id)
                .map(destino -> {
                    destinoService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
