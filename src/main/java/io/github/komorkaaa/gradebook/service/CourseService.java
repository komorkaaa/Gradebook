package io.github.komorkaaa.gradebook.service;

import io.github.komorkaaa.gradebook.model.Course;
import io.github.komorkaaa.gradebook.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  public Optional<Course> findById(String id) {
    return courseRepository.findById(id);
  }

  public Course create(Course course) {
    if (course.getId() == null || course.getId().isBlank()) {
      throw new IllegalArgumentException("Course id must be provided");
    }
    return courseRepository.save(course);
  }

  public Optional<Course> update(String id, Course updatedCourse) {
    return courseRepository.findById(id).map(existing -> {
      existing.setName(updatedCourse.getName());
      return courseRepository.save(existing);
    });
  }

  public boolean delete(String id) {
    if (!courseRepository.existsById(id)) return false;
    courseRepository.deleteById(id);
    return true;
  }
}
