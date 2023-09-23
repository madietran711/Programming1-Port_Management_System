package components;

import entities.port.Port;
import entities.user.PortManager;

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
            System.out.println("+--------------------------------+");
            System.out.println("|   Port Manager Menu: "+portManager.getManagingPort().getName()+"     |");
            System.out.println("|---------------------------------|");
            System.out.println("| 1. Manage Containers            |");
            System.out.println("| 5. Exit                         |");
            System.out.println("+--------------------------------+");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageContainer();
                    break;
                case 5:
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
