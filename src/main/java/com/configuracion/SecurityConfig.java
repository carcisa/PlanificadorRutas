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

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private UsuarioService usuarioService;

   

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(request -> request.requestMatchers("/authenticate/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/usuarios/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/usuarios/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
				
				.requestMatchers(HttpMethod.GET, "/api/destinos/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/destinos/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/destinos/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/destinos/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
				
				.requestMatchers(HttpMethod.GET, "/api/atracciones/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/destino/{destino_Id}/atracciones").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/atracciones/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/atracciones/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
				
				.requestMatchers(HttpMethod.GET, "/api/opiniones/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.POST, "/api/opiniones/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.PUT, "/api/opiniones/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.requestMatchers(HttpMethod.DELETE, "/api/opiniones/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
				
				
				.requestMatchers(HttpMethod.GET, "/api/admin/**").hasAnyAuthority(Role.ROLE_ADMIN.toString())
				.anyRequest()
				.authenticated())
		.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
return http.build();
}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}