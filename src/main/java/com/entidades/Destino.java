package com.entidades;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * La clase Destino representa un destino turístico, mapeado a la tabla 'destinos' en la base de datos.
 * Contiene información básica sobre el destino, como su nombre y descripción, además de una lista de atracciones asociadas.
 */
@Entity
@Table(name = "destinos")
public class Destino {

    /**
     * Identificador único del destino, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del destino. No puede ser nulo.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Descripción breve del destino. Máximo 500 caracteres.
     */
    @Column(length = 500)
    private String descripcion;

    /**
     * Lista de atracciones asociadas al destino. La relación es bidireccional y se maneja automáticamente
     * la referencia inversa en la serialización JSON para evitar la recursión infinita.
     */
    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Atraccion> atracciones;

    /**
     * Constructor por defecto.
     */
    public Destino() {
    }

    /**
     * Constructor completo con todos los atributos, incluyendo la lista de atracciones.
     * @param id Identificador del destino.
     * @param nombre Nombre del destino.
     * @param descripcion Descripción del destino.
     * @param atracciones Lista de atracciones asociadas al destino.
     */
    public Destino(Integer id, String nombre, String descripcion, List<Atraccion> atracciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.atracciones = atracciones;
    }

    /**
     * Constructor sobrecargado sin el identificador, útil para la creación de nuevos destinos.
     * @param nombre Nombre del destino.
     * @param descripcion Descripción del destino.
     */
    public Destino(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
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



	public List<Atraccion> getAtracciones() {
		return atracciones;
	}



	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}
	
	 /**
     * Agrega una atracción a la lista de atracciones del destino y establece la relación inversa.
     * @param atraccion La atracción a agregar.
     */
    public void agregarAtraccion(Atraccion atraccion) {
        atraccion.setDestino(this);
        atracciones.add(atraccion);
    }

	


	

   
  
}
