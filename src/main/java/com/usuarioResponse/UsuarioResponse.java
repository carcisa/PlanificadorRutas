package com.usuarioResponse;

/**
 * Clase que representa la respuesta de usuario utilizada para transferir la información del usuario.
 * Incluye el primer nombre, apellido, correo electrónico y el rol del usuario.
 */
public class UsuarioResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String rol;

    /**
     * Constructor para crear una respuesta de usuario con todos los detalles necesarios.
     * 
     * @param firstName El primer nombre del usuario.
     * @param lastName El apellido del usuario.
     * @param email El correo electrónico del usuario.
     * @param rol El rol del usuario dentro del sistema.
     */
    public UsuarioResponse(String firstName, String lastName, String email, String rol) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rol = rol;
    }

    /**
     * Obtiene el primer nombre del usuario.
     * 
     * @return El primer nombre del usuario.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el primer nombre del usuario.
     * 
     * @param firstName El primer nombre a establecer.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return El apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     * 
     * @param lastName El apellido a establecer.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email El correo electrónico a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el rol del usuario dentro del sistema.
     * 
     * @return El rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario dentro del sistema.
     * 
     * @param rol El rol a establecer.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
