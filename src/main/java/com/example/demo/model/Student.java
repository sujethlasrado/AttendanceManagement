package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(unique = true, nullable = false)
    @NotNull(message = "Roll number cannot be null")
    @Positive(message = "Roll number must be a positive value")
    private int rollNo;

    @Column(nullable = false)
    @NotBlank(message = "Course cannot be empty")
    private String course;

    @Column(nullable = false)
    @NotBlank(message = "Semester cannot be empty")
    private String semester;

    @Column(nullable = false)
    @NotBlank(message = "Branch cannot be empty")
    private String branch;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Attendance> attendanceRecords;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rollNo=" + rollNo +
                ", course='" + course + '\'' +
                ", semester='" + semester + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
