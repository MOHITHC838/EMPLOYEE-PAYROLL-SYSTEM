
package com.employee.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payroll {

    private int payrollId;
    private int employeeId;
    private LocalDate payrollMonth;
    private BigDecimal basicSalary;
    private BigDecimal allowances;
    private BigDecimal deductions;
    private BigDecimal netSalary;
    private LocalDate generatedDate;

    public Payroll(int payrollId, int employeeId,
                   LocalDate payrollMonth,
                   BigDecimal basicSalary,
                   BigDecimal allowances,
                   BigDecimal deductions,
                   BigDecimal netSalary,
                   LocalDate generatedDate) {

        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.payrollMonth = payrollMonth;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.generatedDate = generatedDate;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public LocalDate getPayrollMonth() {
        return payrollMonth;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getAllowances() {
        return allowances;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }
}
