package com.plop.springular.controller;

import com.plop.springular.model.User;
import com.plop.springular.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserRepository userRepository;
  private BCryptPasswordEncoder bcrypt;

  UserController(UserRepository userRepository, BCryptPasswordEncoder bcrypt) {
    this.userRepository = userRepository;
    this.bcrypt = bcrypt;
  }

  @PostMapping("/create")
  public User create(@RequestBody User user) {
    user.setPassword(bcrypt.encode(user.getPassword()));
    return userRepository.save(user);
  }
}