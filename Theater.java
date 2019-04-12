
public class Theater  {
	
	private int rows = 0;
	private int seatsPerRow = 0;
	private MyListReferenceBased<Customer> seats = new MyListReferenceBased<Customer>(); 
	
	
	public Theater(int rows, int seatsPerRow) {  //User will be prompted how many rows and how many seats per row
		super();
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeats(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}

	public MyListReferenceBased<Customer> seats() {
		return seats;
	}
	
	
	
	
	

}
