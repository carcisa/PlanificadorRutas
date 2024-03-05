package com.controller.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.controlador.auth.AuthenticationController;
import com.request.SignUpRequest;
import com.request.SigninRequest;
import com.servicio.AuthenticationService;
import com.usuarioResponse.JwtAuthenticationResponse;

public class AuthenticationControllerTest {

    @Test
    void testSignup_Correcto() {
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        
        SignUpRequest request = new SignUpRequest("John", "Doe", "john@example.com", "password");
        
        JwtAuthenticationResponse expectedResponse = new JwtAuthenticationResponse("jwtToken");
        
        when(authenticationService.signup(request)).thenReturn(expectedResponse);

        AuthenticationController controller = new AuthenticationController(authenticationService);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = controller.signup(request);

        verify(authenticationService).signup(request);
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testSignin_Correcto() {
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        
        SigninRequest request = new SigninRequest("john@example.com", "password");
        
        JwtAuthenticationResponse expectedResponse = new JwtAuthenticationResponse("jwtToken");
        
        when(authenticationService.signin(request)).thenReturn(expectedResponse);

        AuthenticationController controller = new AuthenticationController(authenticationService);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = controller.signin(request);

        verify(authenticationService).signin(request);
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
