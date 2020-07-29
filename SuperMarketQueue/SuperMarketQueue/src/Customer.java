





public class Customer {
	public int ID=1;
	private int arrivalTime;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Customer(int arrivalTime) {
		super();
		this.arrivalTime = arrivalTime;

		this.ID=ID++;
	}
	
}
