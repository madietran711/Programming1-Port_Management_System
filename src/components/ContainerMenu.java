package components;

import entities.container.*;
import entities.user.User;
import utils.ClassCreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContainerMenu {
    public static void displayMenu(User user) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>> DISPLAYING [CONTAINER] MANAGEMENT MENU <<<<<<<<<<<<<<<<<<<<<");
            System.out.println("1. View All Containers");
            System.out.println("2. Create a Container");
            System.out.println("3. Update a Container");
            System.out.println("4. Delete a Container");
            System.out.println("5. View All Containers by type");
            System.out.println("6. Calculate total weight of each container type");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("---------------------VIEW ALL CONTAINERS---------------------");
                    user.getAllContainers().forEach(System.out::println);
                    break;

                case 2:
                    System.out.println("---------------------CREATE A CONTAINER---------------------");
                    try {
                        Container newContainer = ClassCreation.createContainerFromUserInput(Optional.empty());
                        user.createContainer(newContainer);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("---------------------UPDATE A CONTAINER---------------------");
                    System.out.println("Enter the ID of the container you want to update: ");
                    String containerIDToUpdate = scanner.nextLine();
                    if (user.getByContainerId(containerIDToUpdate) == null) {
                        System.out.println("Container with ID " + containerIDToUpdate + " does not exist.");
                        break;
                    }
                    try {
                        Container container = user.getByContainerId(containerIDToUpdate);
                        Container updatedContainer = ClassCreation.createContainerFromUserInput(Optional.of(container));
                        user.updateContainer(updatedContainer);
                        System.out.println("Container with ID " + containerIDToUpdate + " has been updated.");
                        System.out.println(
                                "Updated container information: " + user.getByContainerId(containerIDToUpdate));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("---------------------DELETE A CONTAINERS---------------------");
                    System.out.println("Enter the ID of the container you want to delete: ");
                    String containerID = scanner.nextLine();
                    if (user.deleteContainer(containerID)) {
                        System.out.println("Container with ID " + containerID + " has been deleted.");
                    } else {
                        System.out.println("Delete failed.");
                    }
                    break;

                case 5:
                    System.out.println("---------------------VIEW CONTAINER BY TYPES---------------------");
                    List<Container> containers1 = user.getAllContainers();
                    List<Container> dryContainers = new ArrayList<Container>();
                    List<Container> wetContainers = new ArrayList<Container>();
                    List<Container> oSideContainers = new ArrayList<Container>();
                    List<Container> oTopContainers = new ArrayList<Container>();
                    List<Container> coldContainers = new ArrayList<Container>();
                    for (Container container : containers1) {
                        if (container instanceof DryStorageContainer) {
                            dryContainers.add((DryStorageContainer) container);
                        } else if (container instanceof LiquidContainer) {
                            wetContainers.add((LiquidContainer) container);
                        } else if (container instanceof OpenSideContainer) {
                            oSideContainers.add((OpenSideContainer) container);
                        } else if (container instanceof OpenTopContainer) {
                            oTopContainers.add((OpenTopContainer) container);
                        } else if (container instanceof RefridgeratedContainer) {
                            coldContainers.add((RefridgeratedContainer) container);
                        }
                    }
                    System.out.println("List of Dry Storage Containers : ");
                    dryContainers.forEach(System.out::println);
                    System.out.println("List of Liquid Storage Containers : ");
                    wetContainers.forEach(System.out::println);
                    System.out.println("List of Open Side Storage Containers : ");
                    oSideContainers.forEach(System.out::println);
                    System.out.println("List of Open Top Storage Containers : ");
                    oTopContainers.forEach(System.out::println);
                    System.out.println("List of Refridgerated Storage Containers : ");
                    coldContainers.forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("---------------------CALCULATE TOTAL CONTAINER BY TYPES---------------------");
                    List<Container> containers2 = user.getAllContainers();
                    double dryContainersWeight = 0.0;
                    double wetContainersWeight = 0.0;
                    double oSideContainersWeight = 0.0;
                    double oTopContainersWeight = 0.0;
                    double coldContainersWeight = 0.0;
                    for (Container container : containers2) {
                        if (container instanceof DryStorageContainer) {
                            dryContainersWeight += container.getWeight();
                        } else if (container instanceof LiquidContainer) {
                            wetContainersWeight += container.getWeight();
                        } else if (container instanceof OpenSideContainer) {
                            oSideContainersWeight += container.getWeight();
                        } else if (container instanceof OpenTopContainer) {
                            oTopContainersWeight += container.getWeight();
                        } else if (container instanceof RefridgeratedContainer) {
                            coldContainersWeight += container.getWeight();
                        }
                    }
                    System.out.println("Total Dry Storage Containers weight : " + Double.toString(dryContainersWeight));
                    System.out.println(
                            "Total Liquid Storage Containers weight : " + Double.toString(wetContainersWeight));
                    System.out.println(
                            "Total Open Side Storage Containers weight : " + Double.toString(oSideContainersWeight));
                    System.out.println(
                            "Total Open Top Storage Containers weight : " + Double.toString(oTopContainersWeight));
                    System.out.println(
                            "Total Refridgerated Storage Containers weight : " + Double.toString(coldContainersWeight));
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
