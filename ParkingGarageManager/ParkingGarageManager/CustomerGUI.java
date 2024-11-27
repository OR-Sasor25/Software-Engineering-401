package ParkingGarageManager;

import javax.swing.*;

public class CustomerGUI {
	public CustomerGUI() {

	}
    //Prompt customer to print or pay for a ticket
    public void promptCustomerAction() {
        String[] options = {"Print Ticket", "Pay Ticket"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Welcome! What would you like to do?",
                "Parking Garage",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        
        if (choice == 0) {
            JOptionPane.showMessageDialog(null, "Ticket printed. Enjoy your stay!");
        } else if (choice == 1) {
            displayPaymentStatus(); // Calls the third function to handle payment
        }
    }
    
    // Display welcome message or say Garage Full
    public void displayGarageStatus(boolean isGarageFull) {
        if (isGarageFull) {
            JOptionPane.showMessageDialog(null, "Sorry, the garage is full.", "Garage Full", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Welcome to the Parking Garage!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Display amount owed and ticket paid status
    public void displayPaymentStatus() {
        boolean isTicketPaid = false; // Temporary variable for ticket payment status
        double amountOwed = 10.00; // Temporary variable for amount owed (example value)
        
        if (isTicketPaid) {
            JOptionPane.showMessageDialog(null, "Your ticket is already paid. Thank you!", "Payment Status", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Your amount owed is $" + amountOwed + ".\nEnter payment amount:",
                    "Payment",
                    JOptionPane.PLAIN_MESSAGE
            );
            
            if (input != null) {
                try {
                    double payment = Double.parseDouble(input);
                    if (payment >= amountOwed) {
                        JOptionPane.showMessageDialog(null, "Payment received. Thank you!", "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
                        isTicketPaid = true; // Update the payment status
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient payment. Please pay the full amount.", "Payment Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}