package com.plop.springular.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import com.plop.springular.exception.VehicleNotFoundException;
import com.plop.springular.model.Vehicle;
import com.plop.springular.model.VehicleForm;
import com.plop.springular.repository.VehicleRepository;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

  private VehicleRepository vehicles;

  public VehicleController(VehicleRepository vehicles) {
    this.vehicles = vehicles;
  }

  @GetMapping("")
  public ResponseEntity all() {
    return ok(this.vehicles.findAll());
  }

  @PostMapping("")
  public ResponseEntity save(@RequestBody VehicleForm form, HttpServletRequest request) {
    Vehicle saved = this.vehicles.save(Vehicle.builder().name(form.getName()).build());
    return created(ServletUriComponentsBuilder.fromContextPath(request).path("/v1/vehicles/{id}")
        .buildAndExpand(saved.getId()).toUri()).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity get(@PathVariable("id") Long id) {
    return ok(this.vehicles.findById(id).orElseThrow(() -> new VehicleNotFoundException()));
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") Long id, @RequestBody VehicleForm form) {
    Vehicle existed = this.vehicles.findById(id).orElseThrow(() -> new VehicleNotFoundException());
    existed.setName(form.getName());

    this.vehicles.save(existed);
    return noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {
    Vehicle existed = this.vehicles.findById(id).orElseThrow(() -> new VehicleNotFoundException());
    this.vehicles.delete(existed);
    return noContent().build();
  }
}