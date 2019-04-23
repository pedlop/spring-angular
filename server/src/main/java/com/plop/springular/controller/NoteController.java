package com.plop.springular.controller;

import java.util.List;

import com.plop.springular.model.Note;
import com.plop.springular.repository.NoteRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

  private NoteRepository repository;

  NoteController(NoteRepository noteRepository) {
    this.repository = noteRepository;
  }

  @GetMapping
  public List findAll() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable long id) {
    return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Note create(@RequestBody Note note) {
    System.out.println("NOTE" + note);
    return repository.save(note);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") long id, @RequestBody Note note) {
    return repository.findById(id).map(record -> {
      record.setTitle(note.getTitle());
      record.setDescription(note.getDescription());
      Note updated = repository.save(record);
      return ResponseEntity.ok().body(updated);
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable long id) {
    return repository.findById(id).map(record -> {
      repository.deleteById(id);
      return ResponseEntity.ok().build();
    }).orElse(ResponseEntity.notFound().build());
  }
}