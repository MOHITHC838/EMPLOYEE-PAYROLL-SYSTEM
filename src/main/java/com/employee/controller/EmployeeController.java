package com.employee.controller;

import com.employee.dao.EmployeeDAO;
import com.employee.view.EmployeeView;
import java.util.List;
import java.util.Scanner;

import com.employee.model.Employee;


public class EmployeeController {

    private EmployeeDAO employeeDAO;
    private EmployeeView employeeView;

    public EmployeeController(Scanner sc) {
        employeeDAO = new EmployeeDAO();
        employeeView = new EmployeeView(sc);
    }

    public void start() {

        int choice;

        do {
            employeeView.showEmployeeMenu();

            choice = employeeView.getChoice();

            switch (choice) {

                case 1:
                    Employee emp = employeeView.getEmployeeDetails();
                    boolean added = employeeDAO.addEmployee(emp);
                    if (added) {
                        System.out.println("Employee Added Successfully!");
                    } else {
                        System.out.println("Employee Addition Failed!");
                    }
                    break;

                case 2:
                    List<Employee> employees = employeeDAO.getAllEmployees();
                    employeeView.displayEmployees(employees);
                    break;

                case 3:

                    int id = employeeView.getEmployeeId();

                    Employee emps = employeeDAO.getEmployeeById(id);

                    if (emps != null) {

                        System.out.println("\nEmployee Found!");

                        emps.displayEmployee();

                    } else {

                        System.out.println("Employee Not Found!");

                    }

                    break;

                case 4:

                    int updateId = employeeView.getEmployeeId();

                    Employee existingEmployee =
                            employeeDAO.getEmployeeById(updateId);

                    if (existingEmployee != null) {

                        System.out.println("Employee Found!");

                        Employee updatedEmployee =
                                employeeView.getUpdatedEmployeeDetails();

                        boolean updated =
                                employeeDAO.updateEmployee(updateId, updatedEmployee);

                        if (updated) {
                            System.out.println("Employee Updated Successfully!");
                        } else {
                            System.out.println("Employee Update Failed!");
                        }

                    } else {

                        System.out.println("Employee Not Found!");

                    }

                    break;

                case 5:

                    employeeView.showDeleteMessage();

                    int deleteId = employeeView.getEmployeeId();

                    Employee deleteEmployee =
                            employeeDAO.getEmployeeById(deleteId);

                    if (deleteEmployee != null) {

                        deleteEmployee.displayEmployee();

                        System.out.print("Are you sure you want to delete? (Y/N): ");

                        String confirmation = employeeView.getConfirmation();

                        if (confirmation.equalsIgnoreCase("Y")) {

                            boolean deleted = employeeDAO.deleteEmployee(deleteId);

                            if (deleted) {
                                System.out.println("Employee Deleted Successfully!");
                            } else {
                                System.out.println("Employee Deletion Failed!");
                            }

                        } else {
                            System.out.println("Delete Operation Cancelled!");
                        }

                    } else {

                        System.out.println("Employee Not Found!");

                    }

                    break;

                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 6);
    }
}