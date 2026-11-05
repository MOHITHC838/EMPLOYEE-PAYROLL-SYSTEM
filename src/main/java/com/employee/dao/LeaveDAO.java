
package com.employee.dao;

import com.employee.database.DBConnection;
import com.employee.model.Leave;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveDAO {

    // 1. Apply for Leave
    public boolean applyLeave(Leave leave) {

        String sql = "INSERT INTO leave_requests " +
                "(employee_id, leave_type, start_date, end_date, reason, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, leave.getEmployeeId());
            ps.setString(2, leave.getLeaveType());
            ps.setDate(3, Date.valueOf(leave.getStartDate()));
            ps.setDate(4, Date.valueOf(leave.getEndDate()));
            ps.setString(5, leave.getReason());
            ps.setString(6, leave.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error applying leave: " + e.getMessage());
            return false;
        }
    }

    // 2. View All Leave Requests
    public List<Leave> getAllLeaves() {

        List<Leave> leaves = new ArrayList<>();

        String sql = "SELECT * FROM leave_requests";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Leave leave = new Leave(
                        rs.getInt("leave_id"),
                        rs.getInt("employee_id"),
                        rs.getString("leave_type"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getString("reason"),
                        rs.getString("status")
                );

                leaves.add(leave);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing leaves: " + e.getMessage());
        }

        return leaves;
    }

    // 3. Search Leave by ID
    public Leave getLeaveById(int leaveId) {

        String sql = "SELECT * FROM leave_requests WHERE leave_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, leaveId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Leave(
                            rs.getInt("leave_id"),
                            rs.getInt("employee_id"),
                            rs.getString("leave_type"),
                            rs.getDate("start_date").toLocalDate(),
                            rs.getDate("end_date").toLocalDate(),
                            rs.getString("reason"),
                            rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching leave: " + e.getMessage());
        }

        return null;
    }

    // 4. Approve or Reject Leave
    public boolean updateLeaveStatus(int leaveId, String status) {

        String sql = "UPDATE leave_requests SET status = ? WHERE leave_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, leaveId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating leave status: " + e.getMessage());
            return false;
        }
    }

    // 5. Delete Leave Request
    public boolean deleteLeave(int leaveId) {

        String sql = "DELETE FROM leave_requests WHERE leave_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, leaveId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting leave: " + e.getMessage());
            return false;
        }
    }
}
