package com.employee.view;

import com.employee.model.Attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class AttendanceView {

    private Scanner sc;

    public AttendanceView(Scanner sc) {
        this.sc = sc;
    }

    // Display menu
    public void showAttendanceMenu() {

        System.out.println("\n===== ATTENDANCE MANAGEMENT =====");
        System.out.println("1. Mark Attendance");
        System.out.println("2. View All Attendance");
        System.out.println("3. Search Attendance");
        System.out.println("4. Update Attendance");
        System.out.println("5. Delete Attendance");
        System.out.println("6. Back to Main Menu");

        System.out.print("Enter your choice: ");
    }

    // Get menu choice
    public int getChoice() {
        return Integer.parseInt(sc.nextLine());
    }

    // Get attendance ID
    public int getAttendanceId() {

        System.out.print("Enter Attendance ID: ");

        return Integer.parseInt(sc.nextLine());
    }

    // Get attendance details
    public Attendance getAttendanceDetails() {

        System.out.println("\n===== ATTENDANCE DETAILS =====");

        System.out.print("Enter Employee ID: ");
        int employeeId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Attendance Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Enter Check-In Time (HH:MM:SS): ");
        LocalTime checkIn = LocalTime.parse(sc.nextLine());

        System.out.print("Enter Check-Out Time (HH:MM:SS): ");
        LocalTime checkOut = LocalTime.parse(sc.nextLine());

        System.out.print("Enter Status (PRESENT/ABSENT/HALF_DAY/LEAVE): ");
        String status = sc.nextLine().toUpperCase();

        return new Attendance(
                0,
                employeeId,
                date,
                checkIn,
                checkOut,
                status
        );
    }

    // Display one record
    public void displayAttendance(Attendance attendance) {

        System.out.println("\nAttendance ID: " + attendance.getAttendanceId());
        System.out.println("Employee ID: " + attendance.getEmployeeId());
        System.out.println("Date: " + attendance.getAttendanceDate());
        System.out.println("Check-In: " + attendance.getCheckIn());
        System.out.println("Check-Out: " + attendance.getCheckOut());
        System.out.println("Status: " + attendance.getStatus());
    }

    // Display all records
    public void displayAllAttendance(List<Attendance> records) {

        if (records.isEmpty()) {
            System.out.println("No Attendance Records Found!");
            return;
        }

        System.out.println("\n===== ATTENDANCE RECORDS =====");

        for (Attendance attendance : records) {

            displayAttendance(attendance);

            System.out.println("----------------------------");
        }
    }

    // Confirmation
    public String getConfirmation() {

        System.out.print("Are you sure? (Y/N): ");

        return sc.nextLine();
    }
}