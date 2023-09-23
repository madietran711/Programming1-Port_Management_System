package components;

import entities.container.Container;
import entities.user.User;
import utils.ClassCreation;

import java.util.Optional;
import java.util.Scanner;

public class ContainerMenu {
    public static void displayMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Manage Containers Menu:");
            System.out.println("1. View All Containers");
            System.out.println("2. Create a Container");
            System.out.println("3. Update a Container");
            System.out.println("4. Delete a Container");
            System.out.println();
            System.out.println();
            System.out.println("5. Back to Main Menu");
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
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}
