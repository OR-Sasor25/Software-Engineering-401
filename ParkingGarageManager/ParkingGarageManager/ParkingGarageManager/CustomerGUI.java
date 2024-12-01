package ParkingGarageManager;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class CustomerGUI {

	public CustomerGUI() {
	}

	// Prompt customer to print or pay for a ticket
	public boolean promptCustomerAction() {
		String[] options = { "Print Ticket", "Pay Ticket" };
		int choice = JOptionPane.showOptionDialog(null, "Welcome! What would you like to do?", "Parking Garage",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		// Return true for "Print Ticket", false for "Pay Ticket"
		return choice == 0;
	}

	// Display welcome message or say Garage Full
	public void displayGarageStatus(boolean isGarageFull) {
		if (isGarageFull) {
			JOptionPane.showMessageDialog(null, "Sorry, the garage is full.", "Garage Full",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Welcome to the Parking Garage!", "Welcome",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Display amount owed and ticket paid status based on file selection
	public Boolean displayPaymentStatus() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select a Ticket File");

		// Open the file chooser dialog
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
				// Read ticket data from the file
				String ticketID = reader.readLine();
				double amountOwed = Double.parseDouble(reader.readLine());
				boolean isTicketPaid = reader.readLine().equalsIgnoreCase("Yes");

				// Display payment status based on ticket data
				if (isTicketPaid) {
					JOptionPane.showMessageDialog(null, "Your ticket is already paid. Thank you!", "Payment Status",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					while(true) {
					String input = JOptionPane.showInputDialog(null,
							"Your amount owed is $" + amountOwed + ".\nEnter payment amount:", "Payment",
							JOptionPane.PLAIN_MESSAGE);

					if (input != null) {
						try {
							double payment = Double.parseDouble(input);
							
								if (payment >= amountOwed) {
									JOptionPane.showMessageDialog(null, "Payment received. Thank you!",
											"Payment Successful", JOptionPane.INFORMATION_MESSAGE);
									return true;
									// how do i change ticket paid status :/ is it even worth it?
								} else {
									JOptionPane.showMessageDialog(null,
											"Insufficient payment. Please pay the full amount.", "Payment Failed",
											JOptionPane.ERROR_MESSAGE);
								}
							
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					}
				}
			} catch (IOException | NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "An error occurred while reading the ticket file.", "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		return false;
	}

	// Method to print ticket to a .txt file
	public void printTicket(Ticket ticket) {
		try {
			// Create a new file with the ticket ID as the filename
			File ticketFile = new File("Ticket_" + ticket.getTicketID() + ".txt");
			if (ticketFile.createNewFile()) {
				FileWriter writer = new FileWriter(ticketFile);
				// Write ticket details to the file
				writer.write(ticket.getTicketID() + "\n");
				writer.write(ticket.getPrice() + "\n");
				writer.write((ticket.isPaidStatus() ? "Yes" : "No") + "\n");
				writer.close();
				JOptionPane.showMessageDialog(null, "Ticket has been printed to: " + ticketFile.getName(),
						"Ticket Printed", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred while printing the ticket.", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
