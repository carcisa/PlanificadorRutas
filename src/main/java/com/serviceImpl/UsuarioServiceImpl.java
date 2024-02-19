package com.serviceImpl;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entidades.Usuario;
import com.repositorio.UsuarioRepository;
import com.servicio.UsuarioService;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
    
    @Transactional
	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
		return Optional.empty();
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
	}

	
}
