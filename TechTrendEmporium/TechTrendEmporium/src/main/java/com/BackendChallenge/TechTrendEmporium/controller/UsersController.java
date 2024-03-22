package com.BackendChallenge.TechTrendEmporium.controller;

import com.BackendChallenge.TechTrendEmporium.controller.Requests.UserRequest;
import com.BackendChallenge.TechTrendEmporium.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(value = "delete")
    public ResponseEntity<?> deleteUser(Long userId) {
        Boolean user = userService.deleteUser(userId);
        if (user) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.badRequest().body("ERROR: User not deleted");
        }
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request) {
        Boolean user = userService.updateUser(request.getUser_id(), request.getUsername(), request.getPassword(), request.getEmail());
        if (user) {
            return ResponseEntity.ok("User updated");
        } else {
            return ResponseEntity.badRequest().body("ERROR: User not updated");
        }
    }
}
