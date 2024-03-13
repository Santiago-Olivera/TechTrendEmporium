package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.Auth.*;
import com.BackendChallenge.TechTrendEmporium.JWT.JwtService;
import com.BackendChallenge.TechTrendEmporium.entity.Role;
import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

        @InjectMocks
        private AuthService authService;
        @InjectMocks
        private AuthController authController;
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
                request.setEmail("testAuth1@mail.com");
                request.setPassword("testAuth1");
                AuthResponse authResponse = new AuthResponse();
                ResponseEntity<Object> responseEntity = new ResponseEntity<>(authResponse, HttpStatus.OK);
                when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));
                when(authService.login(request)).thenReturn(responseEntity);
        }

        @Test
        public void loginFailedTest() {
                LoginRequest request = new LoginRequest();
                request.setEmail("testAuth1@mail.com");
                request.setPassword("testAuth1");
                User user = new User();
                user.setEmail("test@mail.com");
                user.setUsername("test");
                user.setPassword("test");
                user.setLogged(true);
                when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
//                NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> authService.login(request));
        }

//        @Test
//        public void registerShopperTest() {
//                RegisterShopperRequest request = new RegisterShopperRequest();
//                request.setEmail("testAuth1@mail.com");
//                request.setUsername("testAuth1");
//                request.setPassword("testAuth1");
//                ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
//                when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));
//                User user = new User();
//                user.setEmail(request.getEmail());
//                user.setUsername(request.getUsername());
//                user.setPassword(request.getPassword());
//                user.setRole(Role.SHOPPER);
//        }
}