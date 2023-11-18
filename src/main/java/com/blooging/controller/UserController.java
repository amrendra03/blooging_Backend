package com.blooging.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blooging.payloads.ApiResponse;
import com.blooging.payloads.UserDto;
import com.blooging.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	// User Create
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createUserDto =this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	// Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer id){
		UserDto  updatedUser = this.userService.updateUser(userDto, id);
		return ResponseEntity.ok(updatedUser);
	}
	// Delete User
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer id){
		this.userService.deleteUser(id);
		return new ResponseEntity(new ApiResponse("Deletd",true),HttpStatus.OK);
	}
	// Get user  list
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllU(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	//Get Single User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId")Integer id){
		return ResponseEntity.ok(this.userService.getUserById(id));
	}

}
