package com.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.entidades.Role;
import com.servicio.UsuarioService;

/**
 * Clase de configuración de seguridad para la aplicación Spring Boot. Define la
 * configuración de seguridad para las solicitudes HTTP, los proveedores de
 * autenticación y los filtros de autenticación JWT.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Configura la cadena de filtros de seguridad para solicitudes HTTP. Define las
	 * políticas de acceso para las diferentes URL de la aplicación y configura la
	 * gestión de sesiones.
	 *
	 * @param http Objeto HttpSecurity para configurar aspectos de seguridad web.
	 * @return La cadena de filtros de seguridad configurada.
	 * @throws Exception si ocurre un error en la configuración.
	 */
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request -> request
				.requestMatchers("/authenticate/**").permitAll().requestMatchers(HttpMethod.GET, "/api/usuarios/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/usuarios/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/usuarios/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())

				.requestMatchers(HttpMethod.GET, "/api/destinos/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/destinos/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/destinos/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/destinos/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())

				.requestMatchers(HttpMethod.GET, "/api/atracciones/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/destino/{destino_Id}/atracciones")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/atracciones/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/atracciones/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())

				.requestMatchers(HttpMethod.GET, "/api/opiniones/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/opiniones/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/opiniones/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/opiniones/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())

				.requestMatchers(HttpMethod.GET, "/api/admin/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
				.anyRequest().authenticated())
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
	 * Bean para el cifrado de contraseñas. Utiliza BCryptPasswordEncoder para el
	 * cifrado de contraseñas en la aplicación.
	 *
	 * @return Un PasswordEncoder que utiliza BCrypt para el cifrado de contraseñas.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Bean para el proveedor de autenticación. Configura un proveedor de
	 * autenticación personalizado que utiliza el servicio de detalles de usuario y
	 * el codificador de contraseñas.
	 *
	 * @return Un AuthenticationProvider que utiliza DaoAuthenticationProvider para
	 *         gestionar la autenticación.
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
     * Bean para el gestor de autenticación.
     * Obtiene y configura el AuthenticationManager basado en la configuración de autenticación global.
     *
     * @param authenticationConfiguration La configuración de autenticación de la aplicación.
     * @return Un AuthenticationManager para gestionar los procesos de autenticación.
     * @throws Exception si ocurre un error al obtener el AuthenticationManager.
     */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}