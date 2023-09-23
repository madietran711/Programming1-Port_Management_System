package components;

import entities.port.Port;
import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;
import utils.ClassCreation;

import java.util.Optional;
import java.util.Scanner;

public class PortMenu {
    public static void displayMenu(User systemAdmin) {
        if (systemAdmin instanceof PortManager) {
            System.out.println("Not authorized to access this menu.");
        } else if (systemAdmin instanceof SystemAdmin) {

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Manage Ports Menu:");
                System.out.println("1. View All Ports");
                System.out.println("2. Create a Port");
                System.out.println("3. Update a Port");
                System.out.println("4. Delete a Port");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("---------------------VIEW ALL PORTS---------------------");
                        // View all ports
                        systemAdmin.getAllPorts().forEach(System.out::println);
                        break;
                    case 2:
                        // Create a new port
                        System.out.println("---------------------CREATE A PORT---------------------");
                        try {
                            Port newPort = ClassCreation.createPortFromUserInput(Optional.empty());
                            systemAdmin.createPort(newPort);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        // Update a port
                        System.out.println("---------------------UPDATE A PORT---------------------");
                        System.out.println("Enter the ID of the port you want to update: ");
                        String portIDToUpdate = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDToUpdate) == null) {
                            System.out.println("Port with ID " + portIDToUpdate + " does not exist.");
                            break;
                        }
                        try {
                            Port updatedPort = ClassCreation.createPortFromUserInput(Optional.of(portIDToUpdate));
                            systemAdmin.updatePort(updatedPort);
                            System.out.println("Port with ID " + portIDToUpdate + " has been updated.");
                            System.out.println("Updated port information: " + systemAdmin.getPortById(portIDToUpdate));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        // Delete a port
                        System.out.println("---------------------DELETE A PORT---------------------");
                        System.out.println("Enter the ID of the port you want to delete: ");
                        String portID = scanner.nextLine();
                        if (systemAdmin.deletePort(portID)) {
                            System.out.println("Port with ID " + portID + " has been deleted.");
                        } else {
                            System.out.println("Delete failed.");
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
