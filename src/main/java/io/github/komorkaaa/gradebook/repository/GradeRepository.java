package io.github.komorkaaa.gradebook.repository;

import io.github.komorkaaa.gradebook.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
