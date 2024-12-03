package ParkingGarageManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class ParkingServer {
		//hard coded employee credentials
	    static String managerUsername = "manager";
        static String managerPassword = "password";
        
	public static void main(String[] args) throws Exception{
		try (var listener = new ServerSocket(59898)) {
            System.out.println("The Server is running...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new RequestHandler(listener.accept()));
                System.out.println("Connection from " + listener + "!");
            }
        }
	}
	
	
	private static class RequestHandler implements Runnable{
		
		private Socket socket;
			RequestHandler(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run() {
			
			try {
				//Input and Output streams to allow passing objects between server and client
				ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream oIStream = new ObjectInputStream(socket.getInputStream());
				InputStream request = (socket.getInputStream());
				
				Garage garage1 = new Garage(10);
				Garage garage2 = new Garage(15);
				Garage garage3 = new Garage(20);
				Garage garage4 = new Garage(25);
				Garage garage5 = new Garage(6);
				Garage[] Garages = {garage1, garage2, garage3, garage4, garage5};
				boolean canLogOut = false;
				int garageID;
				//creates a new report object that will be updated with the methods included 
				GarageReports report = new GarageReports();
				ManagerLogIn employee;
				do {
					//variable that reads ManagerLogInObjects
					employee = (ManagerLogIn)oIStream.readObject();
					
					//if no Manager login objects are passed assume employee.getID is null and login was cancelled
					if(employee.getId() == null){
						System.out.println("Login was cancelled, terminating conection");
						
					}//if the attributes of employee match managerUsername and managerPassword allow the employee to modify a garage
					else if(employee.getId().equals(managerUsername) && employee.getPassword().equals(managerPassword) && employee.getGarageID() < 5 
							&& employee.getGarageID() >= 0) {
						canLogOut = true;
						
						//takes received login, validates it, then passes it back to the client
						employee.validateLogin(true);
						oOStream.writeObject(employee);
						System.out.println("login succesfull!");
						
						//initialize garageID
						garageID = employee.getGarageID();

						//initializes a new garage object if one does not exist at the requested index of Garages[]
						Ticket[] Customers = new Ticket[Garages[garageID].getCapacity()];
						
						//request are passed to choice as an int
						int choice;
						boolean running = true;
						do {
							System.out.println("Awaiting Request...");
							choice = request.read();
							System.out.println("request "+ choice + " received" );
							
							
							//Request are as follows - implementation status
							//0 = doAddTicket - It works poorly
							//1 = doPayTicket - fully implemented 
							//2 = doWriteReport - fully implemented
							//3 = doRetreiveTicket - not implemented - cancelled
							//3 = logout - works
							// All of these should be called from the client
							switch(choice) {
							case 0:
								doAddTicket(oIStream, oOStream, Garages[garageID], Customers, report);
								break;
							case 1: 
								Ticket carOut = (Ticket)oIStream.readObject();
								doPayTicket(oIStream, oOStream, Garages[garageID], Customers, carOut);
								break;
							case 2:	
								doWriteReport(oIStream, oOStream, report);
								break;
							case 3: 
								System.out.println("Client is loging out");
								running = false;
								break;
							default: 
								System.out.println("INVALID REQUEST");
								break;			
							}
							
						}while(running);
					}else if(employee.getGarageID() > 5 || employee.getGarageID() < 0){
						System.out.println("Invalid Garage ID");
						oOStream.writeObject(employee);
					}else {
						System.out.println("Invalid Username or Password");
						oOStream.writeObject(employee);
					}
					
				}while(canLogOut == false);
				
				
			} catch (IOException e) {
				System.out.println("Error:" + socket);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
                    socket.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
                System.out.println("Conection from Garage Terminated");
            }
		}
		
		private void doAddTicket(ObjectInputStream ois, ObjectOutputStream oos, 
				Garage local, Ticket[] Customers, GarageReports report){
				try {
					
					oos.writeObject(local);
					if(!(local.getSpacesTaken()== Customers.length)){
						Ticket carIn = new Ticket();
						System.out.println("adding customer");
						Customers[local.getSpacesTaken()] = carIn;
						report.setCarTracker();
						System.out.println("Report updated");
						oos.writeObject(carIn);
						
						System.out.println("Parking customer " + carIn.getTicketID());
					}else{
						System.out.println("Garrage is full!");
					}
						
				} catch (IOException e) {
					e.printStackTrace();
				}	
		}
		private void doPayTicket(ObjectInputStream ois, ObjectOutputStream oos, Garage local, Ticket[] Customers, Ticket carOut) {
			
		    try {
		        System.out.println("Finding ticket...");
		        
		        // Search for the ticket in the Customers array
		        Ticket foundTicket = null;
		        for (Ticket customerTicket : Customers) {
		            if (customerTicket != null && customerTicket.getTicketID() == carOut.getTicketID()) {
		                foundTicket = customerTicket;
		                break;
		            }
		        }

		        if (foundTicket != null) {
		            System.out.println("Ticket found: ");
		            foundTicket.printTicket();

		            // Define hourly rate
		            double hourlyRate = 5.0; // Example rate: $5/hour

		            // Calculate price and mark as paid
		            foundTicket.calculatePrice(hourlyRate);
		            foundTicket.setPaidStatus(true);

		            System.out.println("Processing payment...");
		            System.out.println("Payment successful! Ticket details:");
		            foundTicket.printTicket();

		            // Optionally send confirmation to client
		            oos.writeObject("Payment successful for ticket ID: " + foundTicket.getTicketID());
		        } else {
		            System.out.println("Ticket not found.");
		            oos.writeObject("Error: Ticket not found.");
		        }

		    } catch (IOException e) {
		        e.printStackTrace();
		        try {
		            oos.writeObject("Error during payment process.");
		        } catch (IOException ioException) {
		            ioException.printStackTrace();
		        }
		    }
		}
		private void doWriteReport(ObjectInputStream ois, ObjectOutputStream oos, GarageReports report) 
		{
		    try 
		    {
		        // Fetch the report details
		        String reportDetails = report.toString();

		        // Write the report to a file
		        String filename = "GarageReport_" + report.getID() + ".txt";
		        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) 
		        {
		            writer.println(reportDetails);
		        }

		        // Confirm back to the client 
		        oos.writeObject("Report written successfully to " + filename);
		        System.out.println("Report written to: " + new File(filename).getAbsolutePath());
		    } 
		    catch (IOException e) 
		    {
		        try 
		        {
		            oos.writeObject("Failed to write the report.");
		        } catch (IOException ex) 
		        {
		            ex.printStackTrace();
		        }
		        e.printStackTrace();
		    }
		}
    }
}
