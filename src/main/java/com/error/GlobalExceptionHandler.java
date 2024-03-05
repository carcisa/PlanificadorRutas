package com.error;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.error.atraccion.AtraccionDatosNoValidosException;
import com.error.atraccion.AtraccionDuplicadaException;
import com.error.atraccion.AtraccionNoEncontradaException;
import com.error.atraccion.ListaAtraccionesVaciaException;
import com.error.destino.DestinoDatosNoValidosException;
import com.error.destino.DestinoDuplicadoException;
import com.error.destino.DestinoNoEncontradoException;
import com.error.destino.ListaDestinoVaciaException;
import com.error.usuario.ListaUsuariosVaciaException;
import com.error.usuario.UsuarioDatosNoValidosException;
import com.error.usuario.UsuarioDuplicadoException;
import com.error.usuario.UsuarioNoEncontradoException;



@ControllerAdvice
public class GlobalExceptionHandler {
	/**
     * #############################################
     * #       BAD_REQUEST. 400 EXCEPTION          ##
     * ##############################################
     */
	 @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<ErrorDetailsResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		 ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
	            new Date(),
	            ex.getMessage(),
	            request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }
    
    
    /**
     * ####################################################
     * #       "Lista de Usuarios vacía" Exception 404    ##
     * #####################################################
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(ListaUsuariosVaciaException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarListaUsuariosVaciaException(ListaUsuariosVaciaException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    /**
     * ####################################################
     * #       "Lista de Destino vacía" Exception 404    ##
     * #####################################################
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(ListaDestinoVaciaException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarListaDestinoVaciaException(ListaDestinoVaciaException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    /**
     * ####################################################
     * #       "Lista de Atracciones vacía" Exception 404    ##
     * #####################################################
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(ListaAtraccionesVaciaException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarListaAtraccionesVaciaException(ListaAtraccionesVaciaException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    /**
     * ####################################################
     * #       Excepción 404: "Usuario no encontrado"   ##
     * ####################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarUsuarioNoEncontradoException(UsuarioNoEncontradoException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * ####################################################
     * #       Excepción 404: "Destino no encontrado"     ##
     * ####################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(DestinoNoEncontradoException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarDestinoNoEncontradoException(DestinoNoEncontradoException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * ####################################################
     * #       Excepción 404: "Atracción no encontrada"   ##
     * ####################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(AtraccionNoEncontradaException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarAtraccionNoEncontradaException(AtraccionNoEncontradaException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

  
    
    /**
     * ##########################################################
     * #       Excepción 409: "Destino duplicado"              ##
     * ##########################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(DestinoDuplicadoException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarDestinoDuplicadoException(DestinoDuplicadoException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    
    /**
     * ##########################################################
     * #       Excepción 409: "Usuario duplicado"              ##
     * ##########################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(UsuarioDuplicadoException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarUsuarioDuplicadoException(UsuarioDuplicadoException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    
    /**
     * ##########################################################
     * #       Excepción 409: "Atracción duplicada"            ##
     * ##########################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(AtraccionDuplicadaException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarAtraccionDuplicadaException(AtraccionDuplicadaException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    /**
     * ####################################################
     * #       Excepción 400: "Datos de usuario no válidos"       ##
     * ####################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(UsuarioDatosNoValidosException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarUsuarioDatosNoValidosException(UsuarioDatosNoValidosException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * ####################################################
     * #       Excepción 400: "Datos de destino no válidos"       ##
     * ####################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(DestinoDatosNoValidosException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarDestinoDatosNoValidosException(DestinoDatosNoValidosException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    
    /**
     * ####################################################
     * #       Excepción 400: "Datos de atracción no válidos"     ##
     * ####################################################
     * 
     * @param ex La excepción que fue lanzada.
     * @param request La solicitud web actual.
     * @return Un objeto ResponseEntity que contiene los detalles del error.
     */
    @ExceptionHandler(AtraccionDatosNoValidosException.class)
    public ResponseEntity<ErrorDetailsResponse> manejarAtraccionDatosNoValidosException(AtraccionDatosNoValidosException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    
    
    /**
     * ####################################################
     * #     "Error interno del servidor" Exception 500   ##
     * #####################################################
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsResponse> handleGlobalException(Exception ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
            new Date(),
            "Error interno del servidor",
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * ####################################################
     * #     "Acceso denegado" Exception 403             ##
     * #####################################################
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetailsResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
            new Date(),
            "Acceso denegado",
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

}