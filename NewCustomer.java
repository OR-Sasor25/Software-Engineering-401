
// cs401 group 1 - project

public class Customer {
    
private String licensePlate;
private String timeArrived;

private Ticket ticketPrice; /* this will create a reference instance of the ticket class allowing the payForParking method in this class 
to use the ticket class calculatePrice() method. This reference instance will be passed into the constuctor */
// with new is involking the constuctor and without "new", its refering to the class name


// add a method that asks the user to input their license plate, by using a scanner.in
// when this constructor is being created, it is being created within the GUI so it needs the scanner function
public Customer(String licensePlate, String timeArrived, Ticket ticketPrice){

     this.licensePlate = licensePlate;
     this.timeArrived = timeArrived;
     this.ticketPrice = ticketPrice; // pass the object here

}



public void payForParking() {

    double feeToPay = ticketPrice.getFee();
    System.out.println("Your payment details: license Plate, Time Arrived, Amount Due:" + licensePlate + timeArrived + feeToPay);
}

}


// example of the ticket class could look like

public class ticket {
private double fee;

//constuctor 
public ticket(double fee) {

this.fee = fee;

}

//method
public double getFee(){

    return fee;

}

}
 
// example of what it would look like in the main 

public class Main {
    public static void main(String[] args) {
        
        Ticket ticketFee = new Ticket(7.00); // Create FeeCalculation object
        
        Customer customer = new Customer("7LOL123", "8:43am", ticketFee); // Pass it to Customer

        customer.payForParking(); // Output: Your payment details: license Plate, Time Arrived, Amount Due: "7LOL123", "8:43am", 7.00
    }
}
 