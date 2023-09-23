package utils;

import entities.container.Container;
import entities.port.Port;

import entities.trip.Trip;

import entities.user.SystemAdmin;
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
            System.out.println("----------All data removed SUCCESSFULLY----------");

            // Port
            List<Port> ports = new ArrayList<>();
            // Port has landing ability
            ports.add(new Port("p-1", "Port 1", 1, 1, "Port 1", 1, 16, true, new ArrayList<>(),new ArrayList<>(), Collections.emptyList()));
            // Port do not has landing ability
            ports.add(new Port("p-2", "Port 2", 2, 2, "Port 2 (no landing)", 2, 2, false,new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            // Port has landing ability
            ports.add(new Port("p-3", "Port 3", 3, 3, "Port 3", 3, 24, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            // Port do not has landing ability
            ports.add(new Port("p-4", "Port 4", 4, 4, "Port 4  (no landing)", 32, 4, false,new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            // Port has landing ability
            ports.add(new Port("p-5", "Port 5", 5, 5, "Port 5", 5, 40, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));

            writeAllLines("Port.dat", ports);



            System.out.println(
                    "---------------------------------------------------------------------------------------------");
            System.out.println("Test get All method");
            admin.getAllPorts().forEach(System.out::println);


            System.out.println(
                    "---------------------------------------------------------------------------------------------"); // Only Admin can use!!!
            System.out.println("Test create Port method");
            Port newPort = new Port("p-6", "Port 6", 6, 6, "Port 6", 6, 52, true, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>());
            admin.createPort(newPort);
            ports = admin.getAllPorts();
            Vehicle newVehicle = new Vehicle("tr-1", "Basic truck 1", 1, 1, 1, ports.get(5), new ArrayList<>());
            // admin.updatePort(ports.get(1));
            admin.getAllPorts().forEach(System.out::println);



            System.out.println(
                    "---------------------------------------------------------------------------------------------");
            System.out.println("Test update Port method");
            Port updatePort = new Port("6", "Port 6", 6, 6, "Port 6 Updated", 6, 52, true, new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>());
            admin.updatePort(updatePort);
            admin.getAllPorts().forEach(System.out::println);

            System.out.println(
                    "---------------------------------------------------------------------------------------------");
            System.out.println("Test delete Port method");
            admin.deletePort("p-3"); // Delete Port with ID = 3
            admin.getAllPorts().forEach(System.out::println);



            // Trip
            System.out.println(
                    "---------------------------------------------------------------------------------------------"); // all the history is only kept for 7 days = from 17/9/2023
            List<Trip> trips = new ArrayList<Trip>();
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-1", null, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 26), admin.getAllPorts().get(0),
                    admin.getAllPorts().get(1),
                    TripStatus.ON_GOING));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-2", null, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.COMPLETED));
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-3", null, LocalDate.of(2023, 9, 23), LocalDate.of(2023, 9, 30), admin.getAllPorts().get(4),
                    admin.getAllPorts().get(1),
                    TripStatus.ON_GOING));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-4", null, LocalDate.of(2023, 9, 28), LocalDate.of(2023, 10, 4), admin.getAllPorts().get(2),
                    admin.getAllPorts().get(4),
                    TripStatus.PENDING));
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-5", null, LocalDate.of(2023, 9, 24), LocalDate.of(2023, 9, 27), admin.getAllPorts().get(0),
                    admin.getAllPorts().get(2),
                    TripStatus.ON_GOING));
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-6", null, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 10, 01), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.ON_GOING));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-7", null, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), admin.getAllPorts().get(3),
                    admin.getAllPorts().get(1),
                    TripStatus.COMPLETED));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-8", null, LocalDate.of(2023, 9, 25), LocalDate.of(2023, 10, 9), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-9", null, LocalDate.of(2023, 10, 01), LocalDate.of(2023, 10, 10), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-10", null, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 22), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(3),
                    TripStatus.COMPLETED));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-11", null, LocalDate.of(2023, 10, 16), LocalDate.of(2023, 10, 27), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-12", null, LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 20), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.COMPLETED));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-13", null, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 27), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-14", null, LocalDate.of(2023, 10, 19), LocalDate.of(2023, 10, 22), admin.getAllPorts().get(0),
                    admin.getAllPorts().get(3),
                    TripStatus.COMPLETED));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-15", null, LocalDate.of(2023, 9, 19), LocalDate.of(2023, 9, 22), admin.getAllPorts().get(3),
                    admin.getAllPorts().get(0),
                    TripStatus.COMPLETED));
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-16", null, LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 25), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.ON_GOING));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-17", null, LocalDate.of(2023, 9, 17), LocalDate.of(2023, 9, 24), admin.getAllPorts().get(0),
                    admin.getAllPorts().get(4),
                    TripStatus.COMPLETED));
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-18", null, LocalDate.of(2023, 9, 23), LocalDate.of(2023, 9, 27), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.ON_GOING));
                    // COMPLETED will from 17/9/2023 to 24/09/2023
            trips.add(new Trip("t-19", null, LocalDate.of(2023, 9, 17), LocalDate.of(2023, 9, 24), admin.getAllPorts().get(4),
                    admin.getAllPorts().get(0),
                    TripStatus.COMPLETED));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-20", null, LocalDate.of(2023, 10, 23), LocalDate.of(2023, 10, 28), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-21", null, LocalDate.of(2023, 10, 20), LocalDate.of(2023, 10, 29), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-22", null, LocalDate.of(2023, 11, 01), LocalDate.of(2023, 11, 10), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    //PENDING is when the departure date is after September 24, 2023
            trips.add(new Trip("t-23", null, LocalDate.of(2023, 11, 20), LocalDate.of(2023, 11, 27), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.PENDING));
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-24", null, LocalDate.of(2023, 9, 20), LocalDate.of(2023, 9, 27), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.COMPLETED));
                    //ON_GOING is when the date arrives after September 24, 2023
            trips.add(new Trip("t-25", null, LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 30), admin.getAllPorts().get(1),
                    admin.getAllPorts().get(2),
                    TripStatus.ON_GOING));
            writeAllLines("Trip.dat", trips);

            System.out.println("Test Trip methods");
            System.out.println("Test get all method");
            admin.getAllTrips().forEach(System.out::println);

            System.out.println("\nTest create Trip method");
            Trip newTrip = new Trip("26", newVehicle, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 29), admin.getAllPorts().get(0),
                    admin.getAllPorts().get(1), TripStatus.PENDING);
            admin.createTrip(newTrip);
            trips = admin.getAllTrips();
            admin.getAllTrips().forEach(System.out::println);

            System.out.println("\nTest update Trip method");
            Trip updateTrip = new Trip("26", newVehicle, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 10, 29),
                    admin.getAllPorts().get(1),
                    admin.getAllPorts().get(0), TripStatus.ON_GOING);
            admin.updateTrip(updateTrip);
            admin.getAllTrips().forEach(System.out::println);

            System.out.println("\nTest delete Trip method");
            admin.deleteTrip("26");
            admin.getAllTrips().forEach(System.out::println);


            // Vehicle
            List<Vehicle> vehicles = new ArrayList<>();

            // 4 Basic trucks
            vehicles.add(new Vehicle("tr-1", "Basic truck 1", 15, 12, 3, ports.get(0), new ArrayList<>()));
            vehicles.add(new Vehicle("tr-2", "Basic truck 2", 15, 11, 3, ports.get(3), new ArrayList<>()));
            vehicles.add(new Vehicle("tr-3", "Basic truck 3", 15, 15, 3, null, new ArrayList<>()));
            vehicles.add(new Vehicle("tr-4", "Basic truck 4", 30, 19, 6, ports.get(4), new ArrayList<>()));

            // 4 Reefer trucks
            vehicles.add(new Vehicle("tr-5", "Reefer truck 1", 12, 11, 2, ports.get(4), new ArrayList<>()));
            vehicles.add(new Vehicle("tr-6", "Reefer truck 2", 12, 8, 2, ports.get(0), new ArrayList<>()));
            vehicles.add(new Vehicle("tr-7", "Reefer truck 3", 12, 12, 2, null, new ArrayList<>()));
            vehicles.add(new Vehicle("tr-8", "Reefer truck 4", 24, 17, 4, ports.get(3), new ArrayList<>()));

            // 4 Tanker trucks
            vehicles.add(new Vehicle("tr-9", "Tanker trucks 1", 16, 2, 3, ports.get(4), new ArrayList<>()));
            vehicles.add(new Vehicle("tr-10", "Tanker trucks 2", 16, 2, 3, ports.get(0), new ArrayList<>()));
            vehicles.add(new Vehicle("tr-11", "Tanker trucks 3", 16, 16, 3, null, new ArrayList<>()));
            vehicles.add(new Vehicle("tr-12", "Tanker trucks 4", 32, 2, 6, ports.get(3), new ArrayList<>()));

            // 8 ships
            vehicles.add(new Vehicle("sh-13", "Ship 1", 80, 28, 4, ports.get(0), new ArrayList<>()));
            vehicles.add(new Vehicle("sh-14", "Ship 2", 80, 71, 4, ports.get(4), new ArrayList<>()));
            vehicles.add(new Vehicle("sh-15", "Ship 3", 80, 62, 4, ports.get(3), new ArrayList<>()));
            vehicles.add(new Vehicle("sh-16", "Ship 4", 80, 19, 4, ports.get(0), new ArrayList<>()));
            vehicles.add(new Vehicle("sh-17", "Ship 5", 80, 43, 4, ports.get(3), new ArrayList<>()));
            vehicles.add(new Vehicle("sh-18", "Ship 6", 80, 51, 4, ports.get(4), new ArrayList<>()));
            vehicles.add(new Vehicle("sh-19", "Ship 7", 80, 80, 4, null, new ArrayList<>()));
            vehicles.add(new Vehicle("sh-20", "Ship 8", 80, 80, 4, null, new ArrayList<>()));

            writeAllLines("Vehicle.dat", vehicles);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test get All method");
            admin.getAllVehicles().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test create Vehicle method");
            Vehicle newVehicle1 = new Vehicle("21", "Vehicle 21", 3, 3, 3, ports.get(1), new ArrayList<>());
            admin.createVehicle(newVehicle1);
            admin.getAllVehicles().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test update Vehicle method");
            Vehicle updateVehicle = new Vehicle("21", "Vehicle 21 Updated", 3, 3, 3, ports.get(1), new ArrayList<>());
            admin.updateVehicle(updateVehicle);
            admin.getAllVehicles().forEach(System.out::println);
            admin.getAllPorts().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test delete Vehicle method");
            admin.deleteVehicle("21");

            admin.getAllVehicles().forEach(System.out::println);


            // Container
            List<Container> containers = new ArrayList<>();

            // 6 Dry Storage containers
            containers.add(new Container("c-1", 3.5));
            containers.add(new Container("c-2", 3.5));
            containers.add(new Container("c-3", 3.5));
            containers.add(new Container("c-4", 4.6));
            containers.add(new Container("c-5", 4.6));
            containers.add(new Container("c-6", 4.6));

            // 6 Open Top containers
            containers.add(new Container("c-7", 2.8));
            containers.add(new Container("c-8", 2.8));
            containers.add(new Container("c-9", 2.8));
            containers.add(new Container("c-10", 3.2));
            containers.add(new Container("c-11", 3.2));
            containers.add(new Container("c-12", 3.2));

            // 6 Open Side containers
            containers.add(new Container("c-13", 2.7));
            containers.add(new Container("c-14", 2.7));
            containers.add(new Container("c-15", 2.7));
            containers.add(new Container("c-16", 3.2));
            containers.add(new Container("c-17", 3.2));
            containers.add(new Container("c-18", 3.2));

            // 6 Refrigerated containers
            containers.add(new Container("c-19", 4.5));
            containers.add(new Container("c-20", 4.5));
            containers.add(new Container("c-21", 4.5));
            containers.add(new Container("c-22", 5.4));
            containers.add(new Container("c-23", 5.4));
            containers.add(new Container("c-24", 5.4));

            // 6 Liquid containers
            containers.add(new Container("c-25", 4.8));
            containers.add(new Container("c-26", 4.8));
            containers.add(new Container("c-27", 4.8));
            containers.add(new Container("c-28", 5.3));
            containers.add(new Container("c-29", 5.3));
            containers.add(new Container("c-30", 5.3));






            writeAllLines("Container.dat", containers);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test get All method");
            admin.getAllContainers().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test create Container method");
            Container newContainer = new Container("31", 6.7);
            admin.createContainer(newContainer);
            admin.getAllContainers().forEach(System.out::println);

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test update Container method");
            Container updateContainer = new Container("1", 7.0);
            admin.updateContainer(updateContainer);
            admin.getAllContainers().forEach(System.out::println);


            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Test delete Container method");
            admin.deleteContainer("31");
            admin.getAllContainers().forEach(System.out::println);

        } else {
            System.out.println("Error removing data");
        }


    }
}
