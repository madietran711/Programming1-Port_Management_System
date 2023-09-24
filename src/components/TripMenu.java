package components;

import entities.port.Port;
import entities.trip.Trip;
import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;
import utils.ClassCreation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TripMenu {
    public static void displayMenu(User systemAdmin) {
        if (systemAdmin instanceof PortManager) {
            System.out.println("Not authorized to access this menu.");
        } else if (systemAdmin instanceof SystemAdmin) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>> DISPLAYING [TRIP] MANAGEMENT MENU <<<<<<<<<<<<<<<<<<<<<");
                System.out.println("1. View All Trips");
                System.out.println("2. Create a Trip");
                System.out.println("3. Update a Trip");
                System.out.println("4. Delete a Trip");
                System.out.println("5. View the tracking Vehicle of a Trip");
                System.out.println("6. View the departure & arrival Dates of a Trip");
                System.out.println("7. View the departure & arrival Ports of a Trip");
                System.out.println("8. View the status of a Trip");
                System.out.println("9. View all Trips running on a Date");
                System.out.println("10. View all Trips running in a time period");
                System.out.println("11. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
            scanner.nextLine();

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
                        System.out.println("Enter the ID of the Trip you want to update: ");
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
                        System.out.println("Enter the ID of the Trip you want to delete: ");
                        String tripID = scanner.nextLine();
                        if (systemAdmin.deleteTrip(tripID)) {
                            System.out.println("Trip with ID " + tripID + " has been deleted.");
                        } else {
                            System.out.println("Delete failed.");
                        }
                        break;

                    case 5:
                        System.out.println("---------------------VIEW TRIP TRACKING VEHICLE---------------------");
                        System.out.println("Enter the ID of the Trip you want to view its tracking Vehicle: ");
                        String tripIDTrackingVehicle = scanner.nextLine();
                        if (systemAdmin.getTripById(tripIDTrackingVehicle) == null) {
                            System.out.println("Trip with ID " + tripIDTrackingVehicle + " does not exist.");
                            break;
                        }
                        Trip tripTrackingVehicle = systemAdmin.getTripById(tripIDTrackingVehicle);
                        System.out.println(
                                "Trip with ID " + tripIDTrackingVehicle + " is currently tracking vehicle with ID "
                                        + tripTrackingVehicle.getTrackingVehicle().getID());
                        break;

                    case 6:
                        System.out.println(
                                "---------------------VIEW TRIP DEPARTURE & ARRIVAL DATES---------------------");
                        System.out.println("Enter the ID of the Trip you want to view its departure & arrival Dates: ");
                        String tripIDdaDate = scanner.nextLine();
                        if (systemAdmin.getTripById(tripIDdaDate) == null) {
                            System.out.println("Trip with ID " + tripIDdaDate + " does not exist.");
                            break;
                        }
                        Trip tripdaDate = systemAdmin.getTripById(tripIDdaDate);
                        System.out.println("Trip with ID " + tripIDdaDate + " departs on "
                                + tripdaDate.getDepartureDate().toString() + " and arrives on "
                                + tripdaDate.getArrivalDate().toString());
                        break;

                    case 7:
                        System.out.println(
                                "---------------------VIEW TRIP DEPARTURE & ARRIVAL PORTS---------------------");
                        System.out.println("Enter the ID of the Trip you want to view its departure & arrival Ports: ");
                        String tripIDdaPort = scanner.nextLine();
                        if (systemAdmin.getTripById(tripIDdaPort) == null) {
                            System.out.println("Trip with ID " + tripIDdaPort + " does not exist.");
                            break;
                        }
                        Trip tripdaPort = systemAdmin.getTripById(tripIDdaPort);
                        System.out.println("Trip with ID " + tripIDdaPort + " departs from "
                                + tripdaPort.getDeparturePort().getName() + " port and arrives at "
                                + tripdaPort.getArrivalPort().getName() + " port");
                        break;

                    case 8:
                        System.out.println("---------------------VIEW TRIP STATUS---------------------");
                        System.out.println("Enter the ID of the Trip you want to view its status: ");
                        String tripIDViewStatus = scanner.nextLine();
                        if (systemAdmin.getTripById(tripIDViewStatus) == null) {
                            System.out.println("Trip with ID " + tripIDViewStatus + " does not exist.");
                            break;
                        }
                        Trip tripViewStatus = systemAdmin.getTripById(tripIDViewStatus);
                        System.out.println("Trip with ID " + tripIDViewStatus + " current status is : "
                                + tripViewStatus.getStatus().toString());
                        break;

                    case 9:
                        System.out.println("---------------------VIEW ALL TRIPS ON DATE---------------------");
                        System.out.println(
                                "Enter the Date on which you want to view all running Trips (enters year, month, day of month separated by a comma): ");
                        String[] trackingDateInput = scanner.nextLine().split(",");
                        LocalDate trackingDate = LocalDate.of(Integer.parseInt(trackingDateInput[0]),
                                Integer.parseInt(trackingDateInput[1]), Integer.parseInt(trackingDateInput[2]));
                        List<Trip> allTripList1 = systemAdmin.getAllTrips();
                        System.out
                                .println("On the Date " + trackingDate.toString() + " there are the following Trips :");
                        for (Trip trip : allTripList1) {
                            if (trip.getDepartureDate().isBefore(trackingDate)
                                    && trip.getArrivalDate().isAfter(trackingDate)) {
                                System.out.println(trip.getID());
                            }
                        }
                        break;

                    case 10:
                        System.out.println("---------------------VIEW ALL TRIPS BETWEEN DATES---------------------");


                        System.out.println("Enter the starting Date : ");
                        String[] trackingStartingDateInput = scanner.nextLine().split(",");
                        LocalDate trackingStartingDate = LocalDate.of(Integer.parseInt(trackingStartingDateInput[0]),
                                Integer.parseInt(trackingStartingDateInput[1]),
                                Integer.parseInt(trackingStartingDateInput[2]));
                        System.out.println("Enter the ending Date : ");
                        String[] trackingEndingDateInput = scanner.nextLine().split(",");
                        LocalDate trackingEndingDate = LocalDate.of(Integer.parseInt(trackingEndingDateInput[0]),
                                Integer.parseInt(trackingEndingDateInput[1]),
                                Integer.parseInt(trackingEndingDateInput[2]));
                        if (trackingEndingDate.isBefore(trackingStartingDate)) {
                            System.out.println(
                                    "Input error: The tracking period's starting date must be before the ending date!");
                            break;
                        }
                        List<Trip> allTripList2 = systemAdmin.getAllTrips();
                        System.out
                                .println("The time period between " + trackingStartingDate.toString() + " and "
                                        + trackingEndingDate.toString() + " there are the following Trips :");
                        for (Trip trip : allTripList2) {
                            if ((trip.getDepartureDate().isAfter(trackingStartingDate)
                                    && trip.getArrivalDate().isBefore(trackingEndingDate))
                                    || (trip.getDepartureDate().isBefore(trackingStartingDate)
                                            && trip.getArrivalDate().isAfter(trackingEndingDate))
                                    || (trip.getDepartureDate().isBefore(trackingStartingDate)
                                            && trip.getArrivalDate().isAfter(trackingStartingDate)
                                            && trip.getArrivalDate().isBefore(trackingEndingDate))
                                    || (trip.getDepartureDate().isAfter(trackingStartingDate)
                                            && trip.getDepartureDate().isBefore(trackingEndingDate)
                                            && trip.getArrivalDate().isAfter(trackingEndingDate))) {
                                System.out.println(trip.getID());
                            }
                        }
                        break;

                    case 11:
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
