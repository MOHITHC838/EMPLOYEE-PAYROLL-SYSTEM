
package com.employee.dao;

import com.employee.database.DBConnection;
import com.employee.model.Payroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class PayrollDAO {

    // 1. Get Employee Basic Salary
    public BigDecimal getEmployeeBasicSalary(int employeeId) {

        String sql = "SELECT basic_salary FROM employees WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getBigDecimal("basic_salary");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching employee salary: " + e.getMessage());
        }

        return null;
    }

    // 2. Save Payroll
    public boolean savePayroll(Payroll payroll) {

        String sql = "INSERT INTO payroll " +
                "(employee_id, payroll_month, basic_salary, allowances, " +
                "deductions, net_salary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, payroll.getEmployeeId());
            ps.setDate(2, Date.valueOf(payroll.getPayrollMonth()));
            ps.setBigDecimal(3, payroll.getBasicSalary());
            ps.setBigDecimal(4, payroll.getAllowances());
            ps.setBigDecimal(5, payroll.getDeductions());
            ps.setBigDecimal(6, payroll.getNetSalary());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error saving payroll: " + e.getMessage());
            return false;
        }
    }

    // 3. View All Payrolls
    public List<Payroll> getAllPayrolls() {

        List<Payroll> payrolls = new ArrayList<>();

        String sql = "SELECT * FROM payroll ORDER BY payroll_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                payrolls.add(mapPayroll(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error viewing payrolls: " + e.getMessage());
        }

        return payrolls;
    }

    // 4. Search Payroll by ID
    public Payroll getPayrollById(int payrollId) {

        String sql = "SELECT * FROM payroll WHERE payroll_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, payrollId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapPayroll(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching payroll: " + e.getMessage());
        }

        return null;
    }

    // 5. Search Payroll by Employee and Month
    public Payroll getPayrollByEmployeeAndMonth(
            int employeeId, java.time.LocalDate payrollMonth) {

        String sql = "SELECT * FROM payroll " +
                "WHERE employee_id = ? AND payroll_month = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setDate(2, Date.valueOf(payrollMonth));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapPayroll(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error checking payroll: " + e.getMessage());
        }

        return null;
    }

    // 6. Delete Payroll
    public boolean deletePayroll(int payrollId) {

        String sql = "DELETE FROM payroll WHERE payroll_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, payrollId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting payroll: " + e.getMessage());
            return false;
        }
    }

    // 7. Convert ResultSet into Payroll Object
    private Payroll mapPayroll(ResultSet rs) throws SQLException {

        return new Payroll(
                rs.getInt("payroll_id"),
                rs.getInt("employee_id"),
                rs.getDate("payroll_month").toLocalDate(),
                rs.getBigDecimal("basic_salary"),
                rs.getBigDecimal("allowances"),
                rs.getBigDecimal("deductions"),
                rs.getBigDecimal("net_salary"),
                rs.getDate("generated_date").toLocalDate()
        );
    }
}
