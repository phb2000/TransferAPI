package com.project.transferapi.interfaces.inbound.http.controller;

import com.project.transferapi.application.AuthenticateUser;
import com.project.transferapi.domain.entity.Authentication;
import com.project.transferapi.interfaces.inbound.http.dto.AuthenticationRequest;
import com.project.transferapi.interfaces.inbound.http.dto.AuthenticationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

   @InjectMocks
   LoginController loginController;

   @Mock
   AuthenticateUser authenticateUser;

   @Test
   void shouldLogin() {
      AuthenticationRequest authenticationRequest = new AuthenticationRequest();
      authenticationRequest.setEmail("email");
      authenticationRequest.setPassword("password");
      when(authenticateUser.invoke("email", "password")).thenReturn(Authentication.builder()
                      .accessToken("token")
                      .refreshToken("refresh")

              .build());

      ResponseEntity<AuthenticationResponse> response = loginController.authenticate(authenticationRequest);

      Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
      Assertions.assertEquals("token", response.getBody().getAccessToken());
      Assertions.assertEquals("refresh", response.getBody().getRefreshToken());

   }
}
