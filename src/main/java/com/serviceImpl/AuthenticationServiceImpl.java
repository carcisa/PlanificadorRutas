package com.serviceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;
import com.request.SignUpRequest;
import com.request.SigninRequest;
import com.servicio.AuthenticationService;
import com.servicio.JwtService;
import com.usuarioResponse.JwtAuthenticationResponse;

import lombok.Builder;


/**
 * Implementación del servicio de autenticación, gestionando el registro y el inicio de sesión de los usuarios.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor para inyección de dependencias requeridas para la autenticación.
     *
     * @param userRepository Repositorio de usuarios para operaciones de base de datos.
     * @param passwordEncoder Codificador de contraseñas para asegurar las contraseñas de los usuarios.
     * @param jwtService Servicio para la generación de tokens JWT.
     * @param authenticationManager Gestor de autenticación para Spring Security.
     */
    public AuthenticationServiceImpl(UsuarioRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        // Corrige la forma de construir el objeto 'User'
        Usuario user = new Usuario();
        user.setNombreUsuario(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(com.entidades.Role.ROLE_USER); 
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        // Maneja la autenticación
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
       // SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
