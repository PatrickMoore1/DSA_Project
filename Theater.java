
public class Theater  {

	private MyListReferenceBased<Customer> seats = new MyListReferenceBased<Customer>();
	private int rows = 0;
	private int seatsPerRow = 0;
	private int totalSeats;
	private int filledSeats = 0;
	private int currentSeat = 0;


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
	
	public boolean isEmpty() {
		return filledSeats == 0;
	}
	
	public boolean isMovieFull() {
		return filledSeats == totalSeats;
	}

	public void fillSeat(Customer customer) {
		int numCust = customer.getSize();
		int listSize = seats.size();
		Node<Customer> searchNode = seats.getHead();
		Node<Customer> startNode = null;
		Node<Customer> endNode = null;
		int emptySeats = 0;
		//if((filledSeats + numCust) < totalSeats) {
		if((totalSeats - listSize) > numCust) {
			for(int i = 0; i < numCust; i++) {
				seats.add(currentSeat, customer);
				currentSeat++;
				filledSeats++;
			}
		}
		else if(canFindSeat(numCust)) {

			while(emptySeats < numCust) {
				if(searchNode.getItem() == null) {
					emptySeats++;
					if(emptySeats == 1) {
						startNode = searchNode;
					}
					searchNode = searchNode.getNext();
				}
				else {
					emptySeats = 0;
					searchNode = searchNode.getNext();
				}
			}
			endNode = searchNode;
		}
		if(emptySeats == numCust) {
			while(!(startNode.equals(endNode))) {
				startNode.setItem(customer);
				startNode = startNode.getNext();
			}
		}
	}

	public boolean canFindSeat(int party) {
		boolean findSeats = false;
		int listSize = seats.size();
		Node<Customer> currSeat = seats.getHead();
		if((totalSeats - listSize) > party) {
			findSeats = true;
		}
		else if((party + filledSeats) < totalSeats) {
			int takingSeats = 0;
			while(!findSeats || currSeat.getNext() != null) {
				if(currSeat.getItem() == null) {
					takingSeats++;
					currSeat = currSeat.getNext();
				}
				else {
					takingSeats = 0;
					currSeat = currSeat.getNext();

				}
				if(takingSeats == party) {
					findSeats = true;
				}
			}
		}
		return findSeats;
	}


	public boolean findCustomer(String name) {
		boolean result = false;

		Node<Customer> node = seats.getHead();
		while(node != null && !result) {
			if(name.equalsIgnoreCase(node.getItem().getName())) {
				result = true;
			} else {
				node = node.getNext();
			}
		}

		return result;

	}

	public boolean customerLeave(String custName) {
		boolean customerFound = false;
		int amountRemoved = 0;
		Node<Customer> searchCust = seats.getHead();
		for(int i = 0; i < totalSeats && searchCust.getNext() != null; i++) {
			if(searchCust.getItem() == null) {
				searchCust = searchCust.getNext();
			}
			else if(!searchCust.getItem().getName().equalsIgnoreCase(custName)) {
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

	

	public void displaySeats() {
		Node<Customer> seatCounter = seats.getHead();
		for(int row = 1; row <= rows; row++) {
			for(int seat = 1; seat <= seatsPerRow; seat++) {
				if(seatCounter == null) {
					System.out.println("Row " + row + " seat " + seat + " is free.");
				}
				else if(seatCounter.getItem() != null) {
					String name = seatCounter.getItem().getName();
					System.out.println("Row " + row + " seat " + seat + " is used by " + name +"'s party.");
					seatCounter = seatCounter.getNext();
				}
				else {
					System.out.println("Row " + row + " seat " + seat + " is free.");
					seatCounter = seatCounter.getNext();
				}
			}
		}
	}
}
