package com.error.atraccion;

public class AtraccionDatosNoValidosException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AtraccionDatosNoValidosException(String mensaje) {
        super(mensaje);
    }
}
