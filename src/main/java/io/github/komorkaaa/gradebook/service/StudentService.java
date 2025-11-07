package io.github.komorkaaa.gradebook.service;

import io.github.komorkaaa.gradebook.model.Student;
import io.github.komorkaaa.gradebook.repository.StudentRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Cacheable(value = "students")
  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  @Cacheable(value = "student", key = "#id")
  public Optional<Student> findById(UUID id) {
    return studentRepository.findById(id);
  }

  @CacheEvict(value = "students", allEntries = true)
  public Student create(Student student) {
    if (student.getId() == null) {
      student.setId(UUID.randomUUID());
    }
    return studentRepository.save(student);
  }

  @CachePut(value = "student", key = "#id")
  @CacheEvict(value = "students", allEntries = true)
  public Optional<Student> update(UUID id, Student updatedStudent) {
    return studentRepository.findById(id).map(existing -> {
      existing.setName(updatedStudent.getName());
      existing.setEmail(updatedStudent.getEmail());
      return studentRepository.save(existing);
    });
  }
  @CacheEvict(value = {"students", "student"}, allEntries = true)
  public boolean delete(UUID id) {
    if (!studentRepository.existsById(id)) return false;
    studentRepository.deleteById(id);
    return true;
  }

  public List<Student> findByNameContains(String q) {
    return studentRepository.findAll().stream()
            .filter(s -> s.getName().toLowerCase().contains(q.toLowerCase()))
            .toList();
  }
}
