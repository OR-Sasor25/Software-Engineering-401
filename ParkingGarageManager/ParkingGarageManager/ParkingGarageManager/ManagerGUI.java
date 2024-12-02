package ParkingGarageManager;

import javax.swing.*;

public class ManagerGUI {
	private String enteredUsername;
	private String enteredPassword;

	public ManagerGUI() {

	}

	public String getEnteredUsername() {
		return enteredUsername;
	}

	public String getEnteredPassword() {
		return enteredPassword;
	}

	public void ManagerLoginUI() {
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
		int option = JOptionPane.showConfirmDialog(null, panel, "Manager Login", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		// Process the input if OK is clicked
		if (option == JOptionPane.OK_OPTION) {
			enteredUsername = usernameField.getText();
			enteredPassword = new String(passwordField.getPassword());
		}
	}

	public void failedLogin() {
		JOptionPane.showMessageDialog(null, "Invalid username or password. Access denied.", "Login Failed",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void displayMessage(String message) 
	{
	    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}


	public Boolean ManagerSelectionScreen() {
		// Define options
		String[] options = { "Print Report", "Turn On Customer GUI" };

		// Show the dialog
		int choice = JOptionPane.showOptionDialog(null, "Welcome, " + enteredUsername + "!\nSelect an option:",
				"Manager Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		// Process the selection
		if (choice == 0) {
			// Print Report selected
			return true;
		} else if (choice == 1) {
			// Turn On Customer GUI selected
			return false;
		}

		// Default return if no valid choice is made
		return false;
	}

	// Method to handle the Print Report action
	public void printReport(GarageReports Report) {
		// Logic for printing the report (placeholder, replace with actual logic)
		System.out.println("Report is being printed...");

		// Display a confirmation panel
		JOptionPane.showMessageDialog(null, "Report Printed.", "Print Report", JOptionPane.INFORMATION_MESSAGE);
	}

}
