package io.github.komorkaaa.gradebook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
  @Id
  private String id;

  @Column(nullable = false)
  private String name;

  public Course() {}

  public Course(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }

  public void setId(String id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
}
