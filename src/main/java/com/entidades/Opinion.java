package com.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "opiniones")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer idUsuario;

    @Column(nullable = false)
    private Integer idAtraccion;

    @Column(nullable = false)
    private Integer puntuacion;

    @Column(length = 1000)
    private String comentario;

    @Column(nullable = false)
    private LocalDate fecha;

    public Opinion() {
    }

    public Opinion(Integer idUsuario, Integer idAtraccion, Integer puntuacion, String comentario, LocalDate fecha) {
        this.idUsuario = idUsuario;
        this.idAtraccion = idAtraccion;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

  

    public Integer getIdAtraccion() {
		return idAtraccion;
	}

	public void setIdAtraccion(Integer idAtraccion) {
		this.idAtraccion = idAtraccion;
	}

	public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Opinion{" +
               "id=" + id +
               ", idUsuario=" + idUsuario +
               ", idAtraccion=" + idAtraccion +
               ", puntuacion=" + puntuacion +
               ", comentario='" + comentario + '\'' +
               ", fecha=" + fecha +
               '}';
    }
}
