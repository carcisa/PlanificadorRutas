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

/**
 * Clase de configuración que implementa CommandLineRunner para inicializar datos en la base de datos al arranque de la aplicación.
 * Utiliza Faker para generar datos aleatorios para usuarios, destinos y atracciones.
 */
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

    /**
     * Método ejecutado automáticamente al iniciar la aplicación.
     * Genera y guarda datos aleatorios de usuarios, destinos y atracciones en la base de datos.
     * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
     */
	@Override
	public void run(String... args) throws Exception {


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



		// Insertar usuarios fijos
		insertarUsuarioSiNoExiste("admin@example.com", "admin", "admin1234", Role.ROLE_ADMIN);
		insertarUsuarioSiNoExiste("user@example.com", "user", "user1234", Role.ROLE_USER);

	}

	
	 /**
     * Inserta un nuevo usuario en la base de datos si no existe uno con el mismo correo electrónico.
     * @param email El correo electrónico del usuario.
     * @param nombreUsuario El nombre de usuario.
     * @param contraseña La contraseña del usuario.
     * @param role El rol de seguridad asignado al usuario.
     */
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
	

	/**
     * Genera una lista de usuarios aleatorios.
     * @param cantidad La cantidad de usuarios a generar.
     * @return Una lista de objetos Usuario.
     */
	public List<Usuario> generarUsuarios(int cantidad) {
		List<Usuario> usuarios = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			Usuario usuario = new Usuario(faker.name().username(), faker.internet().emailAddress(),
					faker.internet().password());
			usuarios.add(usuario);
		}
		return usuarios;
	}

	
	/**
     * Genera una lista de destinos aleatorios.
     * @param cantidad La cantidad de destinos a generar.
     * @return Una lista de objetos Destino.
     */
	public List<Destino> generarDestinos(int cantidad) {
		List<Destino> destinos = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			Destino destino = new Destino(faker.address().cityName(), faker.lorem().sentence());
			destinos.add(destino);
		}
		return destinos;
	}

	/**
     * Genera una lista de atracciones aleatorias asociadas a destinos.
     * @param cantidad La cantidad de atracciones a generar.
     * @param destinos La lista de destinos a los que pueden estar asociadas las atracciones.
     * @return Una lista de objetos Atraccion.
     */
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


}
