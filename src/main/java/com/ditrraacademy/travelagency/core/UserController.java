package com.ditrraacademy.travelagency.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/user")
    public void createUser(@RequestBody User user){
        userRepository.save(user);
    }
    @GetMapping("/users")
    public List<User> getAllUsers (){
       List<User> userList = userRepository.findAll();
       return userList;
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){

        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return user;
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User updateUser){
        Optional<User> userOptional = userRepository.findById(id);
        User databaserUser = userOptional.get();
        databaserUser.setName(updateUser.getName());
        databaserUser.setAge(updateUser.getAge());
        userRepository.save(databaserUser);

    }
}
