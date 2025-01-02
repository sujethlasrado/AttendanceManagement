package com.example.demo.service;

import com.example.demo.dto.AttendanceSummaryDTO;
import com.example.demo.model.Attendance;
import com.example.demo.model.Student;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Fetch attendance records for a specific date
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    // Mark attendance for a student
    public Attendance markAttendance(int rollNo, LocalDate date, String status) {
        Attendance attendance = new Attendance();

        Optional<Student> studentOpt = studentRepository.findByRollNo(rollNo);
        if (studentOpt.isPresent()) {
            attendance.setDate(date);
            attendance.setStatus(status);// Ensure correct date format
            attendance.setName(studentOpt.get().getName());
            attendance.setCourse(studentOpt.get().getCourse());
            attendance.setRollNo(rollNo);
            return attendanceRepository.save(attendance);
        }
        return null;  // Or throw an exception if student is not found
    }

    // Fetch attendance by course
    public List<Attendance> getAttendanceByCourse(String course) {
        return attendanceRepository.findAttendanceByCourse(course);
    }

    public List<AttendanceSummaryDTO> getAttendanceSummary() {
        return attendanceRepository.findAttendanceSummary();
    }

    public void deleteAttendanceRecord(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance record not found for ID: " + id);
        }
        attendanceRepository.deleteById(id);
    }
}
