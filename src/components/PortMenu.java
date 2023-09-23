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
                System.out.println("5. View the location of a Port");
                System.out.println("6. View the capacity of a Port");
                System.out.println("7. View the current trips in a Port");
                System.out.println("8. View the current vehicles in a Port");
                System.out.println("9. View the current containers in a Port");
                System.out.println("10. Get total vehicles in a Port");
                System.out.println("11. Get total containers in a Port");
                System.out.println("12. Calculate the distance between two ports");
                System.out.println("13. View the traffic record of a Port");
                System.out.println("14. Back to Main Menu");
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
                        String portIDToDelete = scanner.nextLine();
                        if (systemAdmin.deletePort(portIDToDelete)) {
                            System.out.println("Port with ID " + portIDToDelete + " has been deleted.");
                        } else {
                            System.out.println("Delete failed.");
                        }
                        break;
                    case 5:
                        System.out.println("---------------------VIEW PORT LOCATION---------------------");
                        System.out.println("Enter the ID of the port you want to view its location: ");
                        String portIDViewLocation = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDViewLocation) == null) {
                            System.out.println("Port with ID " + portIDViewLocation + " does not exist.");
                            break;
                        }
                        Port portViewLocation = systemAdmin.getPortById(portIDViewLocation);
                        System.out.println("Port with ID " + portIDViewLocation + " is at the latitude "
                                + portViewLocation.getLatitude() + " and the longitude "
                                + portViewLocation.getLongitude() + ".");
                        break;
                    case 6:
                        System.out.println("---------------------VIEW PORT CAPACITY---------------------");
                        System.out.println("Enter the ID of the port you want to view its : ");
                        String portIDViewCapacity = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDViewCapacity) == null) {
                            System.out.println("Port with ID " + portIDViewCapacity + " does not exist.");
                            break;
                        }
                        Port portViewCapacity = systemAdmin.getPortById(portIDViewCapacity);
                        System.out.println("Port with ID " + portIDViewCapacity + " is currently at "
                                + portViewCapacity.getCurrentCapacity()
                                + " current capacity with a maximum capacity of "
                                + portViewCapacity.getStoringCapacity() + ".");
                        break;
                    case 7:
                        System.out.println("---------------------VIEW PORT CURRENT TRIPS---------------------");
                        System.out.println("Enter the ID of the port you want to view its : ");
                        String portIDViewTrips = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDViewTrips) == null) {
                            System.out.println("Port with ID " + portIDViewTrips + " does not exist.");
                            break;
                        }
                        Port portViewTrips = systemAdmin.getPortById(portIDViewTrips);
                        System.out
                                .println("Port with ID " + portIDViewTrips + " currently has these following trips :");
                        portViewTrips.getTripList().forEach(System.out::println);
                        break;
                    case 8:
                        System.out.println("---------------------VIEW PORT CURRENT VEHICLES---------------------");
                        System.out.println("Enter the ID of the port you want to view its : ");
                        String portIDViewVehicles = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDViewVehicles) == null) {
                            System.out.println("Port with ID " + portIDViewVehicles + " does not exist.");
                            break;
                        }
                        Port portViewVehicles = systemAdmin.getPortById(portIDViewVehicles);
                        System.out.println(
                                "Port with ID " + portIDViewVehicles + " currently has these following vehicles :");
                        portViewVehicles.getTripList().forEach(System.out::println);
                        break;
                    case 9:
                        System.out.println("---------------------VIEW PORT CURRENT CONTAINERS---------------------");
                        System.out.println("Enter the ID of the port you want to view its : ");
                        String portIDViewContainers = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDViewContainers) == null) {
                            System.out.println("Port with ID " + portIDViewContainers + " does not exist.");
                            break;
                        }
                        Port portViewContainers = systemAdmin.getPortById(portIDViewContainers);
                        System.out.println(
                                "Port with ID " + portIDViewContainers + " currently has these following containers :");
                        portViewContainers.getContainerList().forEach(System.out::println);
                        break;
                    case 10:
                        System.out.println("---------------------COUNT PORT TOTAL VEHICLES---------------------");
                        System.out.println("Enter the ID of the port you want to count its total vehicles: ");
                        String portIDTotalVehicles = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDTotalVehicles) == null) {
                            System.out.println("Port with ID " + portIDTotalVehicles + " does not exist.");
                            break;
                        }
                        Port portTotalVehicels = systemAdmin.getPortById(portIDTotalVehicles);
                        System.out.println("Port with ID " + portIDTotalVehicles + " has a total vehicle count of "
                                + Integer.toString(portTotalVehicels.getTotalVehicleCount()) + ".");
                        break;
                    case 11:
                        System.out.println("---------------------COUNT PORT TOTAL CONTAINERS---------------------");
                        System.out.println("Enter the ID of the port you want to count its total containers: ");
                        String portIDTotalContainers = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDTotalContainers) == null) {
                            System.out.println("Port with ID " + portIDTotalContainers + " does not exist.");
                            break;
                        }
                        Port portTotalContainers = systemAdmin.getPortById(portIDTotalContainers);
                        System.out.println("Port with ID " + portIDTotalContainers + " has a total vehicle count of "
                                + Integer.toString(portTotalContainers.getTotalContainerCount()) + ".");
                        break;
                    case 12:
                        System.out.println("---------------------CALCULATE PORTS DISTANCE---------------------");
                        System.out.println(
                                "Enter the IDs of the two ports you want to calculate the distance between (separated by a coma): ");
                        String portIDsCalDistanceInput = scanner.nextLine();
                        String[] portIDsCalculateDistance = portIDsCalDistanceInput.split(",");
                        for (String portIDCalDistance : portIDsCalculateDistance) {
                            if (systemAdmin.getPortById(portIDCalDistance) == null) {
                                System.out.println("Port with ID " + portIDCalDistance + " does not exist.");
                                break;
                            }
                        }
                        Port port1 = systemAdmin.getPortById(portIDsCalculateDistance[0]);
                        Port port2 = systemAdmin.getPortById(portIDsCalculateDistance[1]);
                        double distanceBetweenPorts = port1.calculateDistanceFromPort(port1, port2);
                        System.out.println("Distance between port with ID " + port1.getID() + " and port "
                                + port2.getID() + " is : " + distanceBetweenPorts);
                        break;
                    case 13:
                        System.out.println("---------------------VIEW PORT TRAFFIC RECORD---------------------");
                        System.out.println("Enter the ID of the port you want to view its : ");
                        String portIDViewTrafficRecord = scanner.nextLine();
                        if (systemAdmin.getPortById(portIDViewTrafficRecord) == null) {
                            System.out.println("Port with ID " + portIDViewTrafficRecord + " does not exist.");
                            break;
                        }
                        Port portViewTrafficRecord = systemAdmin.getPortById(portIDViewTrafficRecord);
                        System.out.println(
                                "Port with ID " + portIDViewTrafficRecord + " has the following traffic records : ");
                        portViewTrafficRecord.getTrafficRecord().forEach(System.out::println);
                        break;
                    case 14:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
