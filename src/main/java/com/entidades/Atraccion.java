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

@Entity
@Table(name = "atracciones")
public class Atraccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) 
    private Categoria categoria;

    @Column
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    @JsonBackReference
    private Destino destino;
    
	public Atraccion() {
		
	}
	

	public Atraccion(String nombre, String descripcion, Categoria categoria, String direccion,
			Destino destino) {
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

	@Override
	public String toString() {
		return "Atraccion [getId()=" + getId() + ", getNombre()=" + getNombre() + ", getDescripcion()="
				+ getDescripcion() + ", getCategoria()=" + getCategoria() + ", getDireccion()=" + getDireccion()
				+ ", getDestino()=" + getDestino() + "]";
	}


   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
