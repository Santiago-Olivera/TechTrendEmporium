package com.BackendChallenge.TechTrendEmporium;

import com.BackendChallenge.TechTrendEmporium.Auth.*;
import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.UserRepository;
import com.BackendChallenge.TechTrendEmporium.entity.Role;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private AuthService authService;

        @InjectMocks
        private AuthController authController;

}
