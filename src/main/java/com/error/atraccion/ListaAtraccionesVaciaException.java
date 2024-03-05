package com.error.atraccion;

public class ListaAtraccionesVaciaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ListaAtraccionesVaciaException(String message) {
        super(message);
    }
}
