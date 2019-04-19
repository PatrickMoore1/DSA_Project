
public class MovieTheater {
	double ticketPrice = 0.0;
	Theater Dumbo;
	Theater Shazam;

	MyListReferenceBased<Customer> RegLine1;
	MyListReferenceBased<Customer> RegLine2;
	MyListReferenceBased<Customer> Express;

	double totalTicketSales = 0.0;
	int ticketsSold = 0;

	int currentLine = 1;


	public MovieTheater(double ticketPrice, Theater dumbo, Theater shazam) {
		super();
		this.ticketPrice = ticketPrice;
		Dumbo = dumbo;
		Shazam = shazam;
	}


	public double getTicketPrice() {
		return ticketPrice;
	}

	public Theater getDumbo() {
		return Dumbo;
	}


	public Theater getShazam() {
		return Shazam;
	}

	public boolean hasCustomer(String customerName) {
		if(customerName.equals("yes")) {
			return true;
		} else {
			return false;
		}
	}

	public void addToLine(Customer customer, boolean hasChild) {

		//INCOMPLETE
		//ADD METHOD MUST WORK CORRECTLY

		if(hasChild) {
			if(RegLine1.size() < Express.size()) {
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


	public void serveLine(int firstLine) {

		if(firstLine != 0) {
			currentLine = firstLine;
		}

		boolean notServed = true;

		while(notServed) {
			if(Express.isEmpty() && RegLine1.isEmpty() && RegLine2.isEmpty()) {
				System.out.println("There are no customers waiting in any line.");
				notServed = false;
			} else {
				if(currentLine == 1) {
					if(Express.isEmpty()) {
						System.out.println("There are no customers waiting in the Express line.");
						System.out.println("Switching to serve Regular Line 1");
						currentLine++;
					} else {
						Customer serving = Express.get(0);
						System.out.println("Serving customer " + serving.getName());
						if(serving.getWatchDumbo()) {
							//CHECK DUMBO THEATER
						} else {
							//CHECK SHAZAM
						}
						currentLine++;
						notServed = false;
					}
				} else if(currentLine == 2) {
					if(RegLine1.isEmpty()) {
						System.out.println("There are no customers waiting in Reg Line 1.");
						System.out.println("Switching to serve Regular Line 2");
						currentLine++;
					} else {
						Customer serving = RegLine1.get(0);
						System.out.println("Serving customer " + serving.getName());
						if(serving.getWatchDumbo()) {
							//CHECK DUMBO THEATER
						} else {
							//CHECK SHAZAM
						}
						System.out.println("SERVED REG1");
						currentLine++;
						notServed = false;
					}
				} else {
					if(RegLine2.isEmpty()) {
						System.out.println("There are no customers waiting in Reg Line 2.");
						System.out.println("Switching to serve the Express line.");
						currentLine = 1;
					} else {
						Customer serving = RegLine2.get(0);
						System.out.println("Serving customer " + serving.getName());
						if(serving.getWatchDumbo()) {
							//CHECK DUMBO THEATER
						} else {
							//CHECK SHAZAM
						}
						System.out.println("SERVED REG2");
						currentLine = 1;
						notServed = false;
					}
				}
			}
		}


	}


	public void customerLeaves(String removeName) {
		//CHECKS LINES AND THEATER FOR NAME
		//IF NAME NOWHERE, NAME CANT BE FOUND
		//IF FOUND REMOVE FROM THEATER
		//IF IN LINE, EVERYONE MOVES UP AS NORMAL
		//IF IN THEATER, SEATS THEY OWN BECOME NULL

	}


	public void displayLines() {


	}





}
