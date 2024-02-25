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

/**
 * Servicio que implementa la lógica de negocio para la gestión de usuarios.
 * Proporciona la implementación de las operaciones CRUD para usuarios y la carga de detalles de usuarios para la autenticación.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     * @param usuarioRepository El repositorio para realizar operaciones de base de datos sobre usuarios.
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Recupera todos los usuarios existentes.
     * @return Una lista de todos los usuarios.
     */
    @Transactional
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por su identificador.
     * @param id El identificador del usuario.
     * @return Un Optional que contiene el usuario encontrado o un Optional vacío si no se encuentra.
     */
    @Transactional
    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Elimina un usuario por su identificador.
     * @param id El identificador del usuario a eliminar.
     */
    @Transactional
    @Override
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
    
    /**
     * Guarda un usuario en la base de datos. Si el usuario es nuevo, lo crea; si el usuario ya existe, actualiza sus datos.
     * @param usuario El usuario a guardar.
     * @return El usuario guardado.
     */
    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param nombreUsuario El nombre de usuario del usuario a buscar.
     * @return Un Optional que contiene el usuario encontrado o un Optional vacío si no se encuentra.
     */
    @Override
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        // Implementación de ejemplo, podría modificarse para buscar realmente por nombre de usuario.
        return Optional.empty();
    }

    /**
     * Proporciona una implementación de UserDetailsService para cargar los detalles de un usuario por su correo electrónico.
     * @return Una instancia de UserDetailsService.
     */
    @Override
    public UserDetailsService userDetailsService() {
        return email -> usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + email));
    }
}

