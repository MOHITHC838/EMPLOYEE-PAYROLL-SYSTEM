package com.employee.view;

import java.util.Scanner;
import com.employee.model.Employee;
import java.util.List;
import java.time.LocalDate;


public class EmployeeView {

    private Scanner sc;

    public EmployeeView(Scanner sc) {
        this.sc = sc;
    }
    public void showEmployeeMenu() {
            System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your choice: ");
    }

    public int getChoice() {
        return Integer.parseInt(sc.nextLine());
    }


    public void displayEmployees(List<Employee> employees) {

        if (employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        System.out.println("\n===== EMPLOYEE DETAILS =====");

        for (Employee emp : employees) {

            emp.displayEmployee();

            System.out.println("----------------------------");
        }
    }


    public Employee getEmployeeDetails() {

        sc.nextLine(); // Clear previous menu input

        System.out.println("\n===== ADD NEW EMPLOYEE =====");

        System.out.print("Enter Full Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();

        System.out.print("Enter Department ID: ");
        int departmentId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Joining Date (YYYY-MM-DD): ");
        LocalDate joiningDate = LocalDate.parse(sc.nextLine());

        System.out.print("Enter Basic Salary: ");
        double salary = Double.parseDouble(sc.nextLine());

        return new Employee(
                0,
                name,
                email,
                phone,
                gender,
                designation,
                departmentId,
                joiningDate,
                salary,
                "ACTIVE"
        );
    }


    public int getEmployeeId() {

        System.out.print("Enter Employee ID: ");

        return Integer.parseInt(sc.nextLine());
    }


    public Employee getUpdatedEmployeeDetails() {

        System.out.println("\n===== UPDATE EMPLOYEE =====");

        System.out.print("Enter New Full Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        System.out.print("Enter New Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter New Gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter New Designation: ");
        String designation = sc.nextLine();

        System.out.print("Enter New Department ID: ");
        int departmentId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter New Joining Date (YYYY-MM-DD): ");
        LocalDate joiningDate = LocalDate.parse(sc.nextLine());

        System.out.print("Enter New Basic Salary: ");
        double salary = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Status (ACTIVE/INACTIVE): ");
        String status = sc.nextLine();

        return new Employee(
                0,
                name,
                email,
                phone,
                gender,
                designation,
                departmentId,
                joiningDate,
                salary,
                status
        );
    }
    public void showDeleteMessage() {
        System.out.println("\n===== DELETE EMPLOYEE =====");
    }

    public String getConfirmation() {
        return sc.nextLine();
    }

}