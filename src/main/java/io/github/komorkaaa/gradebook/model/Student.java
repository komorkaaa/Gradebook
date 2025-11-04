package io.github.komorkaaa.gradebook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student {
  private String id;
  private String name;
  private String email;

  public Student() {
    this.id = UUID.randomUUID().toString();
  }

  public Student(String name, String email) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.email = email;
  }

  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }

  public void setId(String id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
