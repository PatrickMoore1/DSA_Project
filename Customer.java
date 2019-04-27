
/**
 * This is the customer which will handle the group name, the size of the group and what movie 
 * they are watching
 * 
 * 
 * @author Patrick Moore & Kevin Nguyen
 *
 */
public class Customer {
	private int size = 0;
	private String name = "";
	boolean watchDumbo;

	/**
	 * Takes in the customer information
	 * 
	 * @param size			The size of the party
	 * @param name			The name of the group
	 * @param watchDumbo	If they are watching dumbo or not
	 */
	public Customer(int size, String name, boolean watchDumbo) {
		super();
		this.size = size;
		this.name = name;
		this.watchDumbo = watchDumbo;
	}

	/**
	 * Gives back the size of the group
	 * 
	 * @return	int
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gives back the name of the group
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Checks if group is watching dumbo
	 * @return	watchDumbo
	 */
	public boolean getWatchDumbo() {
		return watchDumbo;
	}
	
	/**
	 * Sets the group to not watch dumbo
	 * 
	 * @param watchDumbo
	 */
	public void setWatchDumbo(boolean watchDumbo) {
		this.watchDumbo = watchDumbo;
	}
}
