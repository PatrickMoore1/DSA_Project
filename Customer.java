
public class Customer {
	private int size = 0;
	private String name = "";
	boolean watchDumbo;


	public Customer(int size, String name, boolean watchDumbo) {
		super();
		this.size = size;
		this.name = name;
		this.watchDumbo = watchDumbo;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getName() {
		return name;
	}
	
	public boolean getWatchDumbo() {
		return watchDumbo;
	}
}
