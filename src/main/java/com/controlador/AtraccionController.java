package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entidades.Atraccion;
import com.servicio.AtraccionService;
import com.servicio.DestinoService;

import java.util.List;

/**
 * Controlador REST para gestionar atracciones. Proporciona endpoints para
 * operaciones CRUD sobre atracciones.
 */
@RestController
@RequestMapping("/api/atracciones")
public class AtraccionController {

	private final AtraccionService atraccionService;

	@Autowired
	private DestinoService destinoService;

	/**
	 * Constructor para inyectar el servicio de atracciones.
	 * 
	 * @param atraccionService El servicio que gestiona las operaciones sobre
	 *                         atracciones.
	 */
	public AtraccionController(AtraccionService atraccionService) {
		this.atraccionService = atraccionService;
	}

	
	 /**
     * Obtiene todas las atracciones disponibles.
     * @return Una lista de atracciones.
     */
	@GetMapping
	public List<Atraccion> getAllAtracciones() {
		return atraccionService.findAll();
	}

	/**
     * Obtiene una atracción por su identificador.
     * @param id El identificador único de la atracción.
     * @return ResponseEntity con la atracción si se encuentra, o no encontrado (404) si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Atraccion> getAtraccionById(@PathVariable Long id) {
        return atraccionService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva atracción.
     * @param atraccion Los detalles de la atracción a crear.
     * @return La atracción creada.
     */
    @PostMapping
    public Atraccion createAtraccion(@RequestBody Atraccion atraccion) {
        return atraccionService.save(atraccion);
    }

    /**
     * Actualiza una atracción existente.
     * @param id El identificador único de la atracción a actualizar.
     * @param atraccionDetails Los nuevos detalles de la atracción.
     * @return ResponseEntity con la atracción actualizada si se encuentra, o no encontrado (404) si no se encuentra.
     */
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

    /**
     * Elimina una atracción por su identificador.
     * @param id El identificador único de la atracción a eliminar.
     * @return ResponseEntity con código de estado 200 si se elimina con éxito, o no encontrado (404) si no se encuentra la atracción.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtraccion(@PathVariable Long id) {
        return atraccionService.findById(id).map(atraccion -> {
            atraccionService.deleteById(id);
            return ResponseEntity.ok().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }
}

