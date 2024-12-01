package ParkingGarageManager;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingSystem {
	float hourlyRate;
	
	public ParkingSystem(float hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	/**
	 * Gets the timeIn from Customer attribute and returns a fee based on 
	 * hourly rate and time stayed from timeIn to now.
	 * */
	public float calcFees(Customer customer) {
		LocalDateTime timeIn = customer.getTimeIn();
		Duration timeParked = Duration.between(timeIn, LocalDateTime.now());
		long hoursParked = timeParked.toHours();
		return hoursParked * hourlyRate;
	}
}
