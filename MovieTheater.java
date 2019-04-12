
public class MovieTheater {
	double ticketPrice = 0.0;
	Theater Dumbo;
	Theater Shazam;

	Queue<Customer> RegLine1;
	Queue<Customer> RegLine2;
	Queue<Customer> Express;


	public MovieTheater(double ticketPrice, Theater dumbo, Theater shazam) {
		super();
		this.ticketPrice = ticketPrice;
		Dumbo = dumbo;
		Shazam = shazam;
	}


	public double getTicketPrice() {
		return ticketPrice;
	}


	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
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

		if(hasChild) {
			if(RegLine1.getNumItems() < Express.getNumItems()) {
				if(RegLine2.getNumItems() < RegLine1.getNumItems()) {
					RegLine2.enqueue(customer);
					System.out.println("Customer " + customer.getName() + " is in the second ticket line.");
				} else {
					RegLine1.enqueue(customer);
					System.out.println("Customer " + customer.getName() + " is in the first ticket line.");
				}
			} else {
				Express.enqueue(customer);
				System.out.println("Customer " + customer.getName() + " is in the express ticket line.");
			}

		} else {
			if(RegLine2.getNumItems() < RegLine1.getNumItems()) {
				RegLine2.enqueue(customer);
				System.out.println("Customer " + customer.getName() + " is in the second ticket line.");
			} else {
				RegLine1.enqueue(customer);
				System.out.println("Customer " + customer.getName() + " is in the first ticket line.");
			}
		}
	}





}
