package com.employee.dao;

import com.employee.database.DBConnection;
import com.employee.model.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    // 1. MARK ATTENDANCE
    public boolean markAttendance(Attendance attendance) {

        String sql = "INSERT INTO attendance " +
                "(employee_id, attendance_date, check_in, check_out, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attendance.getEmployeeId());
            ps.setDate(2, Date.valueOf(attendance.getAttendanceDate()));
            ps.setTime(3, Time.valueOf(attendance.getCheckIn()));
            ps.setTime(4, Time.valueOf(attendance.getCheckOut()));
            ps.setString(5, attendance.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error marking attendance: " + e.getMessage());
            return false;
        }
    }

    // 2. VIEW ALL ATTENDANCE
    public List<Attendance> getAllAttendance() {

        List<Attendance> records = new ArrayList<>();

        String sql = "SELECT * FROM attendance ORDER BY attendance_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Attendance attendance = new Attendance(
                        rs.getInt("attendance_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("attendance_date").toLocalDate(),
                        rs.getTime("check_in").toLocalTime(),
                        rs.getTime("check_out").toLocalTime(),
                        rs.getString("status")
                );

                records.add(attendance);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving attendance: " + e.getMessage());
        }

        return records;
    }

    // 3. SEARCH ATTENDANCE BY ID
    public Attendance getAttendanceById(int attendanceId) {

        String sql = "SELECT * FROM attendance WHERE attendance_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attendanceId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Attendance(
                            rs.getInt("attendance_id"),
                            rs.getInt("employee_id"),
                            rs.getDate("attendance_date").toLocalDate(),
                            rs.getTime("check_in").toLocalTime(),
                            rs.getTime("check_out").toLocalTime(),
                            rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching attendance: " + e.getMessage());
        }

        return null;
    }

    // 4. UPDATE ATTENDANCE
    public boolean updateAttendance(int attendanceId, Attendance attendance) {

        String sql = "UPDATE attendance SET employee_id=?, " +
                "attendance_date=?, check_in=?, check_out=?, status=? " +
                "WHERE attendance_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attendance.getEmployeeId());
            ps.setDate(2, Date.valueOf(attendance.getAttendanceDate()));
            ps.setTime(3, Time.valueOf(attendance.getCheckIn()));
            ps.setTime(4, Time.valueOf(attendance.getCheckOut()));
            ps.setString(5, attendance.getStatus());
            ps.setInt(6, attendanceId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating attendance: " + e.getMessage());
            return false;
        }
    }

    // 5. DELETE ATTENDANCE
    public boolean deleteAttendance(int attendanceId) {

        String sql = "DELETE FROM attendance WHERE attendance_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attendanceId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting attendance: " + e.getMessage());
            return false;
        }
    }
}