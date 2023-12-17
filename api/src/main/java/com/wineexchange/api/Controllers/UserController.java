package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.User;
import com.wineexchange.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user");
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
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestBody UUID id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user");
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }
}
