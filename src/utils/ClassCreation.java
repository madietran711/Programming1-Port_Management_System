package utils;

import entities.container.*;
import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.Vehicle;
import enums.TripStatus;
import service.Port.implementation.PortImplement;
import service.Vehicle.implementation.VehicleImplement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ClassCreation {

    private static Map<Class<? extends Container>, Integer> containerTypeToChoice = new HashMap<>();
     static {

        // Initialize the mapping
        containerTypeToChoice.put(DryStorageContainer.class, 1);
        containerTypeToChoice.put(LiquidContainer.class, 2);
        containerTypeToChoice.put(OpenSideContainer.class, 3);
        containerTypeToChoice.put(OpenTopContainer.class, 4);
        containerTypeToChoice.put(RefridgeratedContainer.class, 5);
    }
    public static Port createPortFromUserInput(Optional<String> portID) {
        Scanner scanner = new Scanner(System.in);
        String ID = portID.orElse("");
        if (portID.isPresent()) {
            System.out.println("Enter information to update Port with ID " + portID.get() + ":");
        } else {
            System.out.println("Enter Port ID: ");
            ID = scanner.nextLine();
        }

        System.out.println("Enter Port Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Latitude: ");
        double latitude = scanner.nextDouble();

        System.out.println("Enter Longitude: ");
        double longitude = scanner.nextDouble();

        scanner.nextLine(); // Consume newline

        System.out.println("Enter Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter Current Capacity: ");
        double currentCapacity = scanner.nextDouble();

        System.out.println("Enter Storing Capacity: ");
        double storingCapacity = scanner.nextDouble();

        System.out.println("Does the port have landing ability? (true/false): ");
        boolean landingAbility = scanner.nextBoolean();


        // Create a new Port object with user-entered values
        return new Port(ID, name, latitude, longitude, description, currentCapacity, storingCapacity, landingAbility);
    }
    public static Container createContainerFromUserInput(Optional<Container> existingContainer ) {
        String id = "";
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        if (existingContainer.isPresent()) {
            // match container type with number in hashmap

            id = existingContainer.get().getID();
            choice = containerTypeToChoice.getOrDefault(existingContainer.get().getClass(), 0);
            System.out.println("Enter information to update Container with ID " + id + ":");
        } else {
            System.out.println("Enter Container ID: ");
            id = scanner.nextLine();

            System.out.println("Choose the type of container to create:");
            System.out.println("1. Dry Storage Container");
            System.out.println("2. Liquid Container");
            System.out.println("3. Open Side Container");
            System.out.println("4. Open Top Container");
            System.out.println("5. Refridgerated Container");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        }




        System.out.println("Enter Container Weight: ");
        double weight = scanner.nextDouble();

        // Create the corresponding container type based on user choice
        switch (choice) {
            case 1:
                return new DryStorageContainer(id, weight);
            case 2:
                return new LiquidContainer(id, weight);
            case 3:
                return new OpenSideContainer(id, weight);
            case 4:
                return new OpenTopContainer(id, weight);
            case 5:
                return new RefridgeratedContainer(id, weight);
            default:
                System.out.println("Invalid choice.");
               break;
        }
        return null;
    }

    public static Trip createTripFromUserInput(Optional<String> tripID) {
        Scanner scanner = new Scanner(System.in);
        String ID = tripID.orElse("");
        if (tripID.isPresent()) {
            System.out.println("Enter information to update Trip with ID " + tripID.get() + ":");
        } else {
            System.out.println("Enter Trip ID: ");
            ID = scanner.nextLine();
        }


        System.out.println("Enter Vehicle ID for Tracking: ");
        String vehicleID = scanner.nextLine();

        // Assuming departure and arrival dates are entered in the format yyyy-MM-dd
        System.out.println("Enter Departure Date (yyyy-MM-dd): ");
        String departureDateString = scanner.nextLine();
        LocalDate departureDate = LocalDate.parse(departureDateString);

        System.out.println("Enter Arrival Date (yyyy-MM-dd): ");
        String arrivalDateString = scanner.nextLine();
        LocalDate arrivalDate = LocalDate.parse(arrivalDateString);

        System.out.println("Enter Departure Port ID: ");
        String departurePortID = scanner.nextLine();

        System.out.println("Enter Arrival Port ID: ");
        String arrivalPortID = scanner.nextLine();

        System.out.println("Enter Trip Status (PENDING, ON_GOING, COMPLETED): ");
        String statusString = scanner.nextLine();
        TripStatus status = TripStatus.valueOf(statusString.toUpperCase());

        // You need to retrieve the corresponding Port and Vehicle objects based on their IDs
        PortImplement portImplement = new PortImplement(new Port());
        Port departurePort = portImplement.getById(departurePortID);
        Port arrivalPort = portImplement.getById(arrivalPortID);
        VehicleImplement vehicleImplement = new VehicleImplement(new Vehicle());
        Vehicle trackingVehicle = vehicleImplement.getById(vehicleID);

        // Create and return the Trip object
        return new Trip(ID, trackingVehicle, departureDate, arrivalDate, departurePort, arrivalPort, status);
    }

}
