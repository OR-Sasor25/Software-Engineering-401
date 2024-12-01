package ParkingGarageManager;

import java.time.LocalDateTime;

public class Ticket {
	
    private int ticketID;
    private double price;
    private boolean paidStatus;
    private String timeArrived;
    
    public Ticket(int ticketID, double price) {
        this.ticketID = ticketID;
        this.price = price;
        this.paidStatus = false; 
        this.timeArrived = LocalDateTime();
        this.ticketPrice = ticketPrice; // pass the object here and storing the Ticket object

    }

    // the gettrs and setters
    
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

    public void createTicket(int id, double initialPrice) {
        this.ticketID = id;
        this.price = initialPrice;
        this.paidStatus = false;
    }

    public void calculatePrice(double hoursParked, double ratePerHour) {
        this.price = hoursParked * ratePerHour;
    }

    public void setTimeArrived(String timeArrived) {
        this.timeArrived = timeArrived;
    }
    
    public String getTimeArrived() {
        return timeArrived;
    }
    

    public void printTicket() {
        System.out.println("The ticket ID is: " + ticketID);
        System.out.println("the total price is: $" + price);
        System.out.println("Paid: " + (paidStatus ? "Yes" : "No"));
    }





}
