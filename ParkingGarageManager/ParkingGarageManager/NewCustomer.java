// cs401 group 1 - project
// updated on 11/27
package ParkingGarageManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Customer {
    
private String licensePlate;

private String timeArrived;

// add a method that asks the user to input their license plate, by using a scanner.in
// when this constructor is being created, it is being created within the GUI so it needs the scanner function
public Customer(Ticket ticketPrice){

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter your license plate:");
     this.licensePlate = scanner.nextline();
     this.timeArrived = LocalDateTime();
     this.ticketPrice = ticketPrice; // pass the object here and storing the Ticket object

}

public String getLicensePlate() {
    return licensePlate;
}

public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
}


public String getTimeArrived() {
    return timeArrived;
}

public void setTimeArrived(String timeArrived) {
    this.timeArrived = timeArrived;
}


}

