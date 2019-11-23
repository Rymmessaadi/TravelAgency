package com.ditrraacademy.travelagency.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;


    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userServices.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers (){
       return userServices.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id){

        return userServices.getUser(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?>  updateUser(@PathVariable int id, @RequestBody User updateUser){
        return userServices.updateUser(id,updateUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?>  removeUser(@PathVariable int id) {
        return userServices.removeUser(id);
    }
}
