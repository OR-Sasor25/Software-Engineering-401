package ParkingGarageManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;


public class ParkingServer {
		//hard coded employee credentials
	    static String managerUsername = "manager";
        static String managerPassword = "password";
        static Garage[] Garages = new Garage[10];
        
        
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
				boolean canLogOut = false;
				//Should be used in payTicket
				ParkingSystem location = new ParkingSystem(7.50f);
				ManagerLogIn employee;
				do {
					//variable that reads ManagerLogInObjects
					employee = (ManagerLogIn)oIStream.readObject();
					
					//if no Manager login objects are passed assume employee.getID is null and login was cancelled
					if(employee.getId() == null){
						System.out.println("Login was cancelled, terminating conection");
						
					}//if the attributes of employee match managerUsername and managerPassword allow the employee to modify a garage
					else if(employee.getId().equals(managerUsername) && employee.getPassword().equals(managerPassword) && employee.getGarageID() < 10 
							&& employee.getGarageID() >= 0) {
						canLogOut = true;
						
						//takes received login, validates it, then passes it back to the client
						employee.validateLogin(true);
						oOStream.writeObject(employee);
						System.out.println("login succesfull!");
						
						//initializes a new garage object if one does not exist at the requested index of Garages[]
						if(Garages[employee.getGarageID()] == null) {
							Garages[employee.getGarageID()] = new Garage(10);
						}
						
						Ticket[] Customers = new Ticket[Garages[employee.getGarageID()].getCapacity()];
						
						//request are passed to choice as an int
						int choice;
						boolean running = true;
						do {
							System.out.println("Awaiting Request...");
							choice = request.read();
							System.out.println("request "+ choice + " received" );
							
							
							//Request are as follows - implementation status
							//0 = doAddTicket - I assume it works, there are issues with the customer class that might prevent them from working
							//1 = doPayTicket - partially implemented 
							//2 = doWriteReport - not implemented
							//3 = doRetreiveTicket - not implemented - cancelled
							//3 = logout - works
							// All of these should be called from the client
							switch(choice) {
							case 0:
								doAddTicket(oIStream, oOStream, Garages[employee.getGarageID()], Customers);
								System.out.println("Parking customer " + Customers[Garages[employee.getGarageID()].getSpacesTaken()-1].getTicketID());
								break;
							case 1: 
								Ticket carOut = (Ticket) oIStream.readObject();
								doPayTicket(oIStream, oOStream, Garages[employee.getGarageID()], Customers, carOut , location);
								break;
							case 2:	
								doWriteReport();
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
					}else if(employee.getGarageID() > 10 || employee.getGarageID() < 0){
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
		
		private void doAddTicket(ObjectInputStream ois, ObjectOutputStream oos, Garage local, Ticket[] Customers) {
			if(local.checkSpace()) {
				try {
					Ticket carIn = (Ticket)ois.readObject();
					System.out.println("adding customer");
					Customers[local.getSpacesTaken()] = carIn;
					local.parkVehicle();
					
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}	
				

			}else {
				System.out.println("Garrage is full!");
			}
		}
		
		private void doPayTicket(ObjectInputStream ois, ObjectOutputStream oos, Garage local, Ticket[] Customers, Ticket carOut, ParkingSystem location){
			try {
				carOut = (Ticket)ois.readObject();
				System.out.println("finding ticket");
			
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
	
				e.printStackTrace();
			}
			System.out.println("paying ticket");
			
		}
		
		private void doWriteReport() {
			
			System.out.println("writing report");
		}
	}
}
