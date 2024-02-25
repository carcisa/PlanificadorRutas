package com.dto;

import com.entidades.Categoria;

public class AtraccionesDto {
	
	private String nombre;
	private String descripcion;
	private Categoria categoria;
	private String dirección;
	
	
	public AtraccionesDto() {
	}
	
	public AtraccionesDto(String nombre, String descripcion, Categoria categoria, String dirección) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.dirección = dirección;
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
	public String getDirección() {
		return dirección;
	}
	public void setDirección(String dirección) {
		this.dirección = dirección;
	}
	
	

}
