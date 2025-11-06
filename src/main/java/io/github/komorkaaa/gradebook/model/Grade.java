package io.github.komorkaaa.gradebook.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "grades")
public class Grade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  private BigDecimal value;
  private BigDecimal weight = BigDecimal.ONE;
  private Instant date = Instant.now();
  private String note;

  public Long getId() {
    return id;
  }
  public Student getStudent() {
    return student;
  }
  public Course getCourse() {
    return course;
  }
  public BigDecimal getValue() {
    return value;
  }
  public BigDecimal getWeight() {
    return weight;
  }
  public Instant getDate() {
    return date;
  }
  public String getNote() {
    return note;
  }

  public void setId(Long id) {
    this.id = id;
  }
  public void setStudent(Student student) {
    this.student = student;
  }
  public void setCourse(Course course) {
    this.course = course;
  }
  public void setValue(BigDecimal value) {
    this.value = value;
  }
  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }
  public void setDate(Instant date) {
    this.date = date;
  }
  public void setNote(String note) {
    this.note = note;
  }

}
