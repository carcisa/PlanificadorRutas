package dto;

import java.util.Set;



public class UsuarioDTO {
	 private Long id;
	    private String username;
	    private Set<String> roles;

	    public UsuarioDTO() {
	    }

	    public UsuarioDTO(Long id, String username, Set<String> roles) {
	        this.id = id;
	        this.username = username;
	        this.roles = roles;
	    }

	    // Getters y setters

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public Set<String> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<String> roles) {
	        this.roles = roles;
	    }

	    // Método toString opcional para facilitar el debug
	    @Override
	    public String toString() {
	        return "UsuarioDTO{" +
	               "id=" + id +
	               ", username='" + username + '\'' +
	               ", roles=" + roles +
	               '}';
	    }
}
