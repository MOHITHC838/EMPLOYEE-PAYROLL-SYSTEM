
package com.employee.service;

import com.employee.dao.PayrollDAO;
import com.employee.model.Payroll;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayrollService {

    private PayrollDAO payrollDAO;

    public PayrollService() {
        payrollDAO = new PayrollDAO();
    }

    // Generate Payroll
    public Payroll generatePayroll(
            int employeeId,
            LocalDate payrollMonth,
            BigDecimal allowances,
            BigDecimal deductions) {

        // Check employee basic salary
        BigDecimal basicSalary =
                payrollDAO.getEmployeeBasicSalary(employeeId);

        if (basicSalary == null) {
            throw new IllegalArgumentException(
                    "Employee not found or basic salary is not available!"
            );
        }

        // Validate salary values
        if (basicSalary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Employee basic salary must be greater than zero!"
            );
        }

        if (allowances.compareTo(BigDecimal.ZERO) < 0 ||
                deductions.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Allowances and deductions cannot be negative!"
            );
        }

        // Calculate gross salary
        BigDecimal grossSalary = basicSalary.add(allowances);

        // Calculate net salary
        BigDecimal netSalary = grossSalary.subtract(deductions);

        if (netSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Deductions cannot exceed total salary!"
            );
        }

        // Check duplicate payroll
        Payroll existing =
                payrollDAO.getPayrollByEmployeeAndMonth(
                        employeeId, payrollMonth
                );

        if (existing != null) {
            throw new IllegalArgumentException(
                    "Payroll already generated for this employee and month!"
            );
        }

        // Create Payroll Object
        Payroll payroll = new Payroll(
                0,
                employeeId,
                payrollMonth,
                basicSalary,
                allowances,
                deductions,
                netSalary,
                LocalDate.now()
        );

        // Save into database
        if (payrollDAO.savePayroll(payroll)) {
            return payrollDAO.getPayrollByEmployeeAndMonth(
                    employeeId, payrollMonth
            );
        }

        throw new IllegalArgumentException(
                "Unable to generate payroll!"
        );
    }

    // Get Payroll by ID
    public Payroll getPayrollById(int payrollId) {
        return payrollDAO.getPayrollById(payrollId);
    }
}
