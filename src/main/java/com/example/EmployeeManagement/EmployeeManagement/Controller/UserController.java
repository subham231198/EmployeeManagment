package com.example.EmployeeManagement.EmployeeManagement.Controller;

import com.example.EmployeeManagement.EmployeeManagement.CommonValidatons.Common;
import com.example.EmployeeManagement.EmployeeManagement.Entity.User;
import com.example.EmployeeManagement.EmployeeManagement.Exceptions.EmptyRecordException;
import com.example.EmployeeManagement.EmployeeManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/EmployeeManagement")
public class UserController
{
    @Autowired
    UserService service;

    Common com = new Common();

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            String validationResult = service.createUser(user);
            if (validationResult == null) {
                return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(validationResult, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllUser")
    public String getAllUsers(User user)
    {
        return service.getAllUser(user);
    }

    @GetMapping("/getId/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") Long user_id)
    {
        Optional<User> getUser = service.getUserById(user_id);
        if(getUser.isPresent())
        {
            return new ResponseEntity<>(getUser.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEmail/{user_email}")
    public ResponseEntity<User> getUserByEmailAddress(@PathVariable("user_email") String email)
    {
        if(com.isEmailValidated(email)) {
            Optional<User> getUserEmail = service.getUserByEmail(email);
            if (getUserEmail.isPresent()) {
                return new ResponseEntity<>(getUserEmail.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getPhone/{user_phone}")
    public ResponseEntity<User> getUserByPhoneNumber(@PathVariable("user_phone") String phone)
    {
        if(com.isPhoneValid(phone))
        {
            Optional<User> getUserPhone = service.getUserByPhone(phone);
            if (getUserPhone.isPresent()) {
                return new ResponseEntity<>(getUserPhone.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateUser/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") Long user_id, @RequestBody User user)
    {
        Optional<User> getUser = service.updateUser(user, user_id);
        if(getUser.isPresent())
        {
            return new ResponseEntity<>(getUser.get(), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUser() {
        service.deleteAllUser();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete_user/{user_id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("user_id") Long user_id) {
        try {
            String validationResult = service.deleteUserById(user_id);
            if (validationResult == null) {
                return new ResponseEntity<>("User has been successfully removed from records!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(validationResult, HttpStatus.BAD_REQUEST);
            }
        } catch (EmptyRecordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
