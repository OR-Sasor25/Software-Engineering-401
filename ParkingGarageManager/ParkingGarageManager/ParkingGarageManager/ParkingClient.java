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
    public static void main(String[] args){
        // Test code without server
        ManagerGUI mgui = new ManagerGUI();
        CustomerGUI cgui = new CustomerGUI();
        //test code that demonstrated the passing data to server 
        /*
        String managerUsername = "manager";
        String managerPassword = "password";
        Boolean isGarageFull=false;
        Ticket ticket = new Ticket(123, 15.50); // Ticket with ID 123 and price $15.50
        */
        
        //Initializes Client with server address
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
			ManagerLogIn employeeIn;
    		// Handle manager login
            do {
                mgui.ManagerLoginUI(); // User enters login info
                // Check user info against server or simulate check here
                
               
        		//Object that passes a login to server
        		ManagerLogIn employeeOut = new ManagerLogIn(mgui.getEnteredUsername(),mgui.getEnteredPassword(), mgui.getEnteredGarageID());
        		oOStream.writeObject(employeeOut);
        		
        		//receives the validated login from the server
        		employeeIn = (ManagerLogIn)oIStream.readObject();
				if (employeeIn.getStatus().equals("inactive")) {
					mgui.failedLogin(); // Loop until correct
                }
    	}while (employeeIn.getStatus().equals("inactive"));
        

        // Manager's selection screen
            while (true) {
            	int managerChoice = mgui.ManagerSelectionScreen();
                if (managerChoice==0) {
                    try {
                        // Send the request to the server to write the report
                        request.write(2); 

                        // recieve it
                        String serverResponse = (String) oIStream.readObject();

                        // display the server response to the user
                        System.out.println("Server response: " + serverResponse);
                        mgui.displayMessage(serverResponse); 

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        mgui.displayMessage("Error occurred while generating the report.");
                    }
                } else if(managerChoice==1){
                	  // Start customer GUI
                    while (true) {
                        int printTicket = cgui.promptCustomerAction(); // Get customer action
                        if (printTicket==0) {
                        		request.write(0);
                        		Garage status = (Garage)oIStream.readObject();
                        		cgui.displayGarageStatus(status.checkSpace());//JOptionPane.showMessageDialog(null, "Ticket printed. Enjoy your stay!");
                        		if(!status.checkSpace()) {
                        			Ticket Customer = (Ticket)oIStream.readObject(); // This should create the Ticket_123.txt file
                        			cgui.printTicket(Customer);
                                }
                        } else if(printTicket==1)  {
                            if(cgui.displayPaymentStatus()) { // If customer chooses "Pay Ticket"
                            	//implement pay ticket here
                            }
                            
                        }else { 
                        	break; 
                        }// Exit remove one server is implemented
                    }
                	
                    //break; // Exit the loop if transitioning to the customer interface
                }else {
                	break;
                }
            }

      
        request.write(3);
    	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

