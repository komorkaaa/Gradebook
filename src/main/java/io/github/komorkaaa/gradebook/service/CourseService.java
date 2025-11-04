package io.github.komorkaaa.gradebook.service;

import io.github.komorkaaa.gradebook.model.Course;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CourseService {
  private final Map<String, Course> courses = new ConcurrentHashMap<>();

  public List<Course> findAll() {
    return new ArrayList<>(courses.values());
  }

  public Optional<Course> findById(String id) {
    return Optional.ofNullable(courses.get(id));
  }

  public Course create(Course c) {
    if (c.getId() == null || c.getId().isBlank()) {
      throw new IllegalArgumentException("Course id must be provided");
    }
    courses.put(c.getId(), c);
    return c;
  }

  public Optional<Course>  update(String id, Course update) {
    Course existing = courses.get(id);
    if (existing == null) return Optional.empty();
    existing.setName(update.getName());
    return Optional.of(existing);
  }

  public boolean delete(String id) {
    return courses.remove(id) != null;
  }
}
