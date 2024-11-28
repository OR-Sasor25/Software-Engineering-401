package ParkingGarageManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
    	
    	Scanner scan = new Scanner(System.in);
    	if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
    	
    	try (var socket = new Socket(args[0], 59898)) {
    		//Input and Output streams to allow passing objects between server and client
			ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream oIStream = new ObjectInputStream(socket.getInputStream());
			OutputStream request = (socket.getOutputStream());
			
    		//some test code for the server
    		ManagerLogIn employee = new ManagerLogIn("manager", "password");
    		oOStream.writeObject(employee);
    		
    		
    		for(int i = 0; i < 4; i++) {
    			int choice = scan.nextInt();
    			request.write(choice);
    		}
    		
    		// Simulate the garage status
            boolean isGarageFull = false; // Example value, change to test the behavior

            cgui.displayGarageStatus(isGarageFull); // Call the second function
            if (!isGarageFull) {
                cgui.promptCustomerAction();
           }
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

