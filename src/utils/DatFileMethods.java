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
        users.add(admin);;
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

        Vehicle reefer1 = new ReeferTruck("tr-5", "Reefer truck 1", 12, 11, 100, port5, new ArrayList<>());
        Vehicle reefer2 = new ReeferTruck("tr-6", "Reefer truck 2", 12, 8, 200, port1, new ArrayList<>());
        Vehicle reefer3 = new ReeferTruck("tr-7", "Reefer truck 3", 12, 12, 40, null, new ArrayList<>());
        Vehicle reefer4 = new ReeferTruck("tr-8", "Reefer truck 4", 24, 17, 40, port4, new ArrayList<>());

// ... Create other vehicles in a similar manner

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(truck1);
        vehicles.add(truck2);
        vehicles.add(truck3);
        vehicles.add(truck4);
        vehicles.add(reefer1);
        vehicles.add(reefer2);
        vehicles.add(reefer3);
        vehicles.add(reefer4);

        writeAllLines("Vehicle.dat", vehicles);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllVehicles().forEach(System.out::println);

// Create Containers
        Container container1 = new DryStorageContainer("c-1", 3.5, port1);
        Container container2 = new DryStorageContainer("c-2", 3.5, port1);
        Container container3 = new DryStorageContainer("c-3", 3.5, port2);
        Container container4 = new DryStorageContainer("c-4", 4.6, port2);
        Container container5 = new DryStorageContainer("c-5", 4.6, port3);
        Container container6 = new DryStorageContainer("c-6", 4.6, port4);

// ... Create other containers in a similar manner

        List<Container> containers = new ArrayList<>();
        containers.add(container1);
        containers.add(container2);
        containers.add(container3);
        containers.add(container4);
        containers.add(container5);
        containers.add(container6);

        writeAllLines("Container.dat", containers);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllContainers().forEach(System.out::println);

// Create Trips
// ... Create trips in a similar manner

        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip("t-1", truck1, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 26), port1, port2, TripStatus.ON_GOING));
        trips.add(new Trip("t-2", truck2, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), port2, port3, TripStatus.COMPLETED));
// ... Add other trips

        writeAllLines("Trip.dat", trips);
        admin.getAllTrips().forEach(System.out::println);

        System.out.println("--------------------------------------------END OF SAMPLE TESTING DATA-------------------------------------------------------");




    }
}
