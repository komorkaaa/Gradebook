package io.github.komorkaaa.gradebook.mapper;

import io.github.komorkaaa.gradebook.dto.StudentCreateDto;
import io.github.komorkaaa.gradebook.dto.StudentDto;
import io.github.komorkaaa.gradebook.dto.StudentUpdateDto;
import io.github.komorkaaa.gradebook.model.Student;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentMapper {

  public StudentDto toDto(Student s) {
    return new StudentDto(s.getId(), s.getName(), s.getEmail());
  }

  public Student fromCreate(StudentCreateDto dto) {
    Student s = new Student();
    s.setId(UUID.randomUUID());
    s.setName(dto.name());
    s.setEmail(dto.email());
    return s;
  }

  public void updateEntity(Student entity, StudentUpdateDto dto) {
    entity.setName(dto.name());
    entity.setEmail(dto.email());
  }
}
