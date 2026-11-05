package com.employee.controller;

import com.employee.view.MainMenu;

import java.util.Scanner;

public class MainController {

    private MainMenu mainMenu;
    private EmployeeController employeeController;
    private DepartmentController departmentController;
    private AttendanceController attendanceController;
    private LeaveController leaveController;
    private PayrollController payrollController;


    public MainController(Scanner sc) {

        mainMenu = new MainMenu(sc);

        employeeController = new EmployeeController(sc);

        departmentController = new DepartmentController(sc);

        attendanceController = new AttendanceController(sc);

        leaveController = new LeaveController(sc);

        payrollController = new PayrollController(sc);
    }

    public void start() {

        int choice;

        do {

            mainMenu.showMainMenu();

            choice = mainMenu.getChoice();

            switch (choice) {

                case 1:

                    employeeController.start();

                    break;

                case 2:

                    departmentController.start();

                    break;
                case 3:

                    attendanceController.start();

                    break;

                case 4:
                    leaveController.start();
                    break;

                case 5:
                    payrollController.start();
                    break;

                case 6:

                    System.out.println("Thank You for Using B Employee Payroll System!");

                    break;

                default:

                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 6);
    }
}