package com.error.atraccion;


public class AtraccionNoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AtraccionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
