package com.entidades;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "destinos")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @OneToMany(mappedBy = "destino")
    @JsonBackReference
    private List<Atraccion> atracciones;

    public Destino() {
    }
    

	public Destino(Integer id, String nombre, String descripcion, List<Atraccion> atracciones) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.atracciones = atracciones;
	}


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


	

   
  
}
