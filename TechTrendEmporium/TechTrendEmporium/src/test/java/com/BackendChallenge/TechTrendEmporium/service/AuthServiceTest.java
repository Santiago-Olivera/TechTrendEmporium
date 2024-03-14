package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.Auth.*;
import com.BackendChallenge.TechTrendEmporium.JWT.JwtService;
import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.UnknownServiceException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;


    @Test
    public void loginTest() {
        LoginRequest request = new LoginRequest();
//        request.setEmail("testAuth1@mail.com");
        request.setPassword("testAuth1");
        AuthResponse authResponse = new AuthResponse();
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(authResponse, HttpStatus.OK);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLogged(false);
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
        ResponseEntity<Object> response = authService.login(request);
        assertEquals(responseEntity, response);
        user.setLogged(true);
        ResponseEntity<Object> response2 = authService.login(request);
        ResponseEntity<Object> response3 = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already logged in");
        assertEquals(response3, response2);
    }

}