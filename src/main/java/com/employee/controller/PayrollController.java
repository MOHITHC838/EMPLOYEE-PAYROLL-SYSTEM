
package com.employee.controller;

import com.employee.dao.PayrollDAO;
import com.employee.model.Payroll;
import com.employee.service.PayrollService;
import com.employee.view.PayrollView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PayrollController {

    private PayrollDAO payrollDAO;
    private PayrollService payrollService;
    private PayrollView payrollView;

    public PayrollController(Scanner sc) {

        payrollDAO = new PayrollDAO();
        payrollService = new PayrollService();
        payrollView = new PayrollView(sc);
    }

    public void start() {

        int choice;

        do {

            try {

                payrollView.showPayrollMenu();
                choice = payrollView.getChoice();

                switch (choice) {

                    // 1. Generate Payroll
                    case 1:

                        int employeeId = payrollView.getEmployeeId();

                        LocalDate payrollMonth =
                                payrollView.getPayrollMonth();

                        BigDecimal allowances =
                                payrollView.getAllowances();

                        BigDecimal deductions =
                                payrollView.getDeductions();

                        Payroll generatedPayroll =
                                payrollService.generatePayroll(
                                        employeeId,
                                        payrollMonth,
                                        allowances,
                                        deductions
                                );

                        payrollView.showMessage(
                                "Payroll Generated Successfully!"
                        );

                        payrollView.displayPayroll(generatedPayroll);

                        break;

                    // 2. View All Payrolls
                    case 2:

                        List<Payroll> payrolls =
                                payrollDAO.getAllPayrolls();

                        payrollView.displayAllPayrolls(payrolls);

                        break;

                    // 3. Search Payroll
                    case 3:

                        int searchId = payrollView.getPayrollId();

                        Payroll searchedPayroll =
                                payrollDAO.getPayrollById(searchId);

                        if (searchedPayroll != null) {
                            payrollView.displayPayroll(searchedPayroll);
                        } else {
                            payrollView.showMessage(
                                    "Payroll Record Not Found!"
                            );
                        }

                        break;

                    // 4. Generate Salary Slip
                    case 4:

                        int slipId = payrollView.getPayrollId();

                        Payroll slipPayroll =
                                payrollService.getPayrollById(slipId);

                        if (slipPayroll != null) {
                            payrollView.displaySalarySlip(slipPayroll);
                        } else {
                            payrollView.showMessage(
                                    "Payroll Record Not Found!"
                            );
                        }

                        break;

                    // 5. Delete Payroll
                    case 5:

                        int deleteId = payrollView.getPayrollId();

                        Payroll deletePayroll =
                                payrollDAO.getPayrollById(deleteId);

                        if (deletePayroll != null) {

                            payrollView.displayPayroll(deletePayroll);

                            String confirmation =
                                    payrollView.getConfirmation();

                            if (confirmation.equalsIgnoreCase("Y")) {

                                if (payrollDAO.deletePayroll(deleteId)) {
                                    payrollView.showMessage(
                                            "Payroll Deleted Successfully!"
                                    );
                                } else {
                                    payrollView.showMessage(
                                            "Failed to Delete Payroll!"
                                    );
                                }

                            } else {
                                payrollView.showMessage(
                                        "Delete Operation Cancelled!"
                                );
                            }

                        } else {
                            payrollView.showMessage(
                                    "Payroll Record Not Found!"
                            );
                        }

                        break;

                    // 6. Back to Main Menu
                    case 6:

                        payrollView.showMessage(
                                "Returning to Main Menu..."
                        );

                        break;

                    default:

                        payrollView.showMessage(
                                "Invalid Choice! Try Again."
                        );
                }

            } catch (NumberFormatException e) {

                payrollView.showMessage(
                        "Invalid number! Please enter a valid number."
                );

                choice = 0;

            } catch (java.time.DateTimeException e) {

                payrollView.showMessage(
                        "Invalid date or month! Use YYYY-MM format."
                );

                choice = 0;

            } catch (IllegalArgumentException e) {

                payrollView.showMessage(e.getMessage());
                choice = 0;
            }

        } while (choice != 6);
    }
}
