package com.ditrraacademy.travelagency.core.user;

import com.ditrraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> createUser( User user){
		if (user.getName() ==null)
		return new ResponseEntity<>(new ErrorResponseModel("user name required"), HttpStatus.BAD_REQUEST);

		if (user.getName().length() < 3)
			return new  ResponseEntity<>(new ErrorResponseModel("Wrong user name"),HttpStatus.BAD_REQUEST);


		if (user.getAge() <= 0)
			return new  ResponseEntity<>(new ErrorResponseModel("Wrong user age"),HttpStatus.BAD_REQUEST);

		User userdatabase= userRepository.save(user);
		return new ResponseEntity<>(userdatabase,HttpStatus.OK);
	}


	public List<User> getAllUsers (){
		List<User> userList = userRepository.findAll();
		return userList;

	}

	public ResponseEntity<?> getUser(int id){

		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()){
			ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
			return  new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST);
		}
		User user = userOptional.get();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	public ResponseEntity<?>  updateUser(int id,User updateUser){
		Optional<User> userOptional = userRepository.findById(id);

		if(!userOptional.isPresent()){
			ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
			return  new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST);}


		User userDatabase = userOptional.get();

		if(updateUser.getName()!=null) {
			if (updateUser.getName().length() < 3)
				return new ResponseEntity<>(new ErrorResponseModel("user name required"), HttpStatus.BAD_REQUEST);

			userDatabase.setName(updateUser.getName());
		}

		if(updateUser.getAge()!=null) {

			if (updateUser.getAge() <= 0)
				return new ResponseEntity<>(new ErrorResponseModel("Wrong user age"), HttpStatus.BAD_REQUEST);
			userDatabase.setAge(updateUser.getAge());}


		User userupdate = userRepository.save(userDatabase);
		return new ResponseEntity<>(userupdate,HttpStatus.OK);
	}

	public ResponseEntity<?>  removeUser(int id){
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
			return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
		}

		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
