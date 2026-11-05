package com.employee;

import com.employee.controller.MainController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MainController controller = new MainController(sc);

        controller.start();

        sc.close();
    }
}