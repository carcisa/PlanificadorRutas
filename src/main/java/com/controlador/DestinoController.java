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


/**
 * Controlador REST para gestionar destinos.
 * Proporciona endpoints para operaciones CRUD sobre destinos, incluyendo la gestión de atracciones asociadas.
 */
@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private final DestinoService destinoService;

    /**
     * Constructor para inyectar el servicio de destinos.
     * @param destinoService El servicio que gestiona las operaciones sobre destinos.
     */
    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    /**
     * Obtiene todos los destinos disponibles.
     * @return Una lista de destinos.
     */
    @GetMapping("/")
    public List<Destino> getAllDestinos() {
        return destinoService.findAll();
    }

    /**
     * Obtiene un destino por su identificador.
     * @param id El identificador único del destino.
     * @return ResponseEntity con el destino si se encuentra, o no encontrado (404) si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Destino> getDestinoById(@PathVariable Integer id) {
        return destinoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Obtiene las atracciones asociadas a un destino específico por el identificador del destino.
     * @param id El identificador único del destino.
     * @return ResponseEntity con una lista de atracciones si el destino existe, o vacío si no existe.
     */
    @GetMapping("/{id}/atracciones")
    public ResponseEntity<List<Atraccion>> AtraccionesGetDestinoById(@PathVariable Integer id) {
        Optional<Destino> destino = destinoService.findById(id);
        List<Atraccion> atracciones = destino.get().getAtracciones();
        return ResponseEntity.ok(atracciones);
    }

    /**
     * Crea un nuevo destino.
     * @param destino Los detalles del destino a crear.
     * @return El destino creado.
     */
    @PostMapping
    public Destino createDestino(@RequestBody Destino destino) {
        return destinoService.save(destino);
    }

    /**
     * Agrega una atracción a un destino existente.
     * @param destino_Id El identificador del destino al que se agrega la atracción.
     * @param atraccion La atracción a agregar al destino.
     * @return ResponseEntity con la atracción agregada y estado CREATED si el destino existe, o notFound si el destino no existe.
     */
    @PostMapping("/{destino_Id}/atracciones")
    public Object addAtraccionToDestino(@PathVariable Integer destino_Id, @RequestBody Atraccion atraccion) {
        return destinoService.findById(destino_Id).map(destino -> {
            destino.agregarAtraccion(atraccion);
            destinoService.save(destino);
            return new ResponseEntity<>(atraccion, HttpStatus.CREATED);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un destino existente.
     * @param id El identificador único del destino a actualizar.
     * @param destinoDetails Los nuevos detalles del destino.
     * @return ResponseEntity con el destino actualizado si se encuentra, o no encontrado (404) si no se encuentra.
     */
	
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

    
    /**
     * Elimina un destino por su identificador.
     * @param id El identificador único del destino a eliminar.
     * @return ResponseEntity con código de estado 200 si se elimina con éxito, o no encontrado (404) si no se encuentra el destino.
     */
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
