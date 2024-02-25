package com.controlador.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.request.SignUpRequest;
import com.request.SigninRequest;
import com.servicio.AuthenticationService;
import com.usuarioResponse.JwtAuthenticationResponse;

/**
 * Controlador para manejar la autenticación de usuarios.
 * Proporciona endpoints para el registro (signup) y el inicio de sesión (signin) de usuarios.
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Constructor para inyección de dependencias del servicio de autenticación.
     * 
     * @param authenticationService El servicio para manejar la autenticación de usuarios.
     */
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint para registrar un nuevo usuario en el sistema.
     * 
     * @param request Los detalles del usuario para el registro.
     * @return JwtAuthenticationResponse que contiene el token JWT generado tras el registro exitoso.
     */
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        JwtAuthenticationResponse response = authenticationService.signup(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para iniciar sesión en el sistema.
     * 
     * @param request Los detalles del usuario para el inicio de sesión.
     * @return JwtAuthenticationResponse que contiene el token JWT generado tras el inicio de sesión exitoso.
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        JwtAuthenticationResponse response = authenticationService.signin(request);
        return ResponseEntity.ok(response);
    }
}
