package com.ditrraacademy.travelagency.core;

import com.ditrraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if (user.getName() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user name required"),HttpStatus.BAD_REQUEST);

        if (user.getName().length() < 3)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user name"),HttpStatus.BAD_REQUEST);


        if (user.getAge() <= 0)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user age"),HttpStatus.BAD_REQUEST);

        User userdatabase= userRepository.save(user);
        return new ResponseEntity<>(userdatabase,HttpStatus.OK);
    }
    @GetMapping("/users")
    public List<User> getAllUsers (){
       List<User> userList = userRepository.findAll();
       return userList;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id){

        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
            return  new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?>  updateUser(@PathVariable int id, @RequestBody User updateUser){
        Optional<User> userOptional = userRepository.findById(id);
        User userDatabase = userOptional.get();
        userDatabase.setName(updateUser.getName());
        userDatabase.setAge(updateUser.getAge());
        if (userDatabase.getName() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user name required"),HttpStatus.BAD_REQUEST);

        if (userDatabase.getName().length() < 3)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user name"),HttpStatus.BAD_REQUEST);

        if (userDatabase.getAge() <= 0)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user age"),HttpStatus.BAD_REQUEST);


        User userupdate = userRepository.save(userDatabase);
        return new ResponseEntity<>(userupdate,HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public  void removeUser(@PathVariable int id){

        userRepository.deleteById(id);
    }
}
