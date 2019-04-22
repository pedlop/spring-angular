// package com.plop.springular;

// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;

// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import java.util.Arrays;

// import com.plop.springular.model.User;
// import com.plop.springular.model.Vehicle;
// import com.plop.springular.repository.UserRepository;
// import com.plop.springular.repository.VehicleRepository;

// @Component
// @Slf4j
// public class DataInitializer implements CommandLineRunner {

// @Autowired
// VehicleRepository vehicles;

// @Autowired
// UserRepository users;

// @Autowired
// PasswordEncoder passwordEncoder;

// @Override
// public void run(String... args) throws Exception {
// log.debug("initializing vehicles data...");
// Arrays.asList("moto", "car").forEach(v ->
// this.vehicles.saveAndFlush(Vehicle.builder().name(v).build()));

// log.debug("printing all vehicles...");
// this.vehicles.findAll().forEach(v -> log.debug(" Vehicle :" + v.toString()));

// this.users.save(User.builder().username("user").email("user@test.com")
// .password(this.passwordEncoder.encode("password")).roles(Arrays.asList("ROLE_USER")).build());

// this.users.save(User.builder().username("admin").email("admin@test.com")
// .password(this.passwordEncoder.encode("password")).roles(Arrays.asList("ROLE_USER",
// "ROLE_ADMIN")).build());

// log.debug("printing all users...");
// this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
// }
// }