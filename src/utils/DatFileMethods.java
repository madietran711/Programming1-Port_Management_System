package utils;

import entities.port.Port;

import entities.trip.Trip;

import entities.user.SystemAdmin;
import entities.vehicle.Vehicle;

import java.io.*;
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

    public static void main(String[] args) {
        List<Port> ports = new ArrayList<>();

        ports.add(new Port("1", "Port 1", 1, 1, "Port 1", 1, 1, true, new ArrayList<>(), new ArrayList<>(),
                Collections.emptyList()));
        ports.add(new Port("2", "Port 2", 2, 2, "Port 2", 2, 2, true, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>()));
        writeAllLines("Port.dat", ports);
        SystemAdmin admin = new SystemAdmin("1", "admin", "admin");

        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllPorts().forEach(System.out::println);

        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.println("Test create method");
        Port newPort = new Port("3", "Port 3", 3, 3, "Port 3", 3, 3, true, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        admin.createPort(newPort);
        ports = admin.getAllPorts();
        Vehicle newVehicle = new Vehicle("1", "Vehicle 1", 1, 1, 1, ports.get(1), new ArrayList<>());

      
        System.out.println(newVehicle);
        admin.updatePort(ports.get(1));
        admin.getAllPorts().forEach(System.out::println);


        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.println("Test update method");
        Port updatePort = new Port("3", "Port 3 Updated", 3, 3, "Port 3", 3, 3, true, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        admin.updatePort(updatePort);
        admin.getAllPorts().forEach(System.out::println);

        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.println("Test delete method");
        admin.deletePort("3");
        admin.getAllPorts().forEach(System.out::println);

        System.out.println(
                "---------------------------------------------------------------------------------------------");
        List<Trip> trips = new ArrayList<Trip>();
        trips.add(new Trip("1", null, new Date(123, Calendar.SEPTEMBER, 10), new Date(123, Calendar.SEPTEMBER, 15), admin.getAllPorts().get(0),
                admin.getAllPorts().get(1),
                "On going"));
        trips.add(new Trip("2", null, new Date(123, Calendar.SEPTEMBER, 20), new Date(123, Calendar.SEPTEMBER, 27), admin.getAllPorts().get(1),
                admin.getAllPorts().get(0),
                "On hold"));
        writeAllLines("Trip.dat", trips);

        System.out.println("Test Trip methods");
        System.out.println("Test get all method");
        admin.getAllTrips().forEach(System.out::println);

        System.out.println("\nTest create method");
        Trip newTrip = new Trip("3", newVehicle, new Date(123, Calendar.SEPTEMBER, 1), new Date(123, Calendar.SEPTEMBER, 29), admin.getAllPorts().get(0),
                admin.getAllPorts().get(1), "On going");
        admin.createTrip(newTrip);
        trips = admin.getAllTrips();
        admin.getAllTrips().forEach(System.out::println);

        System.out.println("\nTest update method");
        Trip updateTrip = new Trip("3", newVehicle, new Date(123, Calendar.AUGUST, 1), new Date(123, Calendar.OCTOBER, 29),
                admin.getAllPorts().get(1),
                admin.getAllPorts().get(0), "On going");
        admin.updateTrip(updateTrip);
        admin.getAllTrips().forEach(System.out::println);

        System.out.println("\nTest delete method");
        admin.deleteTrip("3");
        admin.getAllTrips().forEach(System.out::println);


        // Vehicle
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("1", "Vehicle 1", 1, 1, 1, ports.get(0), new ArrayList<>()));
        vehicles.add(new Vehicle("2", "Vehicle 2", 2, 2, 2, ports.get(0), new ArrayList<>()));
        writeAllLines("Vehicle.dat", vehicles);
        SystemAdmin adminVehicle = new SystemAdmin("1", "adminVehicle", "adminVehicle");

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        adminVehicle.getAllVehicles().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test create method");
        Vehicle newVehicle1 = new Vehicle("3", "Vehicle 3", 3, 3, 3, ports.get(1), new ArrayList<>());
        adminVehicle.createVehicle(newVehicle1);
        adminVehicle.getAllVehicles().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test update method");
        Vehicle updateVehicle = new Vehicle("3", "Vehicle 3 Updated", 3, 3, 3, ports.get(1), new ArrayList<>());
        adminVehicle.updateVehicle(updateVehicle);
        adminVehicle.getAllVehicles().forEach(System.out::println);
        adminVehicle.getAllPorts().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test delete method");
        adminVehicle.deleteVehicle("1");
        adminVehicle.deleteVehicle("2");
        adminVehicle.deleteVehicle("3");
        adminVehicle.getAllVehicles().forEach(System.out::println);
    }
}
