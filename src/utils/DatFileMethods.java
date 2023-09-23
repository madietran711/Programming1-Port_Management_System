package utils;

import entities.container.Container;
import entities.container.DryStorageContainer;
import entities.container.LiquidContainer;
import entities.container.OpenTopContainer;
import entities.port.Port;

import entities.trip.Trip;

import entities.user.SystemAdmin;
import entities.vehicle.ReeferTruck;
import entities.vehicle.Ship;
import entities.vehicle.TankerTruck;
import entities.vehicle.Vehicle;
import enums.TripStatus;

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

    public static void main(String[] args) {
        SystemAdmin admin = new SystemAdmin("1", "admin", "admin");
        if (admin.removeAllTrips() && admin.removeAllContainers()  &&  admin.removeAllVehicles() && admin.removeAllPorts()
               ) {
            System.out.println("All data removed successfully");
            List<Port> ports = new ArrayList<>();
            ports.add(new Port("1", "Port 1", 1, 1, "Port 1", 1, 1, true, new ArrayList<>(),new ArrayList<>(), new ArrayList<>()));
            ports.add(new Port("2", "Port 2", 2, 2, "Port 2", 2, 2, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            writeAllLines("Port.dat", ports);



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
            // admin.updatePort(ports.get(1));
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


            // Trip
            System.out.println(
                    "---------------------------------------------------------------------------------------------");
            List<Trip> trips = new ArrayList<Trip>();
            trips.add(new Trip("1", null, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 15), admin.getAllPorts().get(0),
                    admin.getAllPorts().get(1),
                    TripStatus.ON_GOING));
            trips.add(new Trip("2", null, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 27), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(0),
                    TripStatus.COMPLETED));
            writeAllLines("Trip.dat", trips);

            System.out.println("Test Trip methods");
            System.out.println("Test get all method");
            admin.getAllTrips().forEach(System.out::println);

            System.out.println("\nTest create method");
            Trip newTrip = new Trip("3", newVehicle, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 29), admin.getAllPorts().get(0),
                    admin.getAllPorts().get(1), TripStatus.ON_GOING);
            admin.createTrip(newTrip);
            trips = admin.getAllTrips();
            admin.getAllTrips().forEach(System.out::println);

            System.out.println("\nTest update method");
            Trip updateTrip = new Trip("3", newVehicle, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 10, 29),
                    admin.getAllPorts().get(1),
                    admin.getAllPorts().get(0), TripStatus.ON_GOING);
            admin.updateTrip(updateTrip);
            admin.getAllTrips().forEach(System.out::println);

            System.out.println("\nTest delete method");
            admin.getAllTrips().forEach(System.out::println);


            // Vehicle
            List<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(new TankerTruck("1", "Vehicle 1", 1, 1, 1, ports.get(0), new ArrayList<>()));
            vehicles.add(new ReeferTruck("2", "Vehicle 2", 2, 2, 2, ports.get(0), new ArrayList<>()));
            writeAllLines("Vehicle.dat", vehicles);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test get All method");
            admin.getAllVehicles().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test create method");
            Ship newVehicle1 = new Ship("3", "Vehicle 3", 3, 3, 3, ports.get(1), new ArrayList<>());
            admin.createVehicle(newVehicle1);
            admin.getAllVehicles().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test update method");
            Ship updateVehicle = new Ship("3", "Vehicle 3 Updated", 3, 3, 3, ports.get(0), new ArrayList<>());
            admin.updateVehicle(updateVehicle);
            admin.getAllVehicles().forEach(System.out::println);
            admin.getAllPorts().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test delete method");


            admin.getAllVehicles().forEach(System.out::println);


            // Container
            List<Container> containers = new ArrayList<>();
            containers.add(new DryStorageContainer("1", 1.0, ports.get(0)));
            containers.add(new OpenTopContainer("2", 2.0, ports.get(0)));
            writeAllLines("Container.dat", containers);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test get All method");
            admin.getAllContainers().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test create method");
            Container newContainer = new LiquidContainer("3", 6.0,ports.get(0));
            admin.createContainer(newContainer);
            admin.getAllContainers().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test update method");


            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test delete method");


            admin.getAllContainers().forEach(System.out::println);

        } else {
            System.out.println("Error removing data");
        }


    }
}
