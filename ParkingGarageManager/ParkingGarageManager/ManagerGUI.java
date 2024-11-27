package ParkingGarageManager;
import javax.swing.*;

public class ManagerGUI {

    public static void main(String[] args) {
        // Hardcoded manager credentials for simplicity
        String managerUsername = "manager";
        String managerPassword = "password123";

        // Login process
        String enteredUsername = JOptionPane.showInputDialog(null, "Enter Username:", "Manager Login", JOptionPane.PLAIN_MESSAGE);
        if (enteredUsername == null) return; // User clicked cancel

        String enteredPassword = JOptionPane.showInputDialog(null, "Enter Password:", "Manager Login", JOptionPane.PLAIN_MESSAGE);
        if (enteredPassword == null) return; // User clicked cancel

        // Validate credentials
        if (managerUsername.equals(enteredUsername) && managerPassword.equals(enteredPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome, " + enteredUsername + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Access denied.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
