package com.blooging.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blooging.payloads.Test;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

        System.out.println("---" + userDto);
        UserDto createUserDto = null;
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach((error) ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        createUserDto = this.userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer id) {
        UserDto updatedUser = this.userService.updateUser(userDto, id);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete User
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity(new ApiResponse("Deletd", true), HttpStatus.OK);
    }

    // Get user  list
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllU() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //Get Single User
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }


    @PostMapping("/test")
    public ResponseEntity<Test> test(@Valid @RequestBody UserDto persion, BindingResult bindingResult) {

        System.out.println("calling  test ...");
        System.out.println(persion);

        Test test = new Test();
        test.setName("ok");
        test.setAge(1);

        System.out.println("-----X---  " + bindingResult);
        if (bindingResult.hasErrors()) {
            test.setName("ERROR");
            return new ResponseEntity<>(test, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

}
