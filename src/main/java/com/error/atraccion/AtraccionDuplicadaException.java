package com.error.atraccion;

public class AtraccionDuplicadaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AtraccionDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
