package com.example.demo.controller;

import com.example.demo.dto.AttendanceRequest;
import com.example.demo.model.Student;
import com.example.demo.model.Attendance;
import com.example.demo.service.AttendanceService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceService attendanceService;

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/addstudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(savedStudent);
    }


    // Update attendance for a specific student
    @PutMapping("/{rollNo}/attendance")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable("rollNo") int rollNo,
            @RequestBody AttendanceRequest attendanceRequest) {
        System.out.println("Received Request: " + attendanceRequest);
//        LocalDate localDate = attendanceRequest.getDate();
        Attendance updatedAttendance = attendanceService.markAttendance(rollNo, attendanceRequest.getDate(), attendanceRequest.getStatus());
        return ResponseEntity.ok(updatedAttendance);
    }

    @GetMapping("/attendance/course/{course}")
    public ResponseEntity<List<Attendance>> getAttendanceByCourse(@PathVariable("course") String course) {
        List<Attendance> attendanceRecords = attendanceService.getAttendanceByCourse(course);
        if (attendanceRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(attendanceRecords);
    }

    @DeleteMapping("/delete/{recordId}")
    public ResponseEntity<String> deleteAttendanceRecord(@PathVariable Long recordId) {
        try {
            attendanceService.deleteAttendanceRecord(recordId);
            return ResponseEntity.ok("Attendance record deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
