package dev.razafindratelo.tapakilaBackend.service.userService;

public class EmailChecker {
    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
