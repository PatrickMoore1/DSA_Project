
public class Theater  {

	private MyListReferenceBased<Customer> seats = new MyListReferenceBased<Customer>();
	private int rows = 0;
	private int seatsPerRow = 0;
	private int totalSeats;
	private int filledSeats = 0;
	private int currentSeat = 0;
	 private int listSize = seats.size();


	public Theater(int rows, int seatsPerRow) {  //User will be prompted how many rows and how many seats per row
		super();
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
		totalSeats = rows*seatsPerRow;
	}

	public int getRows() {
		return rows;
	}  

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void fillSeat(Customer customer) {
		int numCust = customer.getSize();
		//if((filledSeats + numCust) < totalSeats) {
		if((totalSeats - listSize) > numCust) {
			for(int i = 0; i < numCust; i++) {
				seats.add(currentSeat, customer);
				currentSeat++;
				filledSeats++;
			}
		}
		else {
			System.out.println("No more seats");
		}
	}
	
	public boolean canFindSeat(int party) {
		boolean findSeats = false;
		Node<Customer> currSeat = seats.getHead();
		if((totalSeats - listSize) > party) {
			findSeats = true;
		}
		else if(party < (totalSeats - filledSeats)) {
			int takingSeats = 0;
			while(!findSeats || currSeat.getNext() != null) {
				if(currSeat.getItem() == null) {
					takingSeats++;
					currSeat = currSeat.getNext();
				}
				else {
					takingSeats = 0;
				}
				if(takingSeats == party) {
					findSeats = true;
				}
			}
		}
		return findSeats;
	}

	public boolean customerLeave(String custName) {
		boolean customerFound = false;
		int amountRemoved = 0;
		Node<Customer> searchCust = seats.getHead();
		for(int i = 0; i < totalSeats; i++) {
			if(!searchCust.getItem().getName().equalsIgnoreCase(custName)) {
				searchCust = searchCust.getNext();
			}
			else {
				 amountRemoved = searchCust.getItem().getSize();
				searchCust.setItem(null);
				customerFound = true;
			}
		}
		filledSeats -= amountRemoved;
		return customerFound;
	}
	
	public boolean isMovieFull() {
		return filledSeats == totalSeats;
	}
	
	public void displaySeats() {
		Node<Customer> seatCounter = seats.getHead();
		for(int row = 1; row <= rows; row++) {
			for(int seat = 1; seat <= seatsPerRow; seat++) {
				if(seatCounter.getNext() == null) {
					System.out.println("Row " + row + " seat " + seat + "is free.");
				}
				if(seatCounter.getItem() != null) {
					String name = seatCounter.getItem().getName();
					System.out.println("Row " + row + " seat " + seat + "is used by " + name +"'s party.");
					seatCounter = seatCounter.getNext();
				}
				else if(seatCounter.getItem() != null){
					System.out.println("Row " + row + " seat " + seat + "is free.");
					seatCounter = seatCounter.getNext();
				}
			}
		}
	}
}
