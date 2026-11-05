package com.employee.controller;

import com.employee.dao.DepartmentDAO;
import com.employee.model.Department;
import com.employee.view.DepartmentView;

import java.util.List;
import java.util.Scanner;

public class DepartmentController {

    private DepartmentDAO departmentDAO;
    private DepartmentView departmentView;

    public DepartmentController(Scanner sc) {

        departmentDAO = new DepartmentDAO();

        departmentView = new DepartmentView(sc);
    }

    public void start() {

        int choice;

        do {

            departmentView.showDepartmentMenu();

            choice = departmentView.getChoice();

            switch (choice) {

                // 1. ADD DEPARTMENT
                case 1:

                    String name = departmentView.getDepartmentName();

                    Department newDept = new Department(0, name);

                    boolean added = departmentDAO.addDepartment(newDept);

                    if (added) {
                        System.out.println("Department Added Successfully!");
                    } else {
                        System.out.println("Department Addition Failed!");
                    }

                    break;

                // 2. VIEW ALL DEPARTMENTS
                case 2:

                    List<Department> departments =
                            departmentDAO.getAllDepartments();

                    departmentView.displayDepartments(departments);

                    break;

                // 3. SEARCH DEPARTMENT
                case 3:

                    int searchId = departmentView.getDepartmentId();

                    Department foundDept =
                            departmentDAO.getDepartmentById(searchId);

                    if (foundDept != null) {

                        System.out.println("Department Found!");

                        departmentView.displayDepartment(foundDept);

                    } else {

                        System.out.println("Department Not Found!");
                    }

                    break;

                // 4. UPDATE DEPARTMENT
                case 4:

                    int updateId = departmentView.getDepartmentId();

                    Department existingDept =
                            departmentDAO.getDepartmentById(updateId);

                    if (existingDept != null) {

                        System.out.println("Department Found!");

                        String updatedName =
                                departmentView.getDepartmentName();

                        Department updatedDept =
                                new Department(updateId, updatedName);

                        boolean updated =
                                departmentDAO.updateDepartment(updateId, updatedDept);

                        if (updated) {
                            System.out.println("Department Updated Successfully!");
                        } else {
                            System.out.println("Department Update Failed!");
                        }

                    } else {

                        System.out.println("Department Not Found!");
                    }

                    break;

                // 5. DELETE DEPARTMENT
                case 5:

                    int deleteId = departmentView.getDepartmentId();

                    Department deleteDept =
                            departmentDAO.getDepartmentById(deleteId);

                    if (deleteDept != null) {

                        departmentView.displayDepartment(deleteDept);

                        String confirmation =
                                departmentView.getConfirmation();

                        if (confirmation.equalsIgnoreCase("Y")) {

                            boolean deleted =
                                    departmentDAO.deleteDepartment(deleteId);

                            if (deleted) {
                                System.out.println("Department Deleted Successfully!");
                            } else {
                                System.out.println("Department Deletion Failed!");
                            }

                        } else {

                            System.out.println("Delete Operation Cancelled!");
                        }

                    } else {

                        System.out.println("Department Not Found!");
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