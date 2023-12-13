package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.User;
import com.wineexchange.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
    @GetMapping("/getAllUsers")
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/getUserById")
    public User getUserById(@RequestBody String id){
        return userService.getUserById(id);
    }
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody String id){
        userService.deleteUser(id);
    }
}
