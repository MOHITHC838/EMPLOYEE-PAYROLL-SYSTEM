package com.employee.view;

import com.employee.model.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentView {

    private Scanner sc;

    public DepartmentView(Scanner sc) {
        this.sc = sc;
    }

    // Display menu
    public void showDepartmentMenu() {

        System.out.println("\n===== DEPARTMENT MANAGEMENT =====");
        System.out.println("1. Add Department");
        System.out.println("2. View All Departments");
        System.out.println("3. Search Department");
        System.out.println("4. Update Department");
        System.out.println("5. Delete Department");
        System.out.println("6. Back to Main Menu");

        System.out.print("Enter your choice: ");
    }

    // Get menu choice
    public int getChoice() {
        return Integer.parseInt(sc.nextLine());
    }

    // Get department ID
    public int getDepartmentId() {

        System.out.print("Enter Department ID: ");

        return Integer.parseInt(sc.nextLine());
    }

    // Get department name
    public String getDepartmentName() {

        System.out.print("Enter Department Name: ");

        return sc.nextLine();
    }

    // Display one department
    public void displayDepartment(Department dept) {

        System.out.println("\nDepartment ID: " + dept.getDepartmentId());
        System.out.println("Department Name: " + dept.getDepartmentName());
    }

    // Display all departments
    public void displayDepartments(List<Department> departments) {

        if (departments.isEmpty()) {
            System.out.println("No Departments Found!");
            return;
        }

        System.out.println("\n===== DEPARTMENT DETAILS =====");

        for (Department dept : departments) {

            displayDepartment(dept);

            System.out.println("----------------------------");
        }
    }

    // Confirmation
    public String getConfirmation() {

        System.out.print("Are you sure? (Y/N): ");

        return sc.nextLine();
    }
}