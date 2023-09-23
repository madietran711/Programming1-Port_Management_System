package components;

import entities.port.Port;
import entities.trip.Trip;
import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;
import utils.ClassCreation;

import java.util.Optional;
import java.util.Scanner;

public class TripMenu {
    public static void displayMenu(User systemAdmin) {
        if (systemAdmin instanceof PortManager) {
            System.out.println("Not authorized to access this menu.");
        } else if (systemAdmin instanceof SystemAdmin) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Manage Trips Menu:");
                System.out.println("1. View All Trips");
                System.out.println("2. Create a Trip");
                System.out.println("3. Update a Trip");
                System.out.println("4. Delete a Trip");
                System.out.println();
                System.out.println();
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("---------------------VIEW ALL TRIPS---------------------");
                        systemAdmin.getAllTrips().forEach(System.out::println);
                        break;
                    case 2:

                        System.out.println("---------------------CREATE A TRIP---------------------");
                        try {
                            Trip newTrip = ClassCreation.createTripFromUserInput(Optional.empty());
                            systemAdmin.createTrip(newTrip);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:

                        System.out.println("---------------------UPDATE A TRIP---------------------");
                        System.out.println("Enter the ID of the trip you want to update: ");
                        String tripIDToUpdate = scanner.nextLine();
                        if (systemAdmin.getTripById(tripIDToUpdate) == null) {
                            System.out.println("Trip with ID " + tripIDToUpdate + " does not exist.");
                            break;
                        }
                        try {
                            Trip updatedTrip = ClassCreation.createTripFromUserInput(Optional.of(tripIDToUpdate));
                            systemAdmin.updateTrip(updatedTrip);
                            System.out.println("Trip with ID " + tripIDToUpdate + " has been updated.");
                            System.out.println("Updated trip information: " + systemAdmin.getTripById(tripIDToUpdate));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:

                        System.out.println("---------------------DELETE A TRIP--------------------");
                        System.out.println("Enter the ID of the trip you want to delete: ");
                        String tripID = scanner.nextLine();
                        if (systemAdmin.deleteTrip(tripID)) {
                            System.out.println("Trip with ID " + tripID + " has been deleted.");
                        } else {
                            System.out.println("Delete failed.");
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                scanner.close();
            }
        }
    }
}
