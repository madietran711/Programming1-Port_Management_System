package components;

import entities.port.Port;
import entities.trip.Trip;
import entities.user.PortManager;
import entities.vehicle.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class ManagerMenu {
    private PortManager portManager;
    private Scanner scanner;

    public ManagerMenu(PortManager portManager) {
        this.portManager = portManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>> DISPLAYING [PORT MANAGEMER] MENU <<<<<<<<<<<<<<<<<<<<<");
            System.out.println("1. Manage Containers");
            System.out.println("2. View the location of Port");
            System.out.println("3. View the storage capacity of Port");
            System.out.println("4. View the all Trips at Port");
            System.out.println("5. View the current Vehicles in Port");
            System.out.println("6. View the current Containers in Port");
            System.out.println("7. Get total Vehicles in a Port");
            System.out.println("8. Get total Containers in a Port");
            System.out.println("9. Calculate the distance between managing Port and another");
            System.out.println("10. View the traffic record of Port");
            System.out.println("11. Refuel a Vehicle");
            System.out.println("12. Load Containers onto a Vehicle"); // For Phuong
            System.out.println("13. Unload Containers from a Vehicle"); // For Phuong
            System.out.println("14. View the carrying capacity of a Vehicle");
            System.out.println("15. View the fuel capacity of a Vehicle");
            System.out.println("16. View current containers on a Vehicle");
            System.out.println("17. View the schedule of a Vehicle"); // For Phuong
            System.out.println("18. Calculate how much fuel has been used in a day");
            System.out.println("19. Exit");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Port managingPort = portManager.getManagingPort();

            switch (choice) {
                case 1:
                    manageContainer();
                    break;

                case 2:
                    System.out.println("---------------------VIEW PORT LOCATION---------------------");
                    System.out.println("Port with ID " + portManager.getManagingPort().getID() + " is at the latitude "
                            + portManager.getManagingPort().getLatitude() + " and the longitude "
                            + portManager.getManagingPort().getLongitude() + ".");
                    break;

                case 3:
                    System.out.println("---------------------VIEW PORT CAPACITY---------------------");
                    System.out.println("Port with ID " + portManager.getManagingPort().getID() + " is currently at "
                            + portManager.getManagingPort().getCurrentCapacity()
                            + " current capacity with a maximum capacity of "
                            + portManager.getManagingPort().getStoringCapacity() + ".");
                    break;

                case 4:
                    System.out.println("---------------------VIEW PORT ALL TRIPS---------------------");
                    System.out.println("Port with ID " + portManager.getManagingPort().getID()
                            + " currently has these following trips :");
                    portManager.getManagingPort().getTripList().forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("---------------------VIEW PORT CURRENT VEHICLES---------------------");
                    System.out.println("Port with ID " + portManager.getManagingPort().getID()
                            + " currently has these following vehicles :");
                    portManager.getManagingPort().getTripList().forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("---------------------VIEW PORT CURRENT CONTAINERS---------------------");
                    System.out.println("Port with ID " + portManager.getManagingPort().getID()
                            + " currently has these following containers :");
                    portManager.getManagingPort().getContainerList().forEach(System.out::println);
                    break;

                case 7:
                    System.out.println("---------------------COUNT PORT TOTAL VEHICLES---------------------");
                    System.out.println(
                            "Port with ID " + portManager.getManagingPort().getID() + " has a total vehicle count of "
                                    + Integer.toString(portManager.getManagingPort().getTotalVehicleCount()) + ".");
                    break;

                case 8:
                    System.out.println("---------------------COUNT PORT TOTAL CONTAINERS---------------------");
                    System.out.println(
                            "Port with ID " + portManager.getManagingPort().getID() + " has a total vehicle count of "
                                    + Integer.toString(portManager.getManagingPort().getTotalContainerCount()) + ".");
                    break;

                case 9:
                    System.out.println("---------------------CALCULATE PORTS DISTANCE---------------------");
                    System.out.println(
                            "Enter the ID of the Port you want to calculate the distance to from the current managin Port:");
                    String portIDCalDistanceTo = scanner.nextLine();
                    if (portManager.getPortById(portIDCalDistanceTo) == null) {
                        System.out.println("Port with ID " + portIDCalDistanceTo + " does not exist.");
                        break;
                    }
                    Port portCalDistanceTo = portManager.getPortById(portIDCalDistanceTo);
                    double distanceBetweenPorts = portManager.getManagingPort()
                            .calculateDistanceFromPort(portCalDistanceTo);
                    System.out.println("Distance between current managing port with ID "
                            + portManager.getManagingPort().getID() + " and selected port "
                            + portCalDistanceTo.getID() + " is : " + Double.toString(distanceBetweenPorts));
                    break;

                case 10:
                    System.out.println("---------------------VIEW PORT TRAFFIC RECORD---------------------");
                    System.out.println("Port with ID " + portManager.getManagingPort().getID()
                            + " has the following traffic records : ");
                    portManager.getManagingPort().getTrafficRecord().forEach(System.out::println);
                    break;

                case 11:
                    System.out.println("---------------------REFUEL A VEHICLE---------------------");
                    System.out.println("Enter the ID of the vehicle you want to refuel: ");
                    String refuelingVehicleID = scanner.nextLine();
                    // Check if the Vehicle exists
                    if (portManager.getVehicleById(refuelingVehicleID) == null) {
                        System.out.println("Vehicle with ID " + refuelingVehicleID + " does not exist.");
                        break;
                    }
                    // Check if the Vehicle is currently at the managing Port
                    if (managingPort.getVehicleList()
                            .contains(portManager.getVehicleById(refuelingVehicleID)) == false) {
                        System.out.println("Vehicle with ID " + refuelingVehicleID
                                + " is not current at Port with ID " + portManager.getManagingPort().getID());
                        break;
                    }
                    // Refule Vehicle
                    try {
                        Vehicle refuelingVehicle = portManager.getVehicleById(refuelingVehicleID);
                        refuelingVehicle.setCurrentFuel(refuelingVehicle.getFuelTankCapacity());
                        portManager.updateVehicle(refuelingVehicle);
                        System.out.println("Vehicle with ID " + refuelingVehicleID + " has been refuled and updated.");
                        System.out.println("Current fuel level for vehicle with ID " + refuelingVehicleID + " : "
                                + Double.toString(refuelingVehicle.getCurrentFuel()));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 12:
                    System.out.println("---------------------LOAD A VEHICLE---------------------");
                    // For Phuong
                    break;

                case 13:
                    System.out.println("---------------------UNLOAD A VEHICLE---------------------");
                    // For Phuong
                    break;

                case 14:
                    System.out.println("---------------------VIEW VEHICLE CARRYING CAPACITY---------------------");
                    System.out.println("Enter the ID of the vehicle you want to view its carrying capacity: ");
                    String vehicleIDViewCapacity = scanner.nextLine();
                    // Check if the Vehicle exists
                    if (portManager.getVehicleById(vehicleIDViewCapacity) == null) {
                        System.out.println("Vehicle with ID " + vehicleIDViewCapacity + " does not exist.");
                        break;
                    }
                    // Check if the Vehicle is currently at the managing Port
                    if (managingPort.getVehicleList()
                            .contains(portManager.getVehicleById(vehicleIDViewCapacity)) == false) {
                        System.out.println("Vehicle with ID " + vehicleIDViewCapacity
                                + " is not current at Port with ID " + portManager.getManagingPort().getID());
                        break;
                    }
                    Vehicle vehicleViewCapacity = portManager.getVehicleById(vehicleIDViewCapacity);
                    System.out.println("Vehicle with ID " + vehicleIDViewCapacity + " is currently at "
                            + vehicleViewCapacity.getCurrentCapacity()
                            + " current carrying capacity with a maximum carrying capacity of "
                            + vehicleViewCapacity.getCarryingCapacity() + ".");

                    break;

                case 15:
                    System.out.println("---------------------VIEW VEHICLE FUEL CAPACITY---------------------");
                    System.out.println("Enter the ID of the vehicle you want to view its fuel capacity: ");
                    String vehicleIDViewFuel = scanner.nextLine();
                    // Check if the Vehicle exists
                    if (portManager.getVehicleById(vehicleIDViewFuel) == null) {
                        System.out.println("Vehicle with ID " + vehicleIDViewFuel + " does not exist.");
                        break;
                    }
                    // Check if the Vehicle is currently at the managing Port
                    if (managingPort.getVehicleList()
                            .contains(portManager.getVehicleById(vehicleIDViewFuel)) == false) {
                        System.out.println("Vehicle with ID " + vehicleIDViewFuel
                                + " is not current at Port with ID " + portManager.getManagingPort().getID());
                        break;
                    }
                    Vehicle vehicleViewFuel = portManager.getVehicleById(vehicleIDViewFuel);
                    System.out.println("Vehicle with ID " + vehicleIDViewFuel + " is currently at "
                            + vehicleViewFuel.getCurrentFuel()
                            + " current fuel capacity with a maximum fuel capacity of "
                            + vehicleViewFuel.getFuelTankCapacity() + ".");
                    break;

                case 16:
                    System.out.println("---------------------VIEW VEHICLE CURRENT CONTAINERS---------------------");
                    System.out.println("Enter the ID of the vehicle you want to view its current containers: ");
                    String vehicleIDViewContainers = scanner.nextLine();
                    // Check if the Vehicle exists
                    if (portManager.getVehicleById(vehicleIDViewContainers) == null) {
                        System.out.println("Vehicle with ID " + vehicleIDViewContainers + " does not exist.");
                        break;
                    }
                    // Check if the Vehicle is currently at the managing Port
                    if (managingPort.getVehicleList()
                            .contains(portManager.getVehicleById(vehicleIDViewContainers)) == false) {
                        System.out.println("Vehicle with ID " + vehicleIDViewContainers
                                + " is not current at Port with ID " + portManager.getManagingPort().getID());
                        break;
                    }
                    Vehicle vehicleViewContainers = portManager.getVehicleById(vehicleIDViewContainers);
                    System.out.println("Vehicle with ID " + vehicleIDViewContainers
                            + " currently has these following containers :");
                    vehicleViewContainers.getContainerList().forEach(System.out::println);
                    break;

                case 17:
                    System.out.println("---------------------VIEW VEHICLE SCHEDULES---------------------");
                    System.out.println("Enter the ID of the vehicle you want to view its schedules: ");
                    String vehicleIDViewSchedule = scanner.nextLine();
                    // Check if the Vehicle exists
                    if (portManager.getVehicleById(vehicleIDViewSchedule) == null) {
                        System.out.println("Vehicle with ID " + vehicleIDViewSchedule + " does not exist.");
                        break;
                    }
                    // Check if the Vehicle is currently at the managing Port
                    if (managingPort.getVehicleList()
                            .contains(portManager.getVehicleById(vehicleIDViewSchedule)) == false) {
                        System.out.println("Vehicle with ID " + vehicleIDViewSchedule
                                + " is not current at Port with ID " + portManager.getManagingPort().getID());
                        break;
                    }
                    Vehicle vehicleViewSchedule = portManager.getVehicleById(vehicleIDViewSchedule);
                    // Left for Phuong
                    break;

                case 18:
                    System.out.println(
                            "---------------------CALCULATE FUEL COMSUMPTION ON SPECIFIED DATE---------------------");
                    System.out.println(
                            "Enter the Date on which you want to calculate fuel usage (enters year, month, day of month separated by a comma): ");
                    String[] trackingFuelDateInput = scanner.nextLine().split(",");
                    LocalDate trackingFuelDate = LocalDate.of(Integer.parseInt(trackingFuelDateInput[0]),
                            Integer.parseInt(trackingFuelDateInput[1]), Integer.parseInt(trackingFuelDateInput[2]));
                    List<Trip> allTripList = portManager.getAllTrips();
                    double fuelUsage = 0.0;
                    for (Trip trip : allTripList) {
                        if (trip.getDepartureDate().isBefore(trackingFuelDate)
                                && trip.getArrivalDate().isAfter(trackingFuelDate)) {
                            Vehicle trackingVehicle = trip.getTrackingVehicle();
                            Port departurePort = trip.getDeparturePort();
                            Port arrivalPort = trip.getArrivalPort();
                            double portDistance = departurePort.calculateDistanceFromPort(arrivalPort);
                            LocalDate departureDate = trip.getDepartureDate();
                            LocalDate arrivalDate = trip.getArrivalDate();
                            long daysBetween = ChronoUnit.DAYS.between(departureDate, arrivalDate);
                            double travelDistance = portDistance / daysBetween;
                            double totalFuelConsumptionPerKm = trackingVehicle.getContainerList().stream()
                                    .mapToDouble(container -> container.calculateFuelConsumption(trackingVehicle))
                                    .sum();
                            fuelUsage += travelDistance * totalFuelConsumptionPerKm;
                        }
                    }
                    System.out.println("On the Date " + trackingFuelDate.toString() + " the total fuel usage is "
                            + Double.toString(fuelUsage));
                    break;

                case 19:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageContainer() {
        ContainerMenu.displayMenu(portManager);
    }

    public static void main(String[] args) {
        PortManager portManager = new PortManager("1", "Manager1", "123456", new Port());
        ManagerMenu managerMenu = new ManagerMenu(portManager);
        managerMenu.displayMenu();
    }
}
