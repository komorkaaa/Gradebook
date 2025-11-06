package io.github.komorkaaa.gradebook.repository;

import io.github.komorkaaa.gradebook.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}