package com.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * La clase Actividad representa una entidad de actividad turística mapeada a la
 * tabla 'actividades' en la base de datos. Incluye detalles como el nombre,
 * descripción, categoría, dirección, y su destino asociado.
 */
@Entity
@Table(name = "actividad")
public class Actividad {

	/**
	 * Identificador único de la actividad, generado automáticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre de la actividad. No puede ser nulo.
	 */
	@NotBlank(message = "El nombre no puede estar vacío")
	@Column(nullable = false)
	private String nombre;

	/**
	 * Descripción detallada de la actividad. Máximo 1000 caracteres.
	 */
	@Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
	@Column(length = 1000)
	private String descripcion;

	/**
	 * Categoría de la actividad, representada por un enum. No puede ser nulo.
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "La categoría no puede ser nula")
	private Categoria categoria;

	/**
	 * Dirección física de la actividad.
	 */
	@Column
	private String direccion;

	/**
	 * Destino asociado a la actividad. Se utiliza la anotación JsonBackReference
	 * para manejar la referencia inversa y evitar la recursión infinita en la
	 * serialización JSON.
	 */
	@NotNull(message = "Debe existir un destino asociado")
	@ManyToOne
	@JoinColumn(name = "destino_id", nullable = false)
	@JsonBackReference
	private Destino destino;

	/**
	 * Constructor por defecto.
	 * 
	 */
	public Actividad() {

	}

	/**
	 * Constructor con todos los atributos de la clase.
	 * 
	 * @param nombre      Nombre de la actividad.
	 * @param descripcion Descripción de la actividad.
	 * @param categoria   Categoría de la actividad.
	 * @param direccion   Dirección de la actividad.
	 * @param destino     Destino asociado a la actividad.
	 */
	public Actividad(String nombre, String descripcion, Categoria categoria, String direccion, Destino destino) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.direccion = direccion;
		this.destino = destino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	/**
	 * Representación en cadena de la entidad Actividad, incluyendo todos sus
	 * atributos.
	 * 
	 * @return Cadena que representa la entidad Actividad.
	 */
	@Override
	public String toString() {
		return "Actividad [getId()=" + getId() + ", getNombre()=" + getNombre() + ", getDescripcion()="
				+ getDescripcion() + ", getCategoria()=" + getCategoria() + ", getDireccion()=" + getDireccion()
				+ ", getDestino()=" + getDestino() + "]";
	}

}
