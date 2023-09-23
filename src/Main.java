import components.AdminMenu;
import components.ManagerMenu;
import entities.user.PortManager;
import entities.user.SystemAdmin;
import entities.user.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isLoggedIn = false;
        User user = null;
        int failedAttempts = 0;

        while (!isLoggedIn && failedAttempts < 5) {
            System.out.println("Choose your role:");
            System.out.println("1. SystemAdmin");
            System.out.println("2. PortManager");
            System.out.println("3. Log out");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        user = new SystemAdmin("admin", "admin");
                        isLoggedIn = authenticate(user);
                        break;
                    case 2:
                        String[] usernames = {"manager1", "manager2", "manager3", "manager4", "manager5"};
                        String[] passwords = {"password1", "password2", "password3", "password4", "password5"};
                        user = new PortManager(usernames, passwords);
                        isLoggedIn = authenticate(user);
                        break;
                    case 3:
                        System.out.println("Logged out.");
                        isLoggedIn = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
            }
        }
    }

    public static boolean authenticate(User user) {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int maxAttempts = 5;
        boolean loggedIn = false;
        boolean validUsername = false;

        while (attempts < maxAttempts) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (user instanceof SystemAdmin) {
                if (username.equals(((SystemAdmin) user).getUsername()) && password.equals(((SystemAdmin) user).getPassword())) {
                    System.out.println("Login Successful!");
                    System.out.println("Welcome SystemAdmin!");
                    System.out.println("");
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.displayMenu();
                    loggedIn = true;
                    break;
                } else if (!username.equals(((SystemAdmin) user).getUsername())) {
                    System.out.println("Account does not exist.");
                } else {
                    System.out.println("Incorrect password.");
                }
            } else if (user instanceof PortManager) {
                String[] usernames = ((PortManager) user).getUsernames();
                String[] passwords = ((PortManager) user).getPasswords();
                validUsername = false;

                try {
                    for (int i = 0; i < usernames.length; i++) {
                        if (username.equals(usernames[i])) {
                            validUsername = true;
                            if (!password.equals(passwords[i])) {
                                System.out.println("Incorrect password.");
                                break;
                            } else {
                                System.out.println("Login Successful!");
                                System.out.println("Welcome PortManager " + username + "!");
                                System.out.println("");
                                ManagerMenu managerMenu = new ManagerMenu();
                                managerMenu.displayMenu();
                                loggedIn = true;
                                return loggedIn; // Kết thúc chương trình khi người dùng nhập đúng tài khoản PortManager
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Account does not exist.");
                }

                if (!validUsername) {
                    System.out.println("Account does not exist.");
                }
            }

            attempts++;
            System.out.println("Number of failed attempts: " + attempts);
        }

        if (!loggedIn) {
            System.out.println("Exceeded maximum number of attempts. Exiting program.");
        }

        return loggedIn; // Return the login status
    }
}
