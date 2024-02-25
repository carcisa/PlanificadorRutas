package com.configuracion;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.entidades.Atraccion;
import com.entidades.Categoria;
import com.entidades.Destino;

import com.entidades.Role;
import com.entidades.Usuario;
import com.github.javafaker.Faker;
import com.repositorio.AtraccionRepository;
import com.repositorio.DestinoRepository;

import com.repositorio.UsuarioRepository;

@Component
public class InicializarDatos implements CommandLineRunner {

	private final Faker faker = new Faker(new Locale("es"));

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private DestinoRepository destinoRepository;
	@Autowired
	private AtraccionRepository atraccionRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

//		List<Usuario> usuarios = generarUsuarios(50);
//		usuarioRepository.saveAll(usuarios);
//		

		// Generar e insertar usuarios aleatorios
		List<Usuario> usuarios = generarUsuarios(50);
		for (Usuario usuario : usuarios) {
			if (usuarioRepository.findByEmail(usuario.getEmail()).isEmpty()) {
				usuarioRepository.save(usuario);
			}
		}

		List<Destino> destinos = generarDestinos(50);
		destinoRepository.saveAll(destinos);

		List<Atraccion> atracciones = generarAtracciones(50, destinos);
		atraccionRepository.saveAll(atracciones);

//		List<Opinion> opiniones = generarOpiniones(50, usuarios, atracciones);
//		opinionRepository.saveAll(opiniones);

		// Insertar usuarios fijos
		insertarUsuarioSiNoExiste("admin@example.com", "admin", "admin1234", Role.ROLE_ADMIN);
		insertarUsuarioSiNoExiste("user@example.com", "user", "user1234", Role.ROLE_USER);

	}
//
//		if (usuarioRepository.findByEmail("admin@example.com").isEmpty()) {
//			// Crear un usuario administrador fijo
//			Usuario admin = new Usuario();
//			admin.setNombreUsuario("admin");
//			admin.setEmail("admin@example.com");
//			admin.setPassword(passwordEncoder.encode("admin1234"));
//			admin.setRoles(Collections.singleton(Role.ROLE_ADMIN)); // Asignar el rol de administrador
//			usuarioRepository.save(admin);
//		}
//
//		if (usuarioRepository.findByEmail("user@example.com").isEmpty()) {
//			// Crear un usuario normal fijo
//			Usuario user = new Usuario();
//			user.setNombreUsuario("user");
//			user.setEmail("user@example.com");
//			user.setPassword(passwordEncoder.encode("user1234"));
//			user.setRoles(Collections.singleton(Role.ROLE_USER)); // Asignar el rol de usuario
//			usuarioRepository.save(user);
//		}
//	}

	private void insertarUsuarioSiNoExiste(String email, String nombreUsuario, String contraseña, Role role) {
		usuarioRepository.findByEmail(email).orElseGet(() -> {
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setNombreUsuario(nombreUsuario);
			usuario.setPassword(passwordEncoder.encode(contraseña));
			usuario.setRoles(Collections.singleton(role));
			return usuarioRepository.save(usuario);
		});
	}

	public List<Usuario> generarUsuarios(int cantidad) {
		List<Usuario> usuarios = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			Usuario usuario = new Usuario(faker.name().username(), faker.internet().emailAddress(),
					faker.internet().password());
			usuarios.add(usuario);
		}
		return usuarios;
	}

	public List<Destino> generarDestinos(int cantidad) {
		List<Destino> destinos = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			Destino destino = new Destino(faker.address().cityName(), faker.lorem().sentence());
			destinos.add(destino);
		}
		return destinos;
	}

	public List<Atraccion> generarAtracciones(int cantidad, List<Destino> destinos) {
	    List<Atraccion> atracciones = new ArrayList<>();
	    Categoria[] categorias = Categoria.values(); 
	    for (int i = 0; i < cantidad; i++) {
	        Atraccion atraccion = new Atraccion(
	                faker.lorem().word(),
	                faker.lorem().sentence(),
	                categorias[faker.random().nextInt(categorias.length)], 
	                faker.address().streetAddress(),
	                destinos.get(faker.random().nextInt(destinos.size()))
	        );
	        atracciones.add(atraccion);
	    }
	    return atracciones;
	}


//	public List<Opinion> generarOpiniones(int cantidad, List<Usuario> usuarios, List<Atraccion> atracciones) {
//		List<Opinion> opiniones = new ArrayList<>();
//		for (int i = 0; i < cantidad; i++) {
//			Opinion opinion = new Opinion(usuarios.get(faker.random().nextInt(usuarios.size())).getId(),
//					atracciones.get(faker.random().nextInt(atracciones.size())).getId(),
//					faker.number().numberBetween(1, 5), faker.lorem().sentence(),
//					faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
//
//			);
//			opiniones.add(opinion);
//		}
//		return opiniones;
//	}

}
