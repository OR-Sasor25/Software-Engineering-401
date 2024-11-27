package ParkingGarageManager;
import javax.swing.*;


public class ManagerGUI {
		public ManagerGUI() {

		}
    	//ManagerLogin.pass
    	public void ManagerLoginUI() {
        String managerUsername = "manager";
        String managerPassword = "password";

        // Create a JPanel to hold the input fields
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add username field
        JTextField usernameField = new JTextField(15);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        // Add password field
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        // Show the dialog with the panel
        int option = JOptionPane.showConfirmDialog(null, panel, "Manager Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Process the input if OK is clicked
        if (option == JOptionPane.OK_OPTION) {
            String enteredUsername = usernameField.getText();
            String enteredPassword = new String(passwordField.getPassword());

            // Validate credentials
            if (managerUsername.equals(enteredUsername) && managerPassword.equals(enteredPassword)) {
                JOptionPane.showMessageDialog(null, "Welcome, " + enteredUsername + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Access denied.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login canceled.", "Canceled", JOptionPane.WARNING_MESSAGE);
        }
    }
}
