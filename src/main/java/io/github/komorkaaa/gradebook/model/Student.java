package io.github.komorkaaa.gradebook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student implements Serializable {
  @Id
  private UUID id = UUID.randomUUID();

  @NotBlank
  @Column(nullable = false)
  private String name;

  @Email
  @Column(unique = true)
  private String email;

  public Student() {

  }

  public Student(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public UUID getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }

  public void setId(UUID id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
