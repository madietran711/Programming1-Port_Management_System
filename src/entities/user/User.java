package entities.user;

import service.User.UserInterface;
import service.User.implementation.UserImplement;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
    private final UserInterface userImplement = new UserImplement(this);
    private String ID;
    private String username;
    private String password;
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private int loginAttempts = 1;

    public User() {
    }

    public User(String ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userImplement=" + userImplement +
                '}';
    }

    public void runLoginProcess() {
        boolean loggedIn = false;
        while (!loggedIn && loginAttempts <= MAX_LOGIN_ATTEMPTS) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Password: ");
            String enteredPassword = scanner.nextLine();

            try {
                login(enteredUsername, enteredPassword);
                loggedIn = true;
            } catch (IncorrectPasswordException | AccountNotFoundException e) {
                loginAttempts++;
                System.out.println(e.getMessage());
                if (loginAttempts > MAX_LOGIN_ATTEMPTS) {
                    System.out.println("Exceeded the maximum allowed login attempts.");
                    break;
                }
            }
        }
    }


    private boolean checkLogin(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    public void login(String enteredUsername, String enteredPassword) throws IncorrectPasswordException, AccountNotFoundException {
        if (this.username.equals(enteredUsername)) {
            if (checkLogin(enteredUsername, enteredPassword)) {
                if (this instanceof SystemAdmin) {
                    System.out.println("Welcome Admin " + this.username);
                } else if (this instanceof PortManager) {
                    System.out.println("Welcome Manager " + this.username);
                }
            } else {
                throw new IncorrectPasswordException("Incorrect password entered. (" + loginAttempts + "/" + MAX_LOGIN_ATTEMPTS + ")");
            }
        } else {
            throw new AccountNotFoundException("Wrong account. (" + loginAttempts + "/" + MAX_LOGIN_ATTEMPTS + ")");
        }
    }

    static class IncorrectPasswordException extends Exception {
        public IncorrectPasswordException(String message) {
            super(message);
        }
    }

    static class AccountNotFoundException extends Exception {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }
    public static void main(String[] args) {
        SystemAdmin admin1 = new SystemAdmin("admin1", "password1");
        admin1.runLoginProcess();
    }}
