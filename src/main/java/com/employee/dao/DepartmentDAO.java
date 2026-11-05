package com.employee.dao;

import com.employee.database.DBConnection;
import com.employee.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    // 1. ADD DEPARTMENT
    public boolean addDepartment(Department dept) {

        String sql = "INSERT INTO departments (department_name) VALUES (?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dept.getDepartmentName());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding department: " + e.getMessage());
            return false;
        }
    }

    // 2. VIEW ALL DEPARTMENTS
    public List<Department> getAllDepartments() {

        List<Department> departments = new ArrayList<>();

        String sql = "SELECT * FROM departments";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Department dept = new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                );

                departments.add(dept);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving departments: " + e.getMessage());
        }

        return departments;
    }

    // 3. SEARCH DEPARTMENT
    public Department getDepartmentById(int departmentId) {

        String sql = "SELECT * FROM departments WHERE department_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, departmentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Department(
                            rs.getInt("department_id"),
                            rs.getString("department_name")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching department: " + e.getMessage());
        }

        return null;
    }

    // 4. UPDATE DEPARTMENT
    public boolean updateDepartment(int departmentId, Department dept) {

        String sql = "UPDATE departments SET department_name=? WHERE department_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dept.getDepartmentName());
            ps.setInt(2, departmentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating department: " + e.getMessage());
            return false;
        }
    }

    // 5. DELETE DEPARTMENT
    public boolean deleteDepartment(int departmentId) {

        String sql = "DELETE FROM departments WHERE department_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, departmentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting department: " + e.getMessage());
            return false;
        }
    }
}