package components;

public class ManagerMenu {
    private String username;
    private final String password;

    public ManagerMenu() {
        this.username = "Port_manager";
        this.password = "manager";
    }

    public String get_PM_Username() {
        return username;
    }

    public String get_PM_Password() {
        return password;
    }
}
