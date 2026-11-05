package com.employee.view;

import java.util.Scanner;

public class MainMenu {

    private Scanner sc;

    public MainMenu(Scanner sc) {
        this.sc = sc;
    }

    public void showMainMenu() {

        System.out.println("\n================================");
        System.out.println("   B EMPLOYEE PAYROLL SYSTEM");
        System.out.println("================================");

        System.out.println("1. Employee Management");
        System.out.println("2. Department Management");
        System.out.println("3. Attendance Management");
        System.out.println("4. Leave Management");
        System.out.println("5. Payroll Management");
        System.out.println("6. Exit");

        System.out.print("Enter your choice: ");
    }

    public int getChoice() {
        return Integer.parseInt(sc.nextLine());
    }
}