package com.example.demo.repository;

import com.example.demo.dto.AttendanceSummaryDTO;
import com.example.demo.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    // Find attendance by date
    List<Attendance> findByDate(LocalDate date);

    @Query("SELECT a FROM Attendance a JOIN Student s ON a.rollNo = s.rollNo WHERE s.course = :course")
    List<Attendance> findAttendanceByCourse(String course);

    @Query(value = "SELECT s.roll_no as rollNo, s.name, a.course, " +
            "ROUND((COUNT(CASE WHEN a.status = 'Present' THEN 1 END) * 100.0) / COUNT(a.status), 2) AS attendancePercentage " +
            "FROM attendance a " +
            "JOIN student s ON a.roll_no = s.roll_no " +
            "GROUP BY s.roll_no, a.course, s.name", nativeQuery = true)
    List<AttendanceSummaryDTO> findAttendanceSummary();
}
