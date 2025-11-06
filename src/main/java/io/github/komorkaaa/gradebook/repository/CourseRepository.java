package io.github.komorkaaa.gradebook.repository;

import io.github.komorkaaa.gradebook.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
