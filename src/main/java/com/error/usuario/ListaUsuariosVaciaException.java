package com.error.usuario;

public class ListaUsuariosVaciaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ListaUsuariosVaciaException(String message) {
		super(message);
	}
}
