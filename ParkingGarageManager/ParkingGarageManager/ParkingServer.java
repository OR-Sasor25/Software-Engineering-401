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
				
				//variable that reads ManagerLogInObjects
				ManagerLogIn employee = (ManagerLogIn)oIStream.readObject();
				
				//if the attributes of employee match managerUsername and managerPassword allow the employee to modify a garage
				if(employee.getId().equals(managerUsername) && employee.getPassword().equals(managerPassword)) {
					//request are passed to choice as an int
					int choice;
					do {
						choice = request.read();
						switch(choice) {
						case 0: doAddTicket();
						case 1: doPayTicket();
						case 2:	doWriteReport();
						case 3: doRetrieveTicket();
						case 4: System.out.print("Client is loging out");
						default: System.out.print("INVALID REQUEST");
							break;
						
						}
					}while(choice != 4);
				}else {
					System.out.print("Invalid Username or Password");
				}
				
			} catch (IOException e) {
				System.out.println("Error:" + socket);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Conection from Garage Terminated");
            }
		}
		//methods for passing objects into server and modifying garages
		private void doRetrieveTicket() {
			// TODO Auto-generated method stub
			
		}
		private void doAddTicket() {
			// TODO Auto-generated method stub
			
		}
		private void doPayTicket() {
			// TODO Auto-generated method stub
			
		}
		private void doWriteReport() {
			// TODO Auto-generated method stub
			
		}
	}
}
