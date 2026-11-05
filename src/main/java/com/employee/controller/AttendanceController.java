package com.employee.controller;

import com.employee.dao.AttendanceDAO;
import com.employee.model.Attendance;
import com.employee.view.AttendanceView;

import java.util.List;
import java.util.Scanner;

public class AttendanceController {

    private AttendanceDAO attendanceDAO;
    private AttendanceView attendanceView;

    public AttendanceController(Scanner sc) {

        attendanceDAO = new AttendanceDAO();

        attendanceView = new AttendanceView(sc);
    }

    public void start() {

        int choice;

        do {

            attendanceView.showAttendanceMenu();

            choice = attendanceView.getChoice();

            switch (choice) {

                // 1. MARK ATTENDANCE
                case 1:

                    Attendance newAttendance =
                            attendanceView.getAttendanceDetails();

                    boolean added =
                            attendanceDAO.markAttendance(newAttendance);

                    if (added) {
                        System.out.println("Attendance Marked Successfully!");
                    } else {
                        System.out.println("Attendance Marking Failed!");
                    }

                    break;

                // 2. VIEW ALL ATTENDANCE
                case 2:

                    List<Attendance> records =
                            attendanceDAO.getAllAttendance();

                    attendanceView.displayAllAttendance(records);

                    break;

                // 3. SEARCH ATTENDANCE
                case 3:

                    int searchId = attendanceView.getAttendanceId();

                    Attendance found =
                            attendanceDAO.getAttendanceById(searchId);

                    if (found != null) {

                        System.out.println("Attendance Record Found!");

                        attendanceView.displayAttendance(found);

                    } else {

                        System.out.println("Attendance Record Not Found!");
                    }

                    break;

                // 4. UPDATE ATTENDANCE
                case 4:

                    int updateId = attendanceView.getAttendanceId();

                    Attendance existing =
                            attendanceDAO.getAttendanceById(updateId);

                    if (existing != null) {

                        System.out.println("Attendance Record Found!");

                        Attendance updated =
                                attendanceView.getAttendanceDetails();

                        boolean result =
                                attendanceDAO.updateAttendance(updateId, updated);

                        if (result) {
                            System.out.println("Attendance Updated Successfully!");
                        } else {
                            System.out.println("Attendance Update Failed!");
                        }

                    } else {

                        System.out.println("Attendance Record Not Found!");
                    }

                    break;

                // 5. DELETE ATTENDANCE
                case 5:

                    int deleteId = attendanceView.getAttendanceId();

                    Attendance deleteRecord =
                            attendanceDAO.getAttendanceById(deleteId);

                    if (deleteRecord != null) {

                        attendanceView.displayAttendance(deleteRecord);

                        String confirmation =
                                attendanceView.getConfirmation();

                        if (confirmation.equalsIgnoreCase("Y")) {

                            boolean deleted =
                                    attendanceDAO.deleteAttendance(deleteId);

                            if (deleted) {
                                System.out.println("Attendance Deleted Successfully!");
                            } else {
                                System.out.println("Attendance Deletion Failed!");
                            }

                        } else {

                            System.out.println("Delete Operation Cancelled!");
                        }

                    } else {

                        System.out.println("Attendance Record Not Found!");
                    }

                    break;

                case 6:

                    System.out.println("Returning to Main Menu...");

                    break;

                default:

                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 6);
    }
}