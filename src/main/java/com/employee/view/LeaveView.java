
package com.employee.view;

import com.employee.model.Leave;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LeaveView {

    private Scanner sc;

    public LeaveView(Scanner sc) {
        this.sc = sc;
    }

    // 1. Display Leave Menu
    public void showLeaveMenu() {

        System.out.println("\n===== LEAVE MANAGEMENT =====");
        System.out.println("1. Apply for Leave");
        System.out.println("2. View All Leave Requests");
        System.out.println("3. Search Leave by ID");
        System.out.println("4. Approve / Reject Leave");
        System.out.println("5. Delete Leave Request");
        System.out.println("6. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    // 2. Get Menu Choice
    public int getChoice() {
        return Integer.parseInt(sc.nextLine());
    }

    // 3. Get Leave ID
    public int getLeaveId() {

        System.out.print("Enter Leave ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    // 4. Get Leave Details
    public Leave getLeaveDetails() {

        System.out.print("Enter Employee ID: ");
        int employeeId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Leave Type (CASUAL/SICK/EARNED): ");
        String leaveType = sc.nextLine();

        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(sc.nextLine());

        System.out.print("Enter End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(sc.nextLine());

        System.out.print("Enter Reason: ");
        String reason = sc.nextLine();

        return new Leave(
                0,
                employeeId,
                leaveType,
                startDate,
                endDate,
                reason,
                "PENDING"
        );
    }

    // 5. Display One Leave Request
    public void displayLeave(Leave leave) {

        System.out.println("\n===== LEAVE DETAILS =====");

        System.out.println("Leave ID: " + leave.getLeaveId());
        System.out.println("Employee ID: " + leave.getEmployeeId());
        System.out.println("Leave Type: " + leave.getLeaveType());
        System.out.println("Start Date: " + leave.getStartDate());
        System.out.println("End Date: " + leave.getEndDate());
        System.out.println("Reason: " + leave.getReason());
        System.out.println("Status: " + leave.getStatus());
    }

    // 6. Display All Leave Requests
    public void displayAllLeaves(List<Leave> leaves) {

        if (leaves.isEmpty()) {
            System.out.println("\nNo Leave Requests Found!");
            return;
        }

        System.out.println("\n===== ALL LEAVE REQUESTS =====");

        for (Leave leave : leaves) {
            displayLeave(leave);
            System.out.println("-----------------------------");
        }
    }

    // 7. Get Approval Status
    public String getLeaveStatus() {

        System.out.println("\n1. APPROVED");
        System.out.println("2. REJECTED");
        System.out.print("Select Status: ");

        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            return "APPROVED";
        } else if (choice == 2) {
            return "REJECTED";
        } else {
            return null;
        }
    }

    // 8. Confirmation
    public String getConfirmation() {

        System.out.print("Are you sure? (Y/N): ");
        return sc.nextLine();
    }

    // 9. Success Messages
    public void showMessage(String message) {
        System.out.println(message);
    }
}
