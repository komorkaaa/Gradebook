package io.github.komorkaaa.gradebook.controller;

import io.github.komorkaaa.gradebook.model.Course;
import io.github.komorkaaa.gradebook.service.CourseService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
  private final CourseService courseService;
  public CourseController(CourseService courseService) { this.courseService = courseService; }

  @GetMapping
  public List<Course> all() { return courseService.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<Course> get(@PathVariable String id) {
    return courseService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Course> create(@RequestBody Course course) {
    Course created = courseService.create(course);
    return ResponseEntity.created(URI.create("/api/courses/" + created.getId())).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable String id, @RequestBody Course course) {
    return courseService.update(id, course).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    if (courseService.delete(id)) return ResponseEntity.noContent().build();
    return ResponseEntity.notFound().build();
  }
}
