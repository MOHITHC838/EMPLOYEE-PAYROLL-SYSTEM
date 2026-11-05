
package com.employee.view;

import com.employee.model.Payroll;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

public class PayrollView {

    private Scanner sc;

    public PayrollView(Scanner sc) {
        this.sc = sc;
    }

    // Display Payroll Menu
    public void showPayrollMenu() {

        System.out.println("\n===== PAYROLL MANAGEMENT =====");
        System.out.println("1. Generate Payroll");
        System.out.println("2. View All Payrolls");
        System.out.println("3. Search Payroll by ID");
        System.out.println("4. Generate Salary Slip");
        System.out.println("5. Delete Payroll");
        System.out.println("6. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    // Get Menu Choice
    public int getChoice() {
        return Integer.parseInt(sc.nextLine());
    }

    // Get Payroll ID
    public int getPayrollId() {

        System.out.print("Enter Payroll ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    // Get Employee ID
    public int getEmployeeId() {

        System.out.print("Enter Employee ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    // Get Payroll Month
    public LocalDate getPayrollMonth() {

        System.out.print("Enter Payroll Month (YYYY-MM): ");

        YearMonth month = YearMonth.parse(sc.nextLine());

        return month.atDay(1);
    }

    // Get Allowances
    public BigDecimal getAllowances() {

        System.out.print("Enter Allowances: ");
        return new BigDecimal(sc.nextLine());
    }

    // Get Deductions
    public BigDecimal getDeductions() {

        System.out.print("Enter Deductions: ");
        return new BigDecimal(sc.nextLine());
    }

    // Display One Payroll
    public void displayPayroll(Payroll payroll) {

        System.out.println("\n===== PAYROLL DETAILS =====");

        System.out.println("Payroll ID     : " + payroll.getPayrollId());
        System.out.println("Employee ID    : " + payroll.getEmployeeId());
        System.out.println("Payroll Month  : " + payroll.getPayrollMonth());
        System.out.println("Basic Salary   : Rs. " + payroll.getBasicSalary());
        System.out.println("Allowances     : Rs. " + payroll.getAllowances());
        System.out.println("Deductions     : Rs. " + payroll.getDeductions());

        BigDecimal grossSalary =
                payroll.getBasicSalary().add(payroll.getAllowances());

        System.out.println("Gross Salary   : Rs. " + grossSalary);
        System.out.println("Net Salary     : Rs. " + payroll.getNetSalary());

        System.out.println("Generated Date : " + payroll.getGeneratedDate());
    }

    // View All Payrolls
    public void displayAllPayrolls(List<Payroll> payrolls) {

        if (payrolls.isEmpty()) {
            System.out.println("\nNo Payroll Records Found!");
            return;
        }

        System.out.println("\n===== ALL PAYROLL RECORDS =====");

        for (Payroll payroll : payrolls) {
            displayPayroll(payroll);
            System.out.println("--------------------------------");
        }
    }

    // Salary Slip
    public void displaySalarySlip(Payroll payroll) {

        System.out.println("\n");
        System.out.println("========================================");
        System.out.println("             SALARY SLIP");
        System.out.println("========================================");

        System.out.println("Payroll ID      : " + payroll.getPayrollId());
        System.out.println("Employee ID     : " + payroll.getEmployeeId());
        System.out.println("Salary Month    : " + payroll.getPayrollMonth());

        System.out.println("----------------------------------------");
        System.out.println("EARNINGS");
        System.out.println("----------------------------------------");

        System.out.println("Basic Salary    : Rs. " + payroll.getBasicSalary());
        System.out.println("Allowances      : Rs. " + payroll.getAllowances());

        BigDecimal gross =
                payroll.getBasicSalary().add(payroll.getAllowances());

        System.out.println("Gross Salary    : Rs. " + gross);

        System.out.println("----------------------------------------");
        System.out.println("DEDUCTIONS");
        System.out.println("----------------------------------------");

        System.out.println("Total Deductions: Rs. " + payroll.getDeductions());

        System.out.println("----------------------------------------");
        System.out.println("NET SALARY      : Rs. " + payroll.getNetSalary());
        System.out.println("Generated Date  : " + payroll.getGeneratedDate());
        System.out.println("========================================");
        System.out.println("          END OF SALARY SLIP");
        System.out.println("========================================");
    }

    // Confirmation
    public String getConfirmation() {

        System.out.print("Are you sure? (Y/N): ");
        return sc.nextLine();
    }

    // Message
    public void showMessage(String message) {
        System.out.println(message);
    }
}
