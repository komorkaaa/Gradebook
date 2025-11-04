package io.github.komorkaaa.gradebook.controller;

import io.github.komorkaaa.gradebook.model.Student;
import io.github.komorkaaa.gradebook.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> all(@RequestParam(value = "q", required = false) String q) {
    if (q != null && !q.isBlank()) {
      return studentService.findByNameContains(q);
    }
    return studentService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> get(@PathVariable String id) {
    return studentService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
    Student created = studentService.create(student);
    return ResponseEntity.created(URI.create("/api/students/" + created.getId())).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> update(@PathVariable String id, @Valid @RequestBody Student student) {
    return studentService.update(id, student)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    if (studentService.delete(id)) return ResponseEntity.noContent().build();
    return ResponseEntity.notFound().build();
  }
}
