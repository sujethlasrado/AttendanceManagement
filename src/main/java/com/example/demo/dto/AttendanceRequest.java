package com.example.demo.dto;

import java.time.LocalDate;

public class AttendanceRequest {
    private LocalDate date;
    private String status;

    // Getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceRequest{" +
                "date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
