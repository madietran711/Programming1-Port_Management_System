package components;

import entities.user.SystemAdmin;
import java.util.Scanner;

public class AdminMenu {

    private SystemAdmin systemAdmin;
    private Scanner scanner;

    public AdminMenu(SystemAdmin systemAdmin) {
        this.systemAdmin = systemAdmin;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("| System Admin Menu:                                          |");
            System.out.println("| 1. Manage Ports                                             |");
            System.out.println("| 2. Manage Containers                                        |");
            System.out.println("| 3. Manage Trips                                            |");
            System.out.println("| 4. Manage Vehicles                                         |");
            System.out.println("| 5. View statistical data                                   |");
            System.out.println("| 6. Exit                                                    |");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    managePorts();
                    break;

                case 2:
                    manageContainers();
                    break;
                case 3:
                    manageTrips();
                    break;
                case 4:
                    manageVehicles();
                    break;

                case 5:
                    // View statistical data
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void managePorts() {
        PortMenu.displayMenu(systemAdmin);
    }

    private void manageContainers() {
        ContainerMenu.displayMenu(systemAdmin);
    }

    private void manageTrips() {
        TripMenu.displayMenu(systemAdmin);
    }

    private void manageVehicles() {
        VehicleMenu.displayMenu(systemAdmin);
    }

    public static void main(String[] args) {
        SystemAdmin systemAdmin = new SystemAdmin("admin123", "admin", "password"); // Create a SystemAdmin instance
        AdminMenu adminMenu = new AdminMenu(systemAdmin);

         // Display the System Admin menu
        adminMenu.displayMenu();
    }
}
