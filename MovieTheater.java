
public class MovieTheater {

	public MovieTheater(double ticketPrice, Theater dumbo, Theater shazam) {

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

