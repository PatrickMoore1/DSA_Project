import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * MovieTheater deals with the customers getting into the three different lines, serving the 
 * customers, and keeping track of ticket sales.
 * 
 * @author Patrick Moore
 *
 */
public class MovieTheater {
	static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));

	double ticketPrice = 0.0;
	Theater Dumbo;
	Theater Shazam;

	MyListReferenceBased<Customer> RegLine1;
	MyListReferenceBased<Customer> RegLine2;
	MyListReferenceBased<Customer> Express;

	double totalTicketSales = 0.0;
	int DumboTickets = 0;
	int ShazamTickets = 0;

	int currentLine = 1;

	/**
	 * Sets the theaters dumbo and shazam as well as the ticket price
	 * 
	 * @param ticketPrice		User sets the ticket price
	 * @param dumbo				The dumbo movie seats
	 * @param shazam			The shazam movie seats
	 */
	public MovieTheater(double ticketPrice, Theater dumbo, Theater shazam) {
		super();
		this.ticketPrice = ticketPrice;
		Dumbo = dumbo;
		Shazam = shazam;
		RegLine1 = new MyListReferenceBased<Customer>();
		RegLine2 = new MyListReferenceBased<Customer>();
		Express = new MyListReferenceBased<Customer>();

	}

	/**
	 * Gives back the ticket price
	 * 
	 * @return	double ticketPrice
	 */
	public double getTicketPrice() {
		return ticketPrice;
	}
	
	/**
	 * Finds a customer in the lines by name
	 * 
	 * @param name 		The name of the customer
	 * @return boolean
	 */
	public boolean hasCustomer(String name) {
		boolean custFound = false;
		if(Dumbo.findCustomer(name) || Shazam.findCustomer(name) || 
				Express.findLineCustomer(name) || RegLine1.findLineCustomer(name) || 
				RegLine2.findLineCustomer(name)) {
			custFound = true;
		} 

		return custFound;
	}
	
	/**
	 * Gives back all total sales
	 * 
	 * @return double 
	 */
	public double getTotalTicketSales() {
		return totalTicketSales;
	}

	/**
	 * Determines which line the customer goes to
	 * 
	 * @param customer		The customer and their information
	 * @param hasChild		Checks if group has a child
	 */
	public void addToLine(Customer customer, boolean hasChild) {

		//INCOMPLETE
		//ADD METHOD MUST WORK CORRECTLY

		if(hasChild) {
			if(RegLine1.size() < Express.size() || RegLine2.size() < Express.size()) {
				if(RegLine2.size() < RegLine1.size()) {
					RegLine2.add(customer);
					System.out.println("Customer " + customer.getName() + " is in the second ticket line.");
				} else {
					RegLine1.add(customer);
					System.out.println("Customer " + customer.getName() + " is in the first ticket line.");
				}
			} else {
				Express.add(customer);
				System.out.println("Customer " + customer.getName() + " is in the express ticket line.");
			}

		} else {
			if(RegLine2.size() < RegLine1.size()) {
				RegLine2.add(customer);
				System.out.println("Customer " + customer.getName() + " is in the second ticket line.");
			} else {
				RegLine1.add(customer);
				System.out.println("Customer " + customer.getName() + " is in the first ticket line.");
			}
		}
	}

	/**
	 * Serves a customer at the front of the line
	 * 
	 * @param firstLine		Which line is being served
	 * @return Customer
	 */
	public Customer serveLine(int firstLine) throws IOException {

		if(firstLine != 0) {
			currentLine = firstLine;
		}
		Customer serving = null;

		boolean notServed = true;

		while(notServed) {
			if(Express.isEmpty() && RegLine1.isEmpty() && RegLine2.isEmpty()) {
				System.out.println("There are no customers waiting in any line.");
				notServed = false;
			} else {
				if(currentLine == 1) {
					if(Express.isEmpty()) {
						currentLine++;
					} else {
						serving = Express.get(0);
						System.out.println("Serving customer " + serving.getName());
						//buyingTickets(serving, 0);
						Express.remove(0);
						currentLine++;
						notServed = false;
					}
				} else if(currentLine == 2) {
					if(RegLine1.isEmpty()) {
						currentLine++;
					} else {
						serving = RegLine1.get(0);
						System.out.println("Serving customer " + serving.getName());
						//buyingTickets(serving, 0);
						RegLine1.remove(0);
						currentLine++;
						notServed = false;
					}
				} else {
					if(RegLine2.isEmpty()) {
						currentLine = 1;
					} else {
						serving = RegLine2.get(0);
						System.out.println("Serving customer " + serving.getName());
						//buyingTickets(serving, 0);
						RegLine2.remove(0);
						currentLine = 1;
						notServed = false;
					}
				}
			}
		}
		return serving;
	}

	/**
	 * Customer buys a ticket
	 * 
	 * @param cust			The customer and their information
	 * @param rejected		number of times customer has been rejected from seeing movie 
	 * @return boolean
	 */
	public boolean buyingTickets(Customer cust, int rejected) throws IOException {

		boolean bought = false;
		boolean isDumbo;
		Theater movie;

		if(cust.getWatchDumbo()) {
			movie = Dumbo;
			isDumbo = true;
		} else {
			movie = Shazam;
			isDumbo = false;
		}
		if(movie.canFindSeat(cust.getSize())) { 
			movie.fillSeat(cust);
			bought = true;
			totalTicketSales += ticketPrice * cust.getSize();
			if(isDumbo) {
				Dumbo = movie;
				DumboTickets += cust.getSize();
				System.out.println(cust.getName() + ", party of " + cust.getSize() + " has been seated in the Dumbo Movie Theater.");
			} else {
				Shazam = movie;
				ShazamTickets += cust.getSize();
				System.out.println(cust.getName() + ", party of " + cust.getSize() + " has been seated in the Shazam! Movie Theater.");
			}
		} else {
			System.out.println("Sorry. This movie is sold out.");
			if(rejected == 1) {
				System.out.println("Customer " + cust.getName() + " left the theater.");
			}
		}

		return bought;
	} 	

	/**
	 * Customer leaves the movie theater
	 * 
	 * @param removeName		The customer that is leaving
	 */
	public void customerLeaves(String removeName) {

		//CHANGED TO BOOLEAN IN GITHUB
		if(Dumbo.customerLeave(removeName) || Shazam.customerLeave(removeName)) {
			System.out.println("Customer " + removeName + " has left the Movie Theater");
		} else {
			System.out.println("This customer is not in Movie Theater!");
		}


	}


	/**
	 * Displays the customers in the lines
	 */
	public void displayLines() {
		if(RegLine1.isEmpty()) {
			System.out.println("No customers in the first line!");
		} else {
			if(RegLine1.size() == 1) {
				System.out.println("The following customer is in the first line: ");
				Customer cust = RegLine1.get(0);
				String mov = "Shazam!";
				if(cust.getWatchDumbo()) {
					mov = "Dumbo";
				}
				System.out.println("	Customer " + cust.getName() + " party of " + cust.getSize() + " for " +  mov + " movie.");
			} else {
				System.out.println("The following " + RegLine1.size() + " customers are in the first line: ");
				for(int i = 0; i < RegLine1.size(); i++) {
					Customer cust = RegLine1.get(i);
					String mov = "Shazam!";
					if(cust.getWatchDumbo()) {
						mov = "Dumbo";
					}
					System.out.println("	Customer " + cust.getName() + " party of " + cust.getSize() + " for " +  mov + " movie.");

				}

			}
		}

		if(RegLine2.isEmpty()) {
			System.out.println("No customers in the second line!");
		} else {
			if(RegLine2.size() == 1) {
				System.out.println("The following customer is in the second line: ");
				Customer cust = RegLine2.get(0);
				String mov = "Shazam!";
				if(cust.getWatchDumbo()) {
					mov = "Dumbo";
				}
				System.out.println("	Customer " + cust.getName() + " party of " + cust.getSize() + " for " +  mov + " movie.");
			} else {
				System.out.println("The following " + RegLine2.size() + " customers are in the second line: ");
				for(int i = 0; i < RegLine2.size(); i++) {
					Customer cust = RegLine2.get(i);
					String mov = "Shazam!";
					if(cust.getWatchDumbo()) {
						mov = "Dumbo";
					}
					System.out.println("	Customer " + cust.getName() + " party of " + cust.getSize() + " for " +  mov + " movie.");
				}
			}
		}

		if(Express.isEmpty()) {
			System.out.println("No customers in the express line!");
		} else {
			if(Express.size() == 1) {
				System.out.println("The following customer is in the express line: ");
				Customer cust = Express.get(0);
				String mov = "Shazam!";
				if(cust.getWatchDumbo()) {
					mov = "Dumbo";
				}
				System.out.println("	Customer " + cust.getName() + " party of " + cust.getSize() + " for " +  mov + " movie.");
			} else {
				System.out.println("The following " + Express.size() + " customers are in the express line: ");
				for(int i = 0; i < Express.size(); i++) {
					Customer cust = Express.get(i);
					String mov = "Shazam!";
					if(cust.getWatchDumbo()) {
						mov = "Dumbo";
					}
					System.out.println("	Customer " + cust.getName() + " party of " + cust.getSize() + " for " +  mov + " movie.");

				}

			}
		}

	}

	/**
	 * Shows all ticket sales and earnings from all the movies
	 */
	public void ticketSales() {
		System.out.println("	" + ShazamTickets + " tickets have been sold for the Shazam! Movie.");
		System.out.println("	" + DumboTickets + " tickets have been sold for the Dumbo Movie.");
		System.out.println("	" + "Total earnings: $" + totalTicketSales);	
	}





}
