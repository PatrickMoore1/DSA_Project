/**
 * The Theater Class deals with seating the customers and checking if they can be seated
 * as well as the number of seats
 * 
 * @author Kevin Nguyen
 *
 */
public class Theater  {

	private MyListReferenceBased<Customer> seats = new MyListReferenceBased<Customer>();
	private int rows = 0;
	private int seatsPerRow = 0;
	private int totalSeats;
	private int filledSeats = 0;
	private int currentSeat = 0;

	/**
	 * User sets the number of rows and number of seats per row
	 * 
	 * @param rows			The number of rows
	 * @param seatsPerRow	The number of seats per row
	 */
	public Theater(int rows, int seatsPerRow) {
		super();
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
		totalSeats = rows*seatsPerRow;
	}

	/**
	 * Gives back the number of rows
	 * 
	 * @return int
	 */
	public int getRows() {
		return rows;
	}  

	/**
	 * Gives back the number of seats per row
	 * 
	 * @return int
	 */
	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	/**
	 * Checks if movie is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return filledSeats == 0;
	}

	/**
	 * Checks if movie is full
	 * @return boolean
	 */
	public boolean isMovieFull() {
		return filledSeats == totalSeats;
	}

	/**
	 * Checks if there are seats able to be filled, if not it doesn't, else it adds them
	 * @param customer		The customer and their information
	 */
	public void fillSeat(Customer customer) {
		int numCust = customer.getSize();
		int listSize = seats.size();
		Node<Customer> searchNode = seats.getHead();
		Node<Customer> startNode = null;
		Node<Customer> endNode = null;
		int emptySeats = 0;
		//if((filledSeats + numCust) < totalSeats) {
		if((totalSeats - listSize) >= numCust) {
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

	/**
	 * Checks if whether the customer (and their group) can be given seats
	 * @param party		The number of people in the customer's group
	 * 
	 * @return boolean
	 */
	public boolean canFindSeat(int party) {
		boolean findSeats = false;
		boolean searching = true;
		int listSize = seats.size();
		Node<Customer> currSeat = seats.getHead();
		if((totalSeats - listSize) >= party) {
			findSeats = true;
		}
		else if((party + filledSeats) <= totalSeats) {
			int takingSeats = 0;
			while(searching && !findSeats || currSeat.getNext() != null) {
				if(currSeat.getItem() == null) {
					takingSeats++;
					currSeat = currSeat.getNext();
				}
				else {
					takingSeats = 0;
					if(currSeat.getNext() != null) {
						currSeat = currSeat.getNext();
					} else {
						searching = false;
					}

				}
				if(takingSeats == party) {
					findSeats = true;
				}
			}
		}
		return findSeats;
	}

	/**
	 * Finds the customer by name
	 * @param name		The name of the customer
	 * 
	 * @return boolean
	 */
	public boolean findCustomer(String name) {
		boolean result = false;
		boolean searching = true;

		Node<Customer> node = seats.getHead();
		while(node != null && !result && searching) {
			if(node.getItem() != null) {
				if(node.getItem().getName().equalsIgnoreCase(name)) {
					result = true;
				} else {
					if(node.getNext() != null) {
						node = node.getNext();
					} else {
						searching = false;
					}
				}
			} else {
				if(node.getNext() != null) {
					node = node.getNext();
				} else {
					searching = false;
				}
			}
		}
		return result;

	}



	/**
	 * The customer leaves the movie and their seat becomes empty
	 * 
	 * @param custName		The customer name
	 * @return boolean
	 */
	public boolean customerLeave(String custName) {
		boolean customerFound = false;
		int amountRemoved = 0;
		Node<Customer> searchCust = seats.getHead();
		for(int i = 0; i <= totalSeats && searchCust != null; i++) {
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


	/**
	 * Displays if the seats are empty or not
	 */
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
