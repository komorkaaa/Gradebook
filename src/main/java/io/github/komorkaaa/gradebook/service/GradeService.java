package io.github.komorkaaa.gradebook.service;

import io.github.komorkaaa.gradebook.model.Course;
import io.github.komorkaaa.gradebook.model.Grade;
import io.github.komorkaaa.gradebook.model.Student;
import io.github.komorkaaa.gradebook.repository.CourseRepository;
import io.github.komorkaaa.gradebook.repository.GradeRepository;
import io.github.komorkaaa.gradebook.repository.StudentRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

  private final GradeRepository gradeRepository;
  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;

  public GradeService(GradeRepository gradeRepository,
                      StudentRepository studentRepository,
                      CourseRepository courseRepository) {
    this.gradeRepository = gradeRepository;
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
  }

  @Cacheable(value = "grades", key = "'all'")
  public List<Grade> findAll() {
    return gradeRepository.findAll();
  }

  @Cacheable(value = "grades", key = "#id")
  public Optional<Grade> findById(Long id) {
    return gradeRepository.findById(id);
  }

  @CachePut(value = "grades", key = "#result.id")
  @CacheEvict(value = "grades", key = "'all'")
  public Grade create(Grade grade) {

    if (grade.getStudent() == null || grade.getCourse() == null) {
      throw new IllegalArgumentException("Student and Course must be provided");
    }

    Student student = studentRepository.findById(grade.getStudent().getId())
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    Course course = courseRepository.findById(grade.getCourse().getId())
            .orElseThrow(() -> new IllegalArgumentException("Course not found"));

    grade.setStudent(student);
    grade.setCourse(course);

    if (grade.getWeight() == null) grade.setWeight(BigDecimal.ONE);
    if (grade.getValue() == null) grade.setValue(BigDecimal.ZERO);

    return gradeRepository.save(grade);
  }

  @CachePut(value = "grades", key = "#id")
  @CacheEvict(value = "grades", key = "'all'")
  public Optional<Grade> update(Long id, Grade updatedGrade) {
    return gradeRepository.findById(id).map(existing -> {
      existing.setValue(updatedGrade.getValue());
      existing.setWeight(updatedGrade.getWeight());
      existing.setNote(updatedGrade.getNote());
      existing.setDate(updatedGrade.getDate());

      if (updatedGrade.getStudent() != null) {
        Student student = studentRepository.findById(updatedGrade.getStudent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        existing.setStudent(student);
      }

      if (updatedGrade.getCourse() != null) {
        Course course = courseRepository.findById(updatedGrade.getCourse().getId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        existing.setCourse(course);
      }

      return gradeRepository.save(existing);
    });
  }

  @Caching(evict = {
          @CacheEvict(value = "grades", key = "#id"),
          @CacheEvict(value = "grades", key = "'all'")
  })
  public boolean delete(Long id) {
    if (!gradeRepository.existsById(id)) return false;
    gradeRepository.deleteById(id);
    return true;
  }
}
