package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.User;
import com.wineexchange.api.Exceptions.UserNotFoundException;
import com.wineexchange.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            if (userService.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with this email already exists");
            }

            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user: " + e.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        try {
            Iterable<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID id) {
        try {
            Optional<User> userOptional = userService.getUserById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return ResponseEntity.ok(user);
            } else {
                throw new UserNotFoundException("User not found with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user", e);
        }
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        try {
            Optional<User> userOptional = userService.getUserByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return ResponseEntity.ok(user);
            } else {
                throw new UserNotFoundException("User not found with email: " + email);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user", e);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            Optional<User> userOptional = userService.getUserById(user.getId());
            if (userOptional.isPresent()) {
                userService.updateUser(user);
                return ResponseEntity.ok("User updated successfully");
            } else {
                throw new UserNotFoundException("User not found with id: " + user.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        try {
            Optional<User> userOptional = userService.getUserById(id);
            if (userOptional.isPresent()) {
                userService.deleteUser(id);
                return ResponseEntity.ok("User deleted successfully");
            } else {
                throw new UserNotFoundException("User not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }
}
