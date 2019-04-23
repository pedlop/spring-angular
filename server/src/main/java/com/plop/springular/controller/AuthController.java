package com.plop.springular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.plop.springular.model.AuthenticationRequest;
import com.plop.springular.model.User;
import com.plop.springular.repository.UserRepository;
import com.plop.springular.security.jwt.JwtTokenProvider;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Autowired
  UserRepository users;

  @PostMapping("/signin")
  public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
    System.out.println(data.getPassword());
    try {
      String username = data.getUsername();
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword(), new ArrayList<>()));

      User user = this.users.findByUsername(username);
      if (user == null) {
        throw new UsernameNotFoundException("Username " + username + "not found");
      }

      Long userId = user.getId();
      String token = jwtTokenProvider.createToken(username, user.getRoles(), userId);
      // .orElseThrow(() -> new UsernameNotFoundException("Username " + username +
      // "not found")).getRoles());

      Map<Object, Object> model = new HashMap<>();
      model.put("uid", userId);
      model.put("username", username);
      model.put("token", token);
      return ok(model);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username/password supplied");
    }
  }
}