package com.BackendChallenge.TechTrendEmporium.service;
import com.BackendChallenge.TechTrendEmporium.entity.Role;
import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.UserRepository;
import com.BackendChallenge.TechTrendEmporium.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void deleteUserTest() {
//        User user = new User();
//        user.setUsername("username");
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//        when(userRepository.findByUsername("deleted_users")).thenReturn(Optional.empty());
//
//        assertTrue(userService.deleteUser("username"));
//    }

//    @Test
//    public void updateUserTest() {
//        User user = new User();
//        user.setUsername("username");
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//
//        assertTrue(userService.updateUser("username", "newEmail", "newPassword"));
//    }

    @Test
    public void getAllUsersTest() {
        User user1 = new User();
        user1.setUsername("username1");
        User user2 = new User();
        user2.setUsername("username2");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void deleteUserTest_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertFalse(userService.deleteUser("username"));
    }

    @Test
    public void deleteUserTest_AdminUser() {
        User user = new User();
        user.setUsername("admin");
        user.setRole(Role.ADMIN);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        assertFalse(userService.deleteUser("admin"));
    }

//    @Test
//    public void deleteUserTest_DeletedUser() {
//        User user = new User();
//        user.setUsername("deleted_users");
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//
//        assertFalse(userService.deleteUser("deleted_users"));
//    }

    @Test
    public void updateUserTest_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertFalse(userService.updateUser("username", "newEmail", "newPassword"));
    }
}