package com.employee.model;

import java.time.LocalDate;

public class Employee {

    private int employeeId;
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private String designation;
    private int departmentId;
    private LocalDate joiningDate;
    private double basicSalary;
    private String status;


    public Employee(int employeeId,
                    String fullName,
                    String email,
                    String phone,
                    String gender,
                    String designation,
                    int departmentId,
                    LocalDate joiningDate,
                    double basicSalary,
                    String status) {

        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.designation = designation;
        this.departmentId = departmentId;
        this.joiningDate = joiningDate;
        this.basicSalary = basicSalary;
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


        public void displayEmployee() {

        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Gender: " + gender);
        System.out.println("Designation: " + designation);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Joining Date: " + joiningDate);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Status: " + status);

    }
}