package com.servicio;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioPersonalizadoDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    
	@Override
    @Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		
		Usuario usuario = usuarioRepository.findByNombreUsuario(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

	       return new User(usuario.getNombreUsuario(), usuario.getPassword(), 
	                usuario.getRoles().stream()
	                        .map(rol -> new SimpleGrantedAuthority(rol.toString()))
	                        .collect(Collectors.toList()));
	}


}



