package components;

import entities.container.Container;
import entities.port.Port;
import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;
import entities.vehicle.Vehicle;
import service.Vehicle.implementation.VehicleImplement;
import utils.ClassCreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                System.out.println("8. View the carrying capacity of a Vehicle");
                System.out.println("9. View the fuel capacity of a Vehicle");
                System.out.println("10. View all containers on vehicle");
                System.out.println("");
                System.out.println("");
                System.out.println("12. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("---------------------VIEW ALL VEHICLES---------------------");
                        systemAdmin.getAllVehicles().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("---------------------CREATE A VEHICLE---------------------");
                        try {
                            Vehicle newVehicle = ClassCreation.createVehicleFromUserInput(Optional.empty());
                            systemAdmin.createVehicle(newVehicle);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("---------------------UPDATE A VEHICLE---------------------");
                        System.out.println("Enter the ID of the vehicle you want to update: ");
                        String vehicleIDToUpdate = scanner.nextLine();
                        if (systemAdmin.getVehicleById(vehicleIDToUpdate) == null) {
                            System.out.println("Vehicle with ID " + vehicleIDToUpdate + " does not exist.");
                            break;
                        }
                        try {
                            Vehicle updatedVehicle = ClassCreation
                                    .createVehicleFromUserInput(Optional.of(vehicleIDToUpdate));
                            systemAdmin.updateVehicle(updatedVehicle);
                            System.out.println("Vehicle with ID " + vehicleIDToUpdate + " has been updated.");
                            System.out.println(
                                    "Updated vehicle information: " + systemAdmin.getVehicleById(vehicleIDToUpdate));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("---------------------DELETE A VEHICLE--------------------");
                        System.out.println("Enter the ID of the vehicle you want to delete: ");
                        String deletingVehicleID = scanner.nextLine();
                        if (systemAdmin.deleteVehicle(deletingVehicleID)) {
                            System.out.println("Vehicle with ID " + deletingVehicleID + " has been deleted.");
                        } else {
                            System.out.println("Delete failed.");
                        }
                        break;
                    case 5:
                        System.out.println("---------------------REFUEL A VEHICLE--------------------");
                        System.out.println("Enter the ID of the vehicle you want to refuel: ");
                        String refuelingVehicleID = scanner.nextLine();
                        if (systemAdmin.getVehicleById(refuelingVehicleID) == null) {
                            System.out.println("Vehicle with ID " + refuelingVehicleID + " does not exist.");
                            break;
                        }
                        try {
                            Vehicle refuelingVehicle = systemAdmin.getVehicleById(refuelingVehicleID);
                            refuelingVehicle.setCurrentFuel(refuelingVehicle.getFuelTankCapacity());
                            systemAdmin.updateVehicle(refuelingVehicle);
                            System.out.println("Vehicle with ID " + refuelingVehicleID + " has been updated.");
                            System.out.println("Current fuel level for vehicle with ID " + refuelingVehicleID + " : "
                                    + Double.toString(refuelingVehicle.getCurrentFuel()));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("---------------------LOAD A VEHICLE--------------------");
                        System.out.println("Enter the ID of the vehicle you want to load: ");
                        String loadingVehicleID = scanner.nextLine();

// Check if loading vehicle exists
                        Vehicle loadingVehicle = systemAdmin.getVehicleById(loadingVehicleID);
                        if (loadingVehicle == null) {
                            System.out.println("Vehicle with ID " + loadingVehicleID + " does not exist.");
                        } else {
                            Port loadingPort = loadingVehicle.getCurrentPort();
                            List<Container> loadingPortContainerList = loadingPort.getContainerList();

                            while (true) {
                                System.out.println("List of containers on Port with ID "+ loadingPort.getName());
                                loadingPortContainerList.forEach(System.out::println);
                                System.out.println("Enter the ID of the container you want to load (or 'done' to finish): ");
                                String loadingContainerID = scanner.nextLine();

                                if (loadingContainerID.equalsIgnoreCase("done")) {
                                    break; // Exit the loop if the user is done loading containers
                                }

                                // Check if loading container ID is valid
                                Container loadingContainer = systemAdmin.getByContainerId(loadingContainerID);
                                if (loadingContainer == null) {
                                    System.out.println("Container with ID " + loadingContainerID + " does not exist.");

                                } else if (loadingVehicle.getContainerList().contains(loadingContainer)) {
                                    System.out.println("Container with ID " + loadingContainerID +
                                            " is already on the loading vehicle.");

                                } else if (!loadingPortContainerList.contains(loadingContainer)) {
                                    System.out.println("The loading container with ID " + loadingContainerID +
                                            " is not available at the " + loadingPort.getName() +
                                            " port which the vehicle with ID " + loadingVehicleID + " is currently on.");

                                } else {
                                    // Attempt to load the container onto the vehicle
                                    if (loadingVehicle.loadContainer(loadingContainer)) {
                                        // Update the container lists
                                        loadingPortContainerList.remove(loadingContainer);
                                        loadingContainer.setLocation(loadingVehicleID);
                                        systemAdmin.updateContainer(loadingContainer);
                                        systemAdmin.updatePort(loadingPort);
                                        System.out.println("Container with ID " + loadingContainerID +
                                                " has been loaded onto the vehicle with ID " + loadingVehicleID + ".");
                                        // Display the current container list of the vehicle
                                        System.out.println("Current containers list of vehicle with ID " + loadingVehicleID + " :");
                                        loadingVehicle.getContainerList().forEach(System.out::println);
                                        break;
                                    } else {
                                        System.out.println("Error loading container with ID " + loadingContainerID + ".");
                                    }
                                }
                            }
                        }

                    case 7:
                        System.out.println("---------------------UNLOAD A VEHICLE--------------------");
                        System.out.println("Enter the ID of the vehicle you want to unload: ");
                        String unloadingVehicleID = scanner.nextLine();
                        // Check if unloading vehicle exists
                        if (systemAdmin.getVehicleById(unloadingVehicleID) == null) {
                            System.out.println("Vehicle with ID " + unloadingVehicleID + " does not exist.");
                            break;
                        }
                        Vehicle unloadingVehicle = systemAdmin.getVehicleById(unloadingVehicleID);
                        VehicleImplement vehicleImplement = new VehicleImplement(unloadingVehicle);
                        if (vehicleImplement.unloadContainer()) {
                            System.out.println("All containers have been unloaded from the vehicle with ID " + unloadingVehicleID + ". The vehicle is now empty. All containers have been moved to the port "+ unloadingVehicle.getCurrentPort().getName() + ".");
                        } else {
                            System.out.println("Error unloading containers from the vehicle with ID " + unloadingVehicleID + ".");
                        }

                        break;
                    case 8:
                        System.out.println("---------------------VIEW THE CARRYING CAPACITY OF A VEHICLE---------------------");
                        System.out.println("Enter the ID of the vehicle you want to view the carrying capacity of: ");
                        String carryingCapacityVehicleID = scanner.nextLine();
                        Vehicle carryingCapacityVehicle = systemAdmin.getVehicleById(carryingCapacityVehicleID);
                        if (carryingCapacityVehicle == null) {
                            System.out.println("Vehicle with ID " + carryingCapacityVehicleID + " does not exist.");
                            break;
                        }
                        System.out.println("Carrying capacity of vehicle with ID " + carryingCapacityVehicleID + " : "
                                + Double.toString(carryingCapacityVehicle.getCarryingCapacity()));
                        break;
                    case 10:
                        System.out.println("---------------------VIEW ALL CONTAINERS ON VEHICLE---------------------");
                        System.out.println("Enter the ID of the vehicle you want to view the containers of: ");
                        String vehicleID = scanner.nextLine();
                        Vehicle vehicle = systemAdmin.getVehicleById(vehicleID);
                        if (vehicle == null) {
                            System.out.println("Vehicle with ID " + vehicleID + " does not exist.");
                            break;
                        }
                        List<Container> containerList = vehicle.getContainerList();
                        containerList.forEach(System.out::println);
                        break;
                    case 12:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }
        }
    }
}