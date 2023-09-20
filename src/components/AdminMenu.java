package components;

public class AdminMenu {
    private final String username;
    private final String password;

    public AdminMenu() {
        this.username = "Admin";
        this.password = "admin";
    }

    public String get_AD_Username() {
        return username;
    }

    public String get_AD_Password() {
        return password;
    }
}
