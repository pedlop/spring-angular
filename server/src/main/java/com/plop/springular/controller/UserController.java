package com.plop.springular.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.plop.springular.model.SuccessResponse;
import com.plop.springular.model.User;
import com.plop.springular.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/create")
  @ResponseBody
  public ResponseEntity create(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    // System.out.println(user.getRoles());
    // userRepository.save(User.builder().id(user.getId()).username(user.getUsername())
    // .password(passwordEncoder.encode(user.getPassword())).email(user.getEmail()).roles(user.getRoles()).build());
    userRepository.save(user);

    SuccessResponse response = new SuccessResponse(200, "User " + user.getUsername() + " created.");
    return ResponseEntity.ok().body(response);
    // return userRepository.save(user);
  }
}