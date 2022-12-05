package com.hostmdy.ppm.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.ppm.domain.User;
import com.hostmdy.ppm.service.MapValidationErrorService;
import com.hostmdy.ppm.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	private final MapValidationErrorService mapErrorService;

	public UserController(UserService userService, MapValidationErrorService mapErrorService) {
		super();
		this.userService = userService;
		this.mapErrorService = mapErrorService;
	}
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable String username){
		Optional<User> userOptional = userService.findByUserName(username);
		
		if(userOptional.isEmpty())
			return new ResponseEntity<String>("User with username= "+username+" is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(userOptional.get(),HttpStatus.OK);
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<User> userOptional = userService.findById(id);
		
		if(userOptional.isEmpty())
			return new ResponseEntity<String>("user with with id="+id+" is not found",HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<User>(userOptional.get(),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user,BindingResult result){
		ResponseEntity<?> resposeErrorObject = mapErrorService.validate(result);
		if(resposeErrorObject != null)
			return resposeErrorObject;
		
		 User createUser = userService.saveOrUpdateUser(user);
		 
		return new ResponseEntity<User>(createUser,HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		userService.deleteById(id);
		return new ResponseEntity<String>("Delete id = "+id+" is deleted",HttpStatus.OK);
		
	}

}
