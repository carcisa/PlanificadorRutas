package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entidades.Actividad;
import com.entidades.Destino;
import com.error.actividad.ActividadNoEncontradaException;
import com.error.actividad.ListaActividadesVaciaException;
import com.error.destino.DestinoNoEncontradoException;
import com.error.usuario.ListaUsuariosVaciaException;
import com.servicio.ActividadService;
import com.servicio.DestinoService;

import jakarta.validation.Valid;

import java.util.List;

/**
 * Controlador REST para gestionar actividades. Proporciona endpoints para
 * operaciones CRUD sobre actividades.
 */
@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

	private final ActividadService actividadService;

	@Autowired
	private DestinoService destinoService;

	/**
	 * Constructor para inyectar el servicio de actividades.
	 * 
	 * @param actividadService El servicio que gestiona las operaciones sobre
	 *                         actividades.
	 */
	public ActividadController(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

	/**
	 * Obtiene todas las actividades disponibles.
	 * 
	 * @return Una lista de actividades.
	 */
	@GetMapping
	public List<Actividad> getAllActividades() {
		List<Actividad> actividades = actividadService.findAll();
		if (actividades.isEmpty()) {
			throw new ListaActividadesVaciaException("El listado de atraciones está vacío");
		}
		return actividadService.findAll();
	}

	/**
	 * Obtiene una actividad por su identificador.
	 * 
	 * @param id El identificador único de la actividad.
	 * @return ResponseEntity con la actividad si se encuentra, o no encontrado
	 *         (404) si no se encuentra.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Actividad> getActividadById(@PathVariable Long id) {
		return actividadService.findById(id).map(ResponseEntity::ok)
				.orElseThrow(() ->  new ActividadNoEncontradaException("La actividad no existe" + id));
	}

	/**
	 * Crea una nueva actividad.
	 * 
	 * @param actividad Los detalles de la actividad a crear.
	 * @return La actividad creada.
	 */
	@PostMapping
	public Actividad createActividad(@Valid @RequestBody Actividad actividad) {
		return actividadService.save(actividad);
	}

	/**
	 * Actualiza una actividad existente.
	 * 
	 * @param id               El identificador único de la actividad a actualizar.
	 * @param actividadDetails Los nuevos detalles de la actividad.
	 * @return ResponseEntity con la actividad actualizada si se encuentra, o no
	 *         encontrado (404) si no se encuentra.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Actividad> updateActividad(@Valid @PathVariable Long id,
			@RequestBody Actividad actividadDetails) {
		return actividadService.findById(id).map(actividad -> {
			actividad.setNombre(actividadDetails.getNombre());
			actividad.setDescripcion(actividadDetails.getDescripcion());
			actividad.setCategoria(actividadDetails.getCategoria());
			actividad.setDireccion(actividadDetails.getDireccion());
			Actividad updatedActividad = actividadService.save(actividad);
			return ResponseEntity.ok(updatedActividad);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Elimina una actividad por su identificador.
	 * 
	 * @param id El identificador único de la actividad a eliminar.
	 * @return ResponseEntity con código de estado 200 si se elimina con éxito, o no
	 *         encontrado (404) si no se encuentra la actividad.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteActividad(@PathVariable Long id) {
		return actividadService.findById(id).map(actividad -> {
			actividadService.deleteById(id);
			return ResponseEntity.ok().<Void>build();
		}).orElseGet(() -> ResponseEntity.notFound().build());

	}
}
