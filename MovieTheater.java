
public class MovieTheater {
	double ticketPrice = 0.0;
	Theater Dumbo;
	Theater Shazam;
	
	
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
			System.out.println("Added with Express");
		} else {
			System.out.println("Added with in RegLine");
		}
	}


	
	

}
