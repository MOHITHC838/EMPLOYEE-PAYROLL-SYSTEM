package com.employee.dao;

import com.employee.database.DBConnection;
import com.employee.model.Employee;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {

    public boolean addEmployee(Employee emp) {

        String sql = "INSERT INTO employees " +
                "(full_name, email, phone, gender, designation, " +
                "department_id, joining_date, basic_salary, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getFullName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getGender());
            ps.setString(5, emp.getDesignation());
            ps.setInt(6, emp.getDepartmentId());
            ps.setDate(7, java.sql.Date.valueOf(emp.getJoiningDate()));
            ps.setDouble(8, emp.getBasicSalary());
            ps.setString(9, emp.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Error adding employee: " + e.getMessage());

            return false;
        }
    }


    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Employee emp = new Employee(

                        rs.getInt("employee_id"),

                        rs.getString("full_name"),

                        rs.getString("email"),

                        rs.getString("phone"),

                        rs.getString("gender"),

                        rs.getString("designation"),

                        rs.getInt("department_id"),

                        rs.getDate("joining_date").toLocalDate(),

                        rs.getDouble("basic_salary"),

                        rs.getString("status")
                );

                employees.add(emp);
            }

        } catch (SQLException e) {

            System.out.println("Error retrieving employees: " + e.getMessage());
        }

        return employees;
    }


    public Employee getEmployeeById(int employeeId) {

        String sql = "SELECT * FROM employees WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Employee emp = new Employee(

                            rs.getInt("employee_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getString("designation"),
                            rs.getInt("department_id"),
                            rs.getDate("joining_date").toLocalDate(),
                            rs.getDouble("basic_salary"),
                            rs.getString("status")
                    );

                    return emp;
                }
            }

        } catch (SQLException e) {

            System.out.println("Error searching employee: " + e.getMessage());
        }

        return null;
    }



    public boolean updateEmployee(int employeeId, Employee emp) {

        String sql = "UPDATE employees SET " +
                "full_name=?, email=?, phone=?, gender=?, " +
                "designation=?, department_id=?, joining_date=?, " +
                "basic_salary=?, status=? " +
                "WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getFullName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getGender());
            ps.setString(5, emp.getDesignation());
            ps.setInt(6, emp.getDepartmentId());
            ps.setDate(7, java.sql.Date.valueOf(emp.getJoiningDate()));
            ps.setDouble(8, emp.getBasicSalary());
            ps.setString(9, emp.getStatus());

            ps.setInt(10, employeeId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }


    public boolean deleteEmployee(int employeeId) {

        String sql = "DELETE FROM employees WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
}