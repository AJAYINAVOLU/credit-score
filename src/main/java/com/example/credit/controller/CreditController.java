package com.example.credit.controller;


import com.example.credit.model.User;
import com.example.credit.repository.CreditRepository;
import com.example.credit.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CreditController {

  @Autowired
  CreditRepository creditRepository;
  private final UserService userService;

  public CreditController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/user/{userId}")
  public ResponseEntity<User> findByUserId(@PathVariable("userId") int userId){
    Optional<User> userData = creditRepository.findById(userId);
    if (userData.isPresent()){
      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/creditscore/{userId}")
  public ResponseEntity<Integer> getCreditScore(@PathVariable("userId") int userId){
    Optional<User> userData = creditRepository.findById(userId);
    if (userData.isPresent()){
      return new ResponseEntity<>(userData.get().getCreditScore(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/users")
  public ResponseEntity<User> addUser(@RequestBody User userDto) {
    User createdUser = userService.createUser(userDto);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }



}

