package ParkingGarageManager;
import javax.swing.*;

public class ManagerGUI {
	private String enteredUsername;
	private String enteredPassword;
	private int enteredGarageID;
	public ManagerGUI() {

	}
	public String getEnteredUsername() {
		return enteredUsername;
	}
	public String getEnteredPassword() {
		return enteredPassword;
	}

	public int getEnteredGarageID() {
		return enteredGarageID;
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

		// Add garage id field
		JTextField garageIDField = new JTextField(15); // Use JTextField for numeric input
		panel.add(new JLabel("Garage ID:"));
		panel.add(garageIDField);

		// Show the dialog with the panel
		int option = JOptionPane.showConfirmDialog(null, panel, "Manager Login", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		// Process the input if OK is clicked
		if (option == JOptionPane.OK_OPTION) {
			enteredUsername = usernameField.getText();
			enteredPassword = new String(passwordField.getPassword());
			try {
				enteredGarageID = Integer.parseInt(garageIDField.getText()); // Correctly parse integer
				if(enteredGarageID < 0 || enteredGarageID > 4) {
					throw new Exception();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid Garage ID. Please enter a numeric value.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid Garage ID. Garage ID is not between 0 and 4", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void failedLogin() {
		JOptionPane.showMessageDialog(null, "Invalid username or password. Access denied.", "Login Failed",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void displayMessage(String message) {
	    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	} 

	public int ManagerSelectionScreen() {
	    // Define options
	    String[] options = { "Print Report", "Turn On Customer GUI" , "Logout"};

	    // Show the dialog
	    int choice = JOptionPane.showOptionDialog(null, "Welcome, " + enteredUsername + "!\nSelect an option:",
	            "Manager Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

	    // Process the selection
	    if (choice == 0) {
	        // Print Report selected
	        return 0;
	    } else if (choice == 1) {
	        // Turn On Customer GUI selected
	        return 1;
	    }else {
	    	return 2;
	    }

	    // Return 2 if the dialog is closed without selection (e.g., "X" pressed)
	}

	// Method to handle the Print Report action
	public void printReport(GarageReports Report) {
		// Logic for printing the report (placeholder, replace with actual logic)
		System.out.println("Report is being printed...");

		// Display a confirmation panel
		JOptionPane.showMessageDialog(null, "Report Printed.", "Print Report", JOptionPane.INFORMATION_MESSAGE);
	}

}
