package ParkingGarageManager;
import java.util.UUID;

public class Garage {
	private final String garageID;
	private int capacity;
	private int spacesTaken;
	
	public Garage(int capacity) {
		garageID = UUID.randomUUID().toString();
		this.capacity = capacity;
		spacesTaken = 0;
	}
	
	public boolean parkVehicle() {
		if (checkSpace()) {
			spacesTaken++;
			return true;
		}
		return false;
	}
	
	public boolean removeVehicle() {
		if (spacesTaken > 0) {
			spacesTaken--;
			return true;
		}
		return false;
	}
	
	public boolean checkSpace() {
		return spacesTaken < capacity;
	}
	
	public String getID() {
		return garageID;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getSpacesTaken() {
		return spacesTaken;
	}
}
