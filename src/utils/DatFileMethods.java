package utils;

import entities.container.*;
import entities.port.Port;

import entities.trip.Trip;

import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;
import entities.vehicle.*;
import enums.TripStatus;
import service.User.implementation.UserImplement;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DatFileMethods implements Serializable {
    // get file path
    private static String getFilePath(String fileName) {
        // Assuming file is located in a folder named "dataFiles" relative to your project's root directory
        String absolutePath = "src" + File.separator + "dataFiles" + File.separator + fileName;
        return new File(absolutePath).getPath();
    }


    // write all data in a file
    public static <T> void writeAllLines(String fileName, List<T> data) {
        String filePath = getFilePath(fileName);
        // loop through the data and write each object to the file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (T object : data) {
                outputStream.writeObject(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read all data in a file
    public static <T> List<T> readAllLines(String fileName, Class<T> objectType) {


        String filePath = getFilePath(fileName);
        // initiate an empty array list to store all data

        List<T> allFileData = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            // loop through the file until it reaches the end of file
            while (true) {
                try {
                    // read object from file
                    Object object = inputStream.readObject();
                    allFileData.add(objectType.cast(object));

                } catch (EOFException e) {
                    // End Of File Exception: break the loop
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allFileData;
    }

    public static void initDat() {

        System.out.println("--------------------------------------------INITIATE SAMPLE TESTING DATA-------------------------------------------------------");


        SystemAdmin admin = new SystemAdmin("admin123", "admin", "password"); // Create a SystemAdmin instance
        // Write the SystemAdmin instance to the filesadadm
//        admin.removeAllTrips();
//        admin.removeAllVehicles();
//        admin.removeAllContainers();
//        admin.removeAllPorts();

// Create Ports
        Port port1 = new Port("p-1", "Port 1", 1, 1, "Port 1", 1, 16, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Port port2 = new Port("p-2", "Port 2", 2, 2, "Port 2 (no landing)", 2, 20, false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Port port3 = new Port("p-3", "Port 3", 3, 3, "Port 3", 3, 24, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Port port4 = new Port("p-4", "Port 4", 4, 4, "Port 4  (no landing)", 32, 50, false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Port port5 = new Port("p-5", "Port 5", 5, 5, "Port 5", 5, 40, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        List<Port> ports = new ArrayList<>();
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        ports.add(port5);

        writeAllLines("Port.dat", ports);

        PortManager portManager1 = new PortManager("m-1", "manager1", "password1", port1);
        PortManager portManager2 = new PortManager("m-2", "manager2", "password2", port2);
        PortManager portManager3 = new PortManager("m-3", "manager3", "password3", port3);
        PortManager portManager4 = new PortManager("m-4", "manager4", "password4", port4);
        PortManager portManager5 = new PortManager("m-5", "manager5", "password5", port5);

        List<User> users = new ArrayList<>();
        users.add(portManager1);
        users.add(portManager2);
        users.add(portManager3);
        users.add(portManager4);
        users.add(portManager5);
        users.add(admin);
        writeAllLines("User.dat", users);
        UserImplement userImplement = new UserImplement(new User());
        userImplement.getAll().forEach(System.out::println);
        admin.getAllPorts().forEach(System.out::println);

        System.out.println("Port 1: " + admin.getPortById("p-1"));
        System.out.println("Port 1: " + port1);
        System.out.println(admin.getPortById("p-1").equals(port1));

// Create Vehicles
        Vehicle truck1 = new BasicTruck("tr-1", "Basic truck 1", 15, 0, 100, port1, new ArrayList<>());
        Vehicle truck2 = new BasicTruck("tr-2", "Basic truck 2", 15, 0, 343, port4, new ArrayList<>());
        Vehicle truck3 = new BasicTruck("tr-3", "Basic truck 3", 15, 0, 200, null, new ArrayList<>());
        Vehicle truck4 = new BasicTruck("tr-4", "Basic truck 4", 30, 19, 100, port5, new ArrayList<>());
        Vehicle truck5 = new BasicTruck("tr-5", "Basic truck 5", 15, 0, 100, port1, new ArrayList<>());
        Vehicle truck6 = new BasicTruck("tr-6", "Basic truck 6", 15, 0, 343, port4, new ArrayList<>());
        Vehicle truck7 = new BasicTruck("tr-7", "Basic truck 7", 15, 0, 200, null, new ArrayList<>());
        Vehicle truck8 = new BasicTruck("tr-8", "Basic truck 8", 30, 19, 100, port5, new ArrayList<>());

        Vehicle reefer1 = new ReeferTruck("tr-9", "Reefer truck 1", 12, 11, 100, port1, new ArrayList<>());
        Vehicle reefer2 = new ReeferTruck("tr-10", "Reefer truck 2", 12, 8, 200, port1, new ArrayList<>());
        Vehicle reefer3 = new ReeferTruck("tr-11", "Reefer truck 3", 12, 12, 40, null, new ArrayList<>());
        Vehicle reefer4 = new ReeferTruck("tr-12", "Reefer truck 4", 24, 17, 40, port4, new ArrayList<>());
        Vehicle reefer5 = new ReeferTruck("tr-13", "Reefer truck 5", 12, 12, 40, null, new ArrayList<>());
        Vehicle reefer6 = new ReeferTruck("tr-14", "Reefer truck 6", 24, 17, 40, port4, new ArrayList<>());

        Vehicle tanker1 = new TankerTruck("tr-15", "Tanker Truck 1", 15, 0, 100, port1, new ArrayList<>());
        Vehicle tanker2 = new TankerTruck("tr-16", "Tanker Truck 2", 15, 0, 343, port4, new ArrayList<>());
        Vehicle tanker3 = new TankerTruck("tr-17", "Tanker Truck 3", 15, 0, 200, null, new ArrayList<>());
        Vehicle tanker4 = new TankerTruck("tr-18", "Tanker Truck 4", 30, 19, 100, port5, new ArrayList<>());
        Vehicle tanker5 = new TankerTruck("tr-19", "Tanker Truck 5", 15, 0, 200, null, new ArrayList<>());
        Vehicle tanker6 = new TankerTruck("tr-20", "Tanker Truck 6", 30, 19, 100, port5, new ArrayList<>());

        Vehicle ship1 = new Ship("sh-1", "Ship 1", 80, 28, 4, port1, new ArrayList<>());
        Vehicle ship2 = new Ship("sh-2", "Ship 2", 80, 71, 4, port2, new ArrayList<>());
        Vehicle ship3 = new Ship("sh-3", "Ship 3", 80, 62, 4, port3, new ArrayList<>());
        Vehicle ship4 = new Ship("sh-4", "Ship 4", 80, 19, 4, port4, new ArrayList<>());
        Vehicle ship5 = new Ship("sh-5", "Ship 5", 80, 43, 4, port5, new ArrayList<>());
        Vehicle ship6 = new Ship("sh-6", "Ship 6", 80, 54, 4, port5, new ArrayList<>());

// ... Create other vehicles in a similar manner

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(truck1);
        vehicles.add(truck2);
        vehicles.add(truck3);
        vehicles.add(truck4);
        vehicles.add(truck5);
        vehicles.add(truck6);
        vehicles.add(truck7);
        vehicles.add(truck8);
        vehicles.add(reefer1);
        vehicles.add(reefer2);
        vehicles.add(reefer3);
        vehicles.add(reefer4);
        vehicles.add(reefer5);
        vehicles.add(reefer6);
        vehicles.add(tanker1);
        vehicles.add(tanker2);
        vehicles.add(tanker3);
        vehicles.add(tanker4);
        vehicles.add(tanker5);
        vehicles.add(tanker6);
        vehicles.add(ship1);
        vehicles.add(ship2);
        vehicles.add(ship3);
        vehicles.add(ship4);
        vehicles.add(ship5);
        vehicles.add(ship6);

        writeAllLines("Vehicle.dat", vehicles);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllVehicles().forEach(System.out::println);

// Create Containers
        DryStorageContainer container1 = new DryStorageContainer("c-1", 3.5, port1);
        DryStorageContainer container2 = new DryStorageContainer("c-2", 3.5, port1);
        DryStorageContainer container3 = new DryStorageContainer("c-3", 3.5, port1);
        RefridgeratedContainer container4 = new RefridgeratedContainer("c-4", 4.6, port1);
        RefridgeratedContainer container5 = new RefridgeratedContainer("c-5", 4.6, port2);
        RefridgeratedContainer container6 = new RefridgeratedContainer("c-6", 4.6, reefer1);
        RefridgeratedContainer container7 = new RefridgeratedContainer("c-7", 3.2, port2);
        RefridgeratedContainer container8 = new RefridgeratedContainer("c-8", 3.2, reefer1);
        DryStorageContainer container9 = new DryStorageContainer("c-9", 3.2, ship2);
        DryStorageContainer container10 = new DryStorageContainer("c-10", 4.5, port3);
        DryStorageContainer container11 = new DryStorageContainer("c-11", 4.5, ship2);
        DryStorageContainer container12 = new DryStorageContainer("c-12", 4.5, port3);
        LiquidContainer container13 = new LiquidContainer("c-13", 3.8, port4);
        DryStorageContainer container14 = new DryStorageContainer("c-14", 3.8, ship2);
        LiquidContainer container15 = new LiquidContainer("c-15", 3.8, ship4);
        DryStorageContainer container16 = new DryStorageContainer("c-16", 4.9, ship3);
        OpenTopContainer container17 = new OpenTopContainer("c-17", 4.9, port5);
        DryStorageContainer container18 = new DryStorageContainer("c-18", 4.6, port5);
        OpenTopContainer container19 = new OpenTopContainer("c-19", 4.6, ship2);
        RefridgeratedContainer container20 = new RefridgeratedContainer("c-20", 4.6, reefer1);

// ... Create other containers in a similar manner

        List<Container> containers = new ArrayList<>();
        containers.add(container1);
        containers.add(container2);
        containers.add(container3);
        containers.add(container4);
        containers.add(container5);
        containers.add(container6);
        containers.add(container7);
        containers.add(container8);
        containers.add(container9);
        containers.add(container10);
        containers.add(container11);
        containers.add(container12);
        containers.add(container13);
        containers.add(container14);
        containers.add(container15);
        containers.add(container16);
        containers.add(container17);
        containers.add(container18);
        containers.add(container19);
        containers.add(container20);

        writeAllLines("Container.dat", containers);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllContainers().forEach(System.out::println);

// Create Trips
// ... Create trips in a similar manner

        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip("t-1", truck1, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 26), port1, port2, TripStatus.ON_GOING));
        trips.add(new Trip("t-2", truck2, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), port2, port3, TripStatus.COMPLETED));
        trips.add(new Trip("t-3", truck3, LocalDate.of(2023, 9, 23), LocalDate.of(2023, 9, 30), port3, port4, TripStatus.ON_GOING));
        trips.add(new Trip("t-4", truck4, LocalDate.of(2023, 9, 28), LocalDate.of(2023, 10, 4), port4, port5, TripStatus.PENDING));
        trips.add(new Trip("t-5", truck5, LocalDate.of(2023, 9, 24), LocalDate.of(2023, 9, 27), port5, port1, TripStatus.ON_GOING));
        trips.add(new Trip("t-6", truck6, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 10, 1), port1, port3, TripStatus.ON_GOING));
        trips.add(new Trip("t-7", truck7, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), port3, port5, TripStatus.COMPLETED));
        trips.add(new Trip("t-8", truck8, LocalDate.of(2023, 9, 25), LocalDate.of(2023, 10, 9), port2, port4, TripStatus.PENDING));
        trips.add(new Trip("t-9", truck1, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 10), port1, port4, TripStatus.PENDING));
        trips.add(new Trip("t-10", truck2, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), port2, port5, TripStatus.COMPLETED));
        trips.add(new Trip("t-11", truck1, LocalDate.of(2023, 10, 6), LocalDate.of(2023, 10, 17), port3, port1, TripStatus.PENDING));
        trips.add(new Trip("t-12", truck2, LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 20), port4, port2, TripStatus.COMPLETED));
        trips.add(new Trip("t-13", truck1, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 27), port4, port1, TripStatus.ON_GOING));
        trips.add(new Trip("t-14", truck2, LocalDate.of(2023, 10, 19), LocalDate.of(2023, 10, 22), port5, port2, TripStatus.PENDING));
        trips.add(new Trip("t-15", truck1, LocalDate.of(2023, 9, 19), LocalDate.of(2023, 9, 22), port1, port2, TripStatus.COMPLETED));
        trips.add(new Trip("t-16", truck2, LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 25), port2, port3, TripStatus.ON_GOING));
        trips.add(new Trip("t-17", truck1, LocalDate.of(2023, 9, 17), LocalDate.of(2023, 9, 24), port4, port3, TripStatus.COMPLETED));
        trips.add(new Trip("t-18", truck2, LocalDate.of(2023, 9, 23), LocalDate.of(2023, 9, 27), port4, port5, TripStatus.ON_GOING));
        trips.add(new Trip("t-19", truck1, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 26), port5, port1, TripStatus.ON_GOING));
        trips.add(new Trip("t-20", truck2, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), port2, port5, TripStatus.COMPLETED));
        trips.add(new Trip("t-21", truck1, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 30), port2, port4, TripStatus.ON_GOING));
        trips.add(new Trip("t-22", truck2, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 23), port4, port3, TripStatus.COMPLETED));
        trips.add(new Trip("t-23", truck1, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 26), port5, port2, TripStatus.ON_GOING));
        trips.add(new Trip("t-24", truck2, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), port5, port3, TripStatus.COMPLETED));
        trips.add(new Trip("t-25", truck1, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 30), port1, port2, TripStatus.ON_GOING));

// ... Add other trips

        writeAllLines("Trip.dat", trips);
        admin.getAllTrips().forEach(System.out::println);

        System.out.println("--------------------------------------------END OF SAMPLE TESTING DATA-------------------------------------------------------");




    }
}
