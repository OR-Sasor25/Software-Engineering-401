package ParkingGarageManager;

public class ParkingClient {
    public static void main(String[] args) {
    	ManagerGUI mgui = new ManagerGUI();
    	CustomerGUI cgui = new CustomerGUI();
    	mgui.ManagerLoginUI();
    	
        
        // Simulate the garage status
        boolean isGarageFull = false; // Example value, change to test the behavior
        
        cgui.displayGarageStatus(isGarageFull); // Call the second function
        if (!isGarageFull) {
            cgui.promptCustomerAction();
        }
    }
}