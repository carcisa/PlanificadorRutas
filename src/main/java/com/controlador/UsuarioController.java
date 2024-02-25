package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.entidades.Role;
import com.entidades.Usuario;
import com.servicio.UsuarioService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar operaciones de usuarios. Proporciona endpoints
 * para realizar operaciones CRUD sobre usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	/**
	 * Constructor para inyección de dependencias del servicio de usuarios.
	 * 
	 * @param usuarioService El servicio para manejar operaciones de usuarios.
	 */
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * Obtiene todos los usuarios.
	 * 
	 * @return Lista de todos los usuarios.
	 */
	@GetMapping("/")
	public List<Usuario> getAllUsuarios() {
		return usuarioService.findAll();
	}

	/**
	 * Obtiene un usuario por su ID.
	 * 
	 * @param id El ID del usuario a buscar.
	 * @return ResponseEntity con el usuario encontrado o no encontrado (404).
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
		Optional<Usuario> usuario = usuarioService.findById(id);
		return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Crea un nuevo usuario.
	 * 
	 * @param usuario El usuario a crear.
	 * @return El usuario creado.
	 */
	@PostMapping
	public Usuario createUsuario(@RequestBody Usuario usuario) {
		usuario.setRoles(Collections.singleton(Role.ROLE_USER));
		return usuarioService.save(usuario);
	}

	/**
	 * Actualiza un usuario existente.
	 * 
	 * @param id             El ID del usuario a actualizar.
	 * @param usuarioDetails Los detalles actualizados del usuario.
	 * @return ResponseEntity con el usuario actualizado o no encontrado (404).
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		usuarioDetails.setId(id);
		Usuario updatedUsuario = usuarioService.save(usuarioDetails);
		return ResponseEntity.ok(updatedUsuario);
	}

	/**
	 * Elimina un usuario por su ID.
	 * 
	 * @param id El ID del usuario a eliminar.
	 * @return ResponseEntity con el código de estado 200 si se eliminó con éxito o
	 *         no encontrado (404) si el usuario no existe.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
		if (!usuarioService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
