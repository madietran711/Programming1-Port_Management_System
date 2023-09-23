package components;

import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;

import java.util.Scanner;

public class VehicleMenu {
    public static void displayMenu(User systemAdmin) {
        if (systemAdmin instanceof PortManager) {
            System.out.println("Not authorized to access this menu.");
        } else if (systemAdmin instanceof SystemAdmin) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Manage Vehicle Menu:");
                System.out.println("1. View All Vehicles");
                System.out.println("2. Create a Vehicle");
                System.out.println("3. Update a Vehicle");
                System.out.println("4. Delete a Vehicle");
                System.out.println("5. Refuel a Vehicle");
                System.out.println("6. Load containers onto a Vehicle");
                System.out.println("7. Unload containers from a Vehicle");
                System.out.println("8. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("---------------------VIEW ALL VEHICLES---------------------");
                        systemAdmin.getAllVehicles().forEach(System.out::println);
                        break;
                    case 2:

                        System.out.println("---------------------CREATE A VEHICLE---------------------");
                        break;
                    case 3:

                        System.out.println("---------------------UPDATE A VEHICLE---------------------");
                        break;
                    case 4:
                        System.out.println("---------------------DELETE A VEHICLE--------------------");
                        break;
                    case 5:
                        System.out.println("---------------------REFUEL A VEHICLE--------------------");
                        break;
                    case 6:
                        System.out.println("---------------------LOAD A VEHICLE--------------------");
                        break;
                    case 7:
                        System.out.println("---------------------UNLOAD A VEHICLE--------------------");
                        break;
                    case 8:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}