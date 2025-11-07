package io.github.komorkaaa.gradebook.service;

import io.github.komorkaaa.gradebook.model.Course;
import io.github.komorkaaa.gradebook.repository.CourseRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Cacheable(value = "courses")
  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  @Cacheable(value = "courses", key = "#id")
  public Optional<Course> findById(String id) {
    return courseRepository.findById(id);
  }

  @CachePut(value = "courses", key = "#course.id")
  public Course create(Course course) {
    if (course.getId() == null || course.getId().isBlank()) {
      throw new IllegalArgumentException("Course id must be provided");
    }
    return courseRepository.save(course);
  }

  @CachePut(value = "courses", key = "#id")
  public Optional<Course> update(String id, Course updatedCourse) {
    return courseRepository.findById(id).map(existing -> {
      existing.setName(updatedCourse.getName());
      return courseRepository.save(existing);
    });
  }

  @CacheEvict(value = "courses", key = "#id")
  public boolean delete(String id) {
    if (!courseRepository.existsById(id)) return false;
    courseRepository.deleteById(id);
    return true;
  }
}
