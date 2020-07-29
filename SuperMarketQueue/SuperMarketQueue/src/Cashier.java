import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Cashier {
	private static boolean readyToRecieveCustomer;
	List<Integer> recordOfServiceTimes= new ArrayList<Integer>();
	Iterator iterator = recordOfServiceTimes.iterator();
	public static double averageServiceTime;
	 
	

	
	public static double getAverageServiceTime() {
		return averageServiceTime;
	}

	public static void setAverageServiceTime(double averageServiceTime) {
		Cashier.averageServiceTime = averageServiceTime;
	}

	public boolean isReadyToRecieveCustomer() {
		return readyToRecieveCustomer;
	}

	public void setReadyToRecieveCustomer(boolean readyToRecieveCustomer) {
		this.readyToRecieveCustomer = readyToRecieveCustomer;
	}
	public int decideServiceTimeAndRecordIt(int roof) {
		Random rand = new Random();
		int result;
		result=1+rand.nextInt(roof);
		recordOfServiceTimes.add(result);
		return result;
	}
	
	public void calculateAvergaServiceTime(){
		double total =0;
		for (Integer x:recordOfServiceTimes ) {
			total = total+(int) x;
		}
		double average = total/recordOfServiceTimes.size();
		averageServiceTime=average;
	}
	
	

	

	

}
