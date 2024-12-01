package ParkingGarageManager;

<<<<<<< Updated upstream

public class Ticket {
	
    private int ticketID;
    private double price;
    private boolean paidStatus;

=======
import java.time.LocalDateTime;

public class Ticket {
    
    private int ticketID;
    private double price;
    private boolean paidStatus;
    private String timeArrived;
>>>>>>> Stashed changes
    
    public Ticket(int ticketID, double price) {
        this.ticketID = ticketID;
        this.price = price;
<<<<<<< Updated upstream
        this.paidStatus = false; 
    }

    // the gettrs and setters
    
    public int getTicketID() {
    	
=======
        this.paidStatus = false;
        this.timeArrived = LocalDateTime.now().toString(); // Correct usage of LocalDateTime
    }

    // Getters and Setters
    public int getTicketID() {
>>>>>>> Stashed changes
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public double getPrice() {
<<<<<<< Updated upstream
    	
        return price;
        
=======
        return price;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
    public String getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(String timeArrived) {
        this.timeArrived = timeArrived;
    }
    
    // Method to create a new ticket
>>>>>>> Stashed changes
    public void createTicket(int id, double initialPrice) {
        this.ticketID = id;
        this.price = initialPrice;
        this.paidStatus = false;
    }

<<<<<<< Updated upstream
=======
    // Method to calculate the price based on hours parked and rate
>>>>>>> Stashed changes
    public void calculatePrice(double hoursParked, double ratePerHour) {
        this.price = hoursParked * ratePerHour;
    }

<<<<<<< Updated upstream
    public void printTicket() {
        System.out.println("The ticket ID is: " + ticketID);
        System.out.println("the total price is: $" + price);
=======
    // Method to print ticket details
    public void printTicket() {
        System.out.println("The ticket ID is: " + ticketID);
        System.out.println("The total price is: $" + price);
>>>>>>> Stashed changes
        System.out.println("Paid: " + (paidStatus ? "Yes" : "No"));
    }
}
