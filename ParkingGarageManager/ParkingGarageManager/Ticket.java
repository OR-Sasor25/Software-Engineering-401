package ParkingGarageManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    
    private int ticketID;
    private double price;
    private boolean paidStatus;
    private String timeArrived;
    
    public Ticket(int ticketID, double price) {
        this.ticketID = ticketID;
        this.price = price;
        this.paidStatus = false;
        this.timeArrived = LocalDateTime.now().toString(); // Correct usage of LocalDateTime
    }

    // Getters and Setters
    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(String timeArrived) {
        this.timeArrived = timeArrived;
    }
    
    // Method to create a new ticket
    public void createTicket(int id, double initialPrice) {
        this.ticketID = id;
        this.price = initialPrice;
        this.paidStatus = false;
    }

    // Method to calculate the price based on hours parked and rate
    public void calculatePrice(double ratePerHour) {
    	// Convert timeArrived string to LocalDateTime
		LocalDateTime timeIn = LocalDateTime.parse(timeArrived, DateTimeFormatter.ISO_DATE_TIME);
		// Calculate duration parked
		Duration timeParked = Duration.between(timeIn, LocalDateTime.now());
		// Convert to hours
		long hoursParked = timeParked.toHours();
		
        this.price = hoursParked * ratePerHour;
    }

    // Method to print ticket details
    public void printTicket() {
        System.out.println("The ticket ID is: " + ticketID);
        System.out.println("The total price is: $" + price);
        System.out.println("Paid: " + (paidStatus ? "Yes" : "No"));
    }
}
