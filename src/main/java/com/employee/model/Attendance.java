package com.employee.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {

    private int attendanceId;
    private int employeeId;
    private LocalDate attendanceDate;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String status;

    public Attendance(int attendanceId, int employeeId,
                      LocalDate attendanceDate,
                      LocalTime checkIn,
                      LocalTime checkOut,
                      String status) {

        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.attendanceDate = attendanceDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}