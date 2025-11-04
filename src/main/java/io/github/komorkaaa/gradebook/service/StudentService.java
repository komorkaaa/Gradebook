package io.github.komorkaaa.gradebook.service;

import io.github.komorkaaa.gradebook.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {
  private final Map<String, Student> students = new ConcurrentHashMap<>();

  public List<Student> findAll() {
    return new ArrayList<>(students.values());
  }

  public Optional<Student> findById(String id) {
    return Optional.ofNullable(students.get(id));
  }

  public Student create(Student s) {
    if (s.getId() == null || s.getId().isBlank()) {
      s.setId(UUID.randomUUID().toString());
    }
    students.put(s.getId(), s);
    return s;
  }

  public Optional<Student> update(String id, Student update) {
    Student existing = students.get(id);
    if (existing == null) return Optional.empty();
    existing.setName(update.getName());
    existing.setEmail(update.getEmail());
    return Optional.of(existing);
  }

  public boolean delete(String id) {
    return students.remove(id) != null;
  }

  public List<Student> findByNameContains(String q) {
    List<Student> result = new ArrayList<>();
    for (Student s : students.values()) {
      if (s.getName().toLowerCase().contains(q.toLowerCase())) result.add(s);
    }
    return result;
  }
}
