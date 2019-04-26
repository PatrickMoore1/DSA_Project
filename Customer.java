
public class Customer {
	private int size = 0;
	private String name = "";
	boolean watchDumbo;
	boolean watchShazam;
	
	
	public Customer(int size, String name, boolean watchDumbo, boolean watchShazam) {
		super();
		this.size = size;
		this.name = name;
		this.watchDumbo = watchDumbo;
		this.watchShazam = watchShazam;
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


	public void setName(String name) {
		this.name = name;
	}


	public boolean isWatchDumbo() {
		return watchDumbo;
	}
	
	public boolean isWatchShazam() {
		return watchShazam;
	}


	public void setWatchDumbo(boolean watchDumbo) {
		this.watchDumbo = watchDumbo;
	}


	public void setWatchShazam(boolean watchShazam) {
		this.watchShazam = watchShazam;
	}
	
	
}
