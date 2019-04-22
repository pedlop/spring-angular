package com.plop.springular.security;

import com.plop.springular.model.User;
import com.plop.springular.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository users;

  public CustomUserDetailsService(UserRepository users) {
    this.users = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.users.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Username " + username + " not found");
    }
    return user;
    // return this.users.findByUsername(username)
    // .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + "
    // not found"));
  }
}