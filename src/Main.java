import components.AdminMenu;
import components.ManagerMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        displayHeader();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    if (loginAdmin()) {
                        System.out.println("Logged in as Admin.");
                        // Add Admin-specific functionality here
                    } else {
                        System.out.println("Invalid username or password for Admin.");
                    }
                    break;
                case 2:
                    if (loginManager()) {
                        System.out.println("Logged in as Port Manager.");
                        // Add Port Manager-specific functionality here
                    } else {
                        System.out.println("Invalid username or password for Port Manager.");
                    }
                    break;
                case 3:
                    System.out.println("Good bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayHeader() {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("CONTAINER PORT MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Dr. Phong Ngo");
        System.out.println("Group: Coffee Addict");
        System.out.println("s3974975,Pham Trong Nghia");
        System.out.println("s3979638,Tran Ha Phuong");
        System.out.println("s3959931,Tran Nguyen Quoc An");
        System.out.println("s4022878,Dang Minh Triet");
        System.out.println("-----------------------");
    }

    private static void displayMenu() {
        System.out.println("Choose your status:");
        System.out.println("[1] Admin");
        System.out.println("[2] Port Manager");
        System.out.println("[3] Out process");
        System.out.print("Your answer: ");
    }

    private static boolean loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        AdminMenu adminMenu = new AdminMenu();

        System.out.print("Enter Admin username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter Admin password: ");
        String enteredPassword = scanner.nextLine();

        return enteredUsername.equals(adminMenu.get_AD_Username()) && enteredPassword.equals(adminMenu.get_AD_Password());
    }

    private static boolean loginManager() {
        Scanner scanner = new Scanner(System.in);
        ManagerMenu managerMenu = new ManagerMenu();

        System.out.print("Enter Port Manager username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter Port Manager password: ");
        String enteredPassword = scanner.nextLine();

        return enteredUsername.equals(managerMenu.get_PM_Username()) && enteredPassword.equals(managerMenu.get_PM_Password());
    }
}
