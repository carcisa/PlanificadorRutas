package com.servicio;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.entidades.Usuario;

@Service
public interface JwtService {
	String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);

}
