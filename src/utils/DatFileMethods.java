package utils;

import entities.container.Container;
import entities.port.Port;
import entities.user.SystemAdmin;
import entities.vehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<Container> containers = new ArrayList<>();
        ports.add(new Port("1", "Port 1", 1, 1, "Port 1", 1, 1, true, new ArrayList<>(),new ArrayList<>(), Collections.emptyList()));
        ports.add(new Port("2", "Port 2", 2, 2, "Port 2", 2, 2, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
        containers.add(new Container("1", 1.0));
        containers.add(new Container("2", 2.0));
        writeAllLines("Port.dat", ports);
        writeAllLines("Container.dat", containers);
        SystemAdmin admin = new SystemAdmin("1", "admin", "admin");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllPorts().forEach(System.out::println);
        admin.getAllContainers().forEach(System.out::println);





        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test create method");
        Container newContainer = new Container("1", 6.0);
        Port newPort = new Port("3", "Port 3", 3, 3, "Port 3", 3, 3, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        Port newPort_Nghia = new Port("4", "Port 4", 4, 4, "Port 4 by Nghia", 4, 4, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        admin.createContainer(newContainer);
        admin.createPort(newPort);
        admin.createPort(newPort_Nghia);
        ports = admin.getAllPorts();
        containers = admin.getAllContainers();
        Vehicle newVehicle = new Vehicle("1", "Vehicle 1", 1,1,1,ports.get(1), new ArrayList<>());
        System.out.println(newVehicle);
        admin.updatePort(ports.get(1));
        admin.getAllPorts().forEach(System.out::println);
        admin.updateContainer((containers.get(1)));
        admin.getAllContainers().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test update method");
        Port updatePort = new Port("3", "Port 3 Updated", 3, 3, "Port 3", 3, 3, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        Port Nghia_updatedPort = new Port("4", "Port 4", 3, 3, "Updated by Nghia", 4, 4, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        admin.updatePort(updatePort);
        admin.updatePort(Nghia_updatedPort);
        admin.getAllPorts().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test delete method");
        admin.deletePort("3");
        admin.deletePort("4");
        admin.getAllPorts().forEach(System.out::println);


    }
}
