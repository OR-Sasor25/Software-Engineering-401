package ParkingGarageManager;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingSystem {
	float hourlyRate;
	
	public ParkingSystem() {
		this.hourlyRate = 10.00;
	}
	
	public ParkingSystem(float hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	/**
	 * Gets the timeIn from Customer attribute and returns a fee based on 
	 * hourly rate and time stayed from timeIn to now.
	 * */
	public float calcFees(Ticket ticket) {
		LocalDateTime timeIn = LocalDateTime.parse(ticket.getTimeArrived(), DateTimeFormatter.ISO_DATE_TIME);
		Duration timeParked = Duration.between(timeIn, LocalDateTime.now());
		long hoursParked = timeParked.toHours();
		return hoursParked * hourlyRate;
	}
}
