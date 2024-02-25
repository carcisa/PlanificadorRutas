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

/**
 * La clase Atraccion representa una entidad de atracción turística mapeada a la tabla 'atracciones' en la base de datos.
 * Incluye detalles como el nombre, descripción, categoría, dirección, y su destino asociado.
 */
@Entity
@Table(name = "atracciones")
public class Atraccion {

    /**
     * Identificador único de la atracción, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la atracción. No puede ser nulo.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Descripción detallada de la atracción. Máximo 1000 caracteres.
     */
    @Column(length = 1000)
    private String descripcion;

    /**
     * Categoría de la atracción, representada por un enum. No puede ser nulo.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING) 
    private Categoria categoria;

    /**
     * Dirección física de la atracción.
     */
    @Column
    private String direccion;

    /**
     * Destino asociado a la atracción. Se utiliza la anotación JsonBackReference para manejar la referencia inversa
     * y evitar la recursión infinita en la serialización JSON.
     */
    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    @JsonBackReference
    private Destino destino;
    
    /**
     * Constructor por defecto.
     */
    public Atraccion() {
    }
    
    /**
     * Constructor con todos los atributos de la clase.
     * @param nombre Nombre de la atracción.
     * @param descripcion Descripción de la atracción.
     * @param categoria Categoría de la atracción.
     * @param direccion Dirección de la atracción.
     * @param destino Destino asociado a la atracción.
     */
    public Atraccion(String nombre, String descripcion, Categoria categoria, String direccion, Destino destino) {
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
     * Representación en cadena de la entidad Atraccion, incluyendo todos sus atributos.
     * @return Cadena que representa la entidad Atraccion.
     */
	@Override
	public String toString() {
		return "Atraccion [getId()=" + getId() + ", getNombre()=" + getNombre() + ", getDescripcion()="
				+ getDescripcion() + ", getCategoria()=" + getCategoria() + ", getDireccion()=" + getDireccion()
				+ ", getDestino()=" + getDestino() + "]";
	}


   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
