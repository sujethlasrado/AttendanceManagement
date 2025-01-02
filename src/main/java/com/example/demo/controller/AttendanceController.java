package com.example.demo.controller;

import com.example.demo.dto.AttendanceRequest;
import com.example.demo.dto.AttendanceSummaryDTO;
import com.example.demo.model.Attendance;
import com.example.demo.service.AttendanceService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    // Get attendance by date
    @GetMapping("/{date}")
    public List<Attendance> getAttendanceByDate(@PathVariable String date) {
        // Convert string to LocalDate for comparison
        LocalDate localDate = LocalDate.parse(date);
        // Fetch attendance records for the given date
        return attendanceService.getAttendanceByDate(localDate);
    }

    @GetMapping("/attendance-summary")
    public List<AttendanceSummaryDTO> getAttendanceSummary() {
        return attendanceService.getAttendanceSummary();

    }
}
