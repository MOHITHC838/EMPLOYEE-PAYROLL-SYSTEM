
package com.employee.controller;

import com.employee.dao.LeaveDAO;
import com.employee.model.Leave;
import com.employee.view.LeaveView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LeaveController {

    private LeaveDAO leaveDAO;
    private LeaveView leaveView;

    public LeaveController(Scanner sc) {

        leaveDAO = new LeaveDAO();
        leaveView = new LeaveView(sc);
    }

    public void start() {

        int choice;

        do {

            leaveView.showLeaveMenu();
            choice = leaveView.getChoice();

            switch (choice) {

                // 1. Apply for Leave
                case 1:

                    try {
                        Leave leave = leaveView.getLeaveDetails();

                        if (leave.getEndDate().isBefore(leave.getStartDate())) {
                            leaveView.showMessage(
                                    "End date cannot be before start date!"
                            );
                            break;
                        }

                        if (leaveDAO.applyLeave(leave)) {
                            leaveView.showMessage(
                                    "Leave Applied Successfully!"
                            );
                        } else {
                            leaveView.showMessage(
                                    "Failed to Apply Leave!"
                            );
                        }

                    } catch (IllegalArgumentException e) {
                        leaveView.showMessage(
                                "Invalid input! Please enter valid details."
                        );
                    }

                    break;

                // 2. View All Leave Requests
                case 2:

                    List<Leave> leaves = leaveDAO.getAllLeaves();
                    leaveView.displayAllLeaves(leaves);

                    break;

                // 3. Search Leave by ID
                case 3:

                    int searchId = leaveView.getLeaveId();

                    Leave searchedLeave =
                            leaveDAO.getLeaveById(searchId);

                    if (searchedLeave != null) {
                        leaveView.displayLeave(searchedLeave);
                    } else {
                        leaveView.showMessage(
                                "Leave Request Not Found!"
                        );
                    }

                    break;

                // 4. Approve / Reject Leave
                case 4:

                    int updateId = leaveView.getLeaveId();

                    Leave existingLeave =
                            leaveDAO.getLeaveById(updateId);

                    if (existingLeave != null) {

                        leaveView.displayLeave(existingLeave);

                        String status = leaveView.getLeaveStatus();

                        if (status == null) {
                            leaveView.showMessage(
                                    "Invalid Status Choice!"
                            );
                            break;
                        }

                        if (leaveDAO.updateLeaveStatus(updateId, status)) {
                            leaveView.showMessage(
                                    "Leave Status Updated Successfully!"
                            );
                        } else {
                            leaveView.showMessage(
                                    "Failed to Update Leave Status!"
                            );
                        }

                    } else {
                        leaveView.showMessage(
                                "Leave Request Not Found!"
                        );
                    }

                    break;

                // 5. Delete Leave Request
                case 5:

                    int deleteId = leaveView.getLeaveId();

                    Leave deleteLeave =
                            leaveDAO.getLeaveById(deleteId);

                    if (deleteLeave != null) {

                        leaveView.displayLeave(deleteLeave);

                        String confirmation =
                                leaveView.getConfirmation();

                        if (confirmation.equalsIgnoreCase("Y")) {

                            if (leaveDAO.deleteLeave(deleteId)) {
                                leaveView.showMessage(
                                        "Leave Deleted Successfully!"
                                );
                            } else {
                                leaveView.showMessage(
                                        "Failed to Delete Leave!"
                                );
                            }

                        } else {
                            leaveView.showMessage(
                                    "Delete Operation Cancelled!"
                            );
                        }

                    } else {
                        leaveView.showMessage(
                                "Leave Request Not Found!"
                        );
                    }

                    break;

                // 6. Back to Main Menu
                case 6:

                    leaveView.showMessage(
                            "Returning to Main Menu..."
                    );

                    break;

                default:

                    leaveView.showMessage(
                            "Invalid Choice! Try Again."
                    );
            }

        } while (choice != 6);
    }
}
