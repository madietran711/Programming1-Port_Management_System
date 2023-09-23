package components;

import entities.container.Container;
import entities.port.Port;
import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;
import entities.vehicle.Vehicle;
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
                System.out.println("10. View the list of containers of a Vehicle");
                System.out.println("11. View current port of a Vehicle");
                System.out.println("13. View the schedule of a Vehicle");
                System.out.println("14. Back to Main Menu");
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
                            System.out.println("Vehilve with ID " + deletingVehicleID + " has been deleted.");
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
                        if (systemAdmin.getVehicleById(loadingVehicleID) == null) {
                            System.out.println("Vehicle with ID " + loadingVehicleID + " does not exist.");
                            break;
                        }
                        Vehicle loadingVehicle = systemAdmin.getVehicleById(loadingVehicleID);
                        Port loadingPort = loadingVehicle.getCurrentPort();
                        List<Container> loadingPortContainerList = loadingPort.getContainerList();
                        System.out.println("Enter the IDs of the container you want to load (separated by a comma): ");
                        String loadingContainerIDsInput = scanner.nextLine();
                        String[] loadingContainerIDs = loadingContainerIDsInput.split(",");
                        // Check if loading container IDs are valid
                        for (String loadingContainerID : loadingContainerIDs) {
                            if (systemAdmin.getByContainerId(loadingContainerID) == null) {
                                System.out.println("Container with ID " + loadingContainerID + " does not exist.");
                                break;
                            }
                        }
                        // Check if loading containers are already on loading vehicle
                        for (String loadingContainerID : loadingContainerIDs) {
                            Container loadingContainer = systemAdmin.getByContainerId(loadingContainerID);
                            if (loadingVehicle.getContainerList().contains(loadingContainer)) {
                                System.out.println("Container with ID " + loadingContainerID
                                        + " is already on the loading vehicle.");
                                break;
                            } else {
                                continue;
                            }
                        }
                        // Check if loading containers are available at the vehicle's current port
                        for (String loadingContainerID : loadingContainerIDs) {
                            Container loadingContainer = systemAdmin.getByContainerId(loadingContainerID);
                            if (loadingPortContainerList.contains(loadingContainer)) {
                                continue;
                            } else {
                                System.out.println("The  loading container with ID " + loadingContainerID
                                        + " is not available at the " + loadingPort.getName()
                                        + " port which the vehicle with ID " + loadingVehicleID + " is currently on.");
                                break;
                            }
                        }
                        // Loading containers on to vehicle
                        try {
                            ArrayList<Container> loadingVehicleContainerList = loadingVehicle.getContainerList();
                            for (String loadingContainerID : loadingContainerIDs) {
                                Container loadingContainer = systemAdmin.getByContainerId(loadingContainerID);
                                loadingVehicleContainerList.add(loadingContainer);
                                loadingPortContainerList.remove(loadingContainer);
                            }
                            // Update loading vehicle and port with new container list
                            loadingVehicle.setContainerList(loadingVehicleContainerList);
                            systemAdmin.updateVehicle(loadingVehicle);
                            loadingPort.setContainerList(loadingPortContainerList);
                            systemAdmin.updatePort(loadingPort);
                            System.out.println(
                                    "Vehicle with ID " + loadingVehicleID + " has been loaded with new containers.");
                            System.out.println("Current conatiners list of vehicle with ID " + loadingVehicleID + " :");
                            loadingVehicle.getContainerList().forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
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
                        Port unloadingPort = unloadingVehicle.getCurrentPort();
                        List<Container> unloadingPortContainerList = unloadingPort.getContainerList();
                        System.out
                                .println("Enter the IDs of the container you want to unload (separated by a comma): ");
                        String unloadingContainerIDsInput = scanner.nextLine();
                        String[] unloadingContainerIDs = unloadingContainerIDsInput.split(",");
                        // Check if unloading container IDs are valid
                        for (String unloadingContainerID : unloadingContainerIDs) {
                            if (systemAdmin.getByContainerId(unloadingContainerID) == null) {
                                System.out.println("Container with ID " + unloadingContainerID + " does not exist.");
                                break;
                            }
                        }
                        // Check if unloading containers are actually on the unloading vehicle
                        for (String unloadingContainerID : unloadingContainerIDs) {
                            Container unloadingContainer = systemAdmin.getByContainerId(unloadingContainerID);
                            if (unloadingVehicle.getContainerList().contains(unloadingContainer)) {
                                continue;
                            } else {
                                System.out.println("Container with ID " + unloadingContainerID
                                        + " is not on the unloading vehicle.");
                                break;
                            }
                        }
                        // Check if unloading containers already existed at the vehicle's current port
                        for (String unloadingContainerID : unloadingContainerIDs) {
                            Container unloadingContainer = systemAdmin.getByContainerId(unloadingContainerID);
                            if (unloadingPortContainerList.contains(unloadingContainer)) {
                                System.out.println("The  unloading container with ID " + unloadingContainerID
                                        + " already exists at the " + unloadingPort.getName()
                                        + " port which the vehicle with ID " + unloadingVehicleID
                                        + " is currently on.");
                                break;
                            } else {
                                continue;
                            }
                        }
                        // Unloading containers off of vehicle
                        try {
                            ArrayList<Container> unloadingVehicleContainerList = unloadingVehicle.getContainerList();
                            for (String unloadingContainerID : unloadingContainerIDs) {
                                Container unloadingContainer = systemAdmin.getByContainerId(unloadingContainerID);
                                unloadingVehicleContainerList.remove(unloadingContainer);
                                unloadingPortContainerList.add(unloadingContainer);
                            }
                            // Update unloading vehicle and port with new container list
                            unloadingVehicle.setContainerList(unloadingVehicleContainerList);
                            systemAdmin.updateVehicle(unloadingVehicle);
                            unloadingPort.setContainerList(unloadingPortContainerList);
                            systemAdmin.updatePort(unloadingPort);
                            System.out.println(
                                    "Vehicle with ID " + unloadingVehicleID + " has been loaded with new containers.");
                            System.out
                                    .println("Current conatiners list of vehicle with ID " + unloadingVehicleID + " :");
                            unloadingVehicle.getContainerList().forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
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