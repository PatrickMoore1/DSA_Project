/*
 * Purpose: Data Structure and Algorithms Final Project
 * Status: Incomplete
 * Last update: 04/12/19
 * Submitted:  04/12/19
 * @author: Patrick Moore, Kevin Nguyen
 * @version: 2019.04.12
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

	static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{

		boolean firstLine = true;

		System.out.println("Welcome to the Wonderful Movie Theater program!");
		System.out.println("	Tonight's features are:");
		System.out.println("		'Shazam!' and 'Dumbo'\n");

		System.out.println("Please specify the size of the Movie Theaters");

		System.out.println("	Enter the information about the Dumbo Movie Theater:");		
		System.out.print("		>>Enter number of rows: ");
		int dumboRows = Integer.parseInt(stdin.readLine());
		System.out.println(dumboRows);   
		System.out.print("		>>Enter number of seats in a row: ");
		int dumboSeats = Integer.parseInt(stdin.readLine());
		System.out.println(dumboSeats);   

		System.out.println("	Enter the information about the Shazam! Movie Theater:");		
		System.out.print("		>>Enter number of rows: ");
		int shazamRows = Integer.parseInt(stdin.readLine());
		System.out.println(shazamRows);   
		System.out.print("		>>Enter number of seats in a row: ");
		int shazamSeats = Integer.parseInt(stdin.readLine());
		System.out.println(shazamSeats + "\n");   

		System.out.print("		>>Enter the price of a ticket: ");
		double ticketPrice = Double.parseDouble(stdin.readLine());
		System.out.println(ticketPrice + "\n");

		Theater dumbo = new Theater(dumboRows, dumboSeats);
		Theater shazam = new Theater(shazamRows, shazamSeats);

		MovieTheater movieTheater = new MovieTheater(ticketPrice, dumbo, shazam);

		System.out.println("Select from the following menu: ");
		System.out.println("		0. End the program.");
		System.out.println("		1. Customer(s) enter(s) Movie Theater.");
		System.out.println("		2. Customer buys ticket(s).");
		System.out.println("		3. Customer(s) leave(s) the theater.");
		System.out.println("		4. Display info about customers waiting for tickets.");
		System.out.println("		5. Display seating chart for Shazam! Movie Theater.");
		System.out.println("		6. Display seating chart for Dumbo Movie Theater.");
		System.out.println("		7. Display number of tickets sold and total earnings.");

		boolean running = true;

		while(running) {
			System.out.print("\n>>Make your menu selection now: ");
			int choice = Integer.parseInt(stdin.readLine());

			switch(choice)
			{
			case 0 :
				System.out.println(choice);   
				System.out.println("The Wonderful Movie Theater who earned $" + movieTheater.getTotalTicketSales() + " kicks out remaining customers and closes...");
				System.out.println("Good Bye!");   
				running = false;

				break;		
			case 1 :
				System.out.println(choice);

				boolean customerRunning = true;
				boolean childRunning = true;
				boolean sizeRunning = true;
				boolean movieRunning = true;
				boolean watchDumbo = true;

				int partySize = 0;

				String customerName = "";
				Customer customer;

				while(customerRunning) {
					customerRunning = false;
					System.out.print("		>>Enter customer name: ");
					customerName = stdin.readLine();
					System.out.println(customerName);

					if(movieTheater.hasCustomer(customerName)) {
						System.out.println("Customer " + customerName + " is already in the theater.");
						System.out.println("Please specify a different name.");
						customerRunning = true;
					}
				}

				while(sizeRunning) {
					sizeRunning = false;
					System.out.print("		>>Enter party size: ");
					partySize = Integer.parseInt(stdin.readLine());
					System.out.println(partySize);

					if(partySize <= 0) {
						System.out.println("		Invalid input. Must enter a number greater than 0.");
						sizeRunning = true;
					}
				}

				while(movieRunning) {
					movieRunning = false;
					System.out.print("		>>Enter movie name: ");
					String movieName = stdin.readLine();
					System.out.println(movieName);

					if(movieName.equalsIgnoreCase("dumbo")) {
						watchDumbo = true;
					} else if(movieName.equalsIgnoreCase("shazam!")) {
						watchDumbo = false;
					} else {
						System.out.println("		Invalid input. Must enter 'Dumbo' or 'Shazam!'");
						movieRunning = true;
					}
				}

				while(childRunning) {
					childRunning = false;
					System.out.print("		>>Is a child 11 or younger in this party(Y/N)? ");
					String isChild = stdin.readLine();
					System.out.println(isChild);

					if(isChild.equalsIgnoreCase("Y")) {
						customer = new Customer(partySize, customerName, watchDumbo);			
						movieTheater.addToLine(customer, true);
					} else if(isChild.equalsIgnoreCase("N")) {
						customer = new Customer(partySize, customerName, watchDumbo);			
						movieTheater.addToLine(customer, false);
					} else {
						System.out.println("		Invalid input. Must enter 'Y' or 'N'.");
						childRunning = true;
					}
				}

				break;
			case 2 :
				System.out.println(choice);

				boolean lineRunning = true;

				while(lineRunning) {
					lineRunning = false;
					if(firstLine) {

						System.out.print("Which line would you like to serve customers first?(Express/Reg1/Reg2): ");
						String line = stdin.readLine();
						System.out.println(line);

						if(line.equalsIgnoreCase("Express")) {
							movieTheater.serveLine(1);
							firstLine = false;

						} else if(line.equalsIgnoreCase("Reg1")) {
							movieTheater.serveLine(2);
							firstLine = false;

						} else if(line.equalsIgnoreCase("Reg2")) {
							movieTheater.serveLine(3);
							firstLine = false;

						} else {
							lineRunning = true;
							System.out.println("	Invalid input. Must enter 'Express', 'Reg1', or 'Reg2'.");
						}

					} else {
						Customer cust = movieTheater.serveLine(0);
						if(!movieTheater.buyingTickets(cust,0)) {
							boolean buying = true;
							while(buying) {
								buying = false;

								System.out.print("Would you like to see the other movie(Y/N)? ");
								String other = stdin.readLine();
								System.out.println(other);

								if(other.equalsIgnoreCase("Y")) {
									cust.setWatchDumbo(!cust.getWatchDumbo());
									movieTheater.buyingTickets(cust,1);
								} else if(other.equalsIgnoreCase("N")) {
									System.out.println("Customer " + cust.getName() + " left the theater.");
								} else {
									System.out.println("Invalid input. Must enter 'Y' or 'N'.");
									buying = true;
								}
							}
							
						}
					}
				}

				break;
			case 3 :
				System.out.println(choice);
				
				if(movieTheater.Dumbo.isEmpty() && movieTheater.Shazam.isEmpty()) {
					System.out.println("No customers are in the movie theater at this time.");
				} else {
					System.out.print(">>Enter customer name to leave Movie Theater: ");
					String removeName = stdin.readLine();
					System.out.println(removeName);

					movieTheater.customerLeaves(removeName);
				}		

				break;
			case 4 :
				System.out.println(choice);

				movieTheater.displayLines();

				break;
			case 5 :
				System.out.println(choice);
				System.out.println("Here is the seating chart for the Shazam! Movie Theater.");

				movieTheater.Shazam.displaySeats();

				break;
			case 6 :
				System.out.println(choice);
				System.out.println("Here is the seating chart for the Dumbo Movie Theater.");

				movieTheater.Dumbo.displaySeats();

				break;
			case 7 :
				System.out.println(choice);

				movieTheater.ticketSales();

			}
		}
	}

}
