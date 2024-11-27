package ParkingGarageManager;
/*need Items to be passed here:
 * Manager:
 *	 	manager login and password to be sent to server validation to be sent back
 * 		report for manager
 * 
 * Customer:
 * 		is garage full?
 * 		ticket with unique id, timestamp, and unpaidstatus sent to client
 * 
 * 		ticket with paid status and amount for report processing sent to server
 * 		
 * 
 * */
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