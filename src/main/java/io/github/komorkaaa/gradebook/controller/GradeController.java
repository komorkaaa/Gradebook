package io.github.komorkaaa.gradebook.controller;

import io.github.komorkaaa.gradebook.model.Grade;
import io.github.komorkaaa.gradebook.service.GradeService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

  private final GradeService gradeService;

  public GradeController(GradeService gradeService) {
    this.gradeService = gradeService;
  }

  @GetMapping
  public List<Grade> all() {
    return gradeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Grade> get(@PathVariable Long id) {
    return gradeService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Grade> create(@Valid @RequestBody Grade grade) {
    Grade created = gradeService.create(grade);
    return ResponseEntity
            .created(URI.create("/api/grades/" + created.getId()))
            .body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Grade> update(@PathVariable Long id, @Valid @RequestBody Grade grade) {
    return gradeService.update(id, grade)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (gradeService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
