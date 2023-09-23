import components.AdminMenu;
import components.ManagerMenu;
import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;
import utils.DatFileMethods;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatFileMethods.initDat();
        String groupName = "Coffee Addicts";
        String[] studentNames = {
                "s3979638, Tran Ha Phuong",
                "sXXXXXXX, Tran Nguyen Quoc An",
                "sXXXXXXX, Pham Trong Nghia",
                "sXXXXXXX, Dang Minh Triet",
        };

        printHeader(groupName, studentNames);

        entryMenu();
    }

    public static void printHeader(String groupName, String[] studentNames) {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("CONTAINER PORT MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Dr. Phong Ngo");
        System.out.println("Group: " + groupName);

        // Calculate the maximum name and student ID lengths
        int maxNameLength = 0;
        int maxStudentIDLength = 0;

        for (String studentName : studentNames) {
            String[] parts = studentName.split(", ");
            if (parts.length == 2) {
                String name = parts[1];
                String studentID = parts[0];
                maxNameLength = Math.max(maxNameLength, name.length());
                maxStudentIDLength = Math.max(maxStudentIDLength, studentID.length());
            }
        }

        // Calculate the table width based on the maximum lengths
        int tableWidth = maxNameLength + maxStudentIDLength + 8;

        // Print the table header
        String headerLine = "+-" + "-".repeat(maxNameLength) + "-+-" + "-".repeat(maxStudentIDLength) + "-+";
        System.out.println(headerLine);
        System.out.printf("| %-" + maxNameLength + "s | %-" + maxStudentIDLength + "s |\n", "Name", "Student ID");
        System.out.println(headerLine);

        // Print student names and IDs in the table
        for (String studentName : studentNames) {
            String[] parts = studentName.split(", ");
            if (parts.length == 2) {
                String name = parts[1];
                String studentID = parts[0];
                System.out.printf("| %-" + maxNameLength + "s | %-" + maxStudentIDLength + "s |\n", name, studentID);
            }
        }

        // Print the table footer
        System.out.println(headerLine);
    }
    public static void entryMenu(){
        User user = new User();
        System.out.println("Welcome to Container Port Management System");
        System.out.println("1. Login as System Admin");
        System.out.println("2. Login as Port Manager");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter username: ");
                String username = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                user = user.userLogin(username, password);
                if (user instanceof SystemAdmin) {
                    AdminMenu adminMenu = new AdminMenu((SystemAdmin) user);
                    adminMenu.displayMenu();
                } else {
                    System.out.println("Invalid username or password");
                }
                break;
            case 2:
                System.out.println("Enter username: ");
                username = scanner.nextLine();
                System.out.println("Enter password: ");
                password = scanner.nextLine();
                user = user.userLogin(username, password);
                if (user instanceof PortManager) {
                    ManagerMenu managerMenu = new ManagerMenu((PortManager) user);
                    managerMenu.displayMenu();
                } else {
                    System.out.println("Invalid username or password");
                }
                break;
            case 3:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }


}
