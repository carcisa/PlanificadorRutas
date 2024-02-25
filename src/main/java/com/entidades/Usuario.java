package com.entidades;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * La clase Usuario representa un usuario de la aplicación, implementando la interfaz UserDetails para integrarse con Spring Security.
 * Mapea la entidad a la tabla 'usuarios' en la base de datos y gestiona los roles de seguridad.
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del usuario, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre de usuario, único y no nulo.
     */
    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    /**
     * Correo electrónico del usuario, único y no nulo.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Contraseña del usuario, no nula.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Roles de seguridad asignados al usuario, representados por el enum Role.
     * Se cargan con estrategia EAGER para disponibilidad inmediata.
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_roles")
    @Column(name = "rol")
    private Set<Role> roles;
    
    /**
     * Constructor por defecto.
     */
    public Usuario() {
    }

    /**
     * Constructor para crear un nuevo usuario con nombre de usuario, correo electrónico y contraseña.
     * @param nombreUsuario El nombre de usuario, debe ser único.
     * @param correoElectronico El correo electrónico del usuario, debe ser único.
     * @param password La contraseña del usuario.
     */
    public Usuario(String nombreUsuario, String correoElectronico, String password) {
        this.nombreUsuario = nombreUsuario;
        this.email = correoElectronico;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

   

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String contraseña) {
        this.password = contraseña;
    }
    
   

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roleUser) {
		this.roles = roleUser;
	}

	@Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", nombreUsuario='" + nombreUsuario + '\'' +
               ", email='" + email + '\'' +
               ", contraseña='" + password + '\'' +
               '}';
    }

	/**
     * Método para obtener las autoridades de seguridad del usuario, convertidas de roles a GrantedAuthority.
     * @return Colección de GrantedAuthority representando los roles del usuario.
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = roles.stream()
		        .map(role -> new SimpleGrantedAuthority(role.name()))
		        .collect(Collectors.toList());
		    
		    return authorities;	
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

	
}
