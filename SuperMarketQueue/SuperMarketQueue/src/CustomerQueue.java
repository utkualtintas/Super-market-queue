import java.util.Iterator;


import java.util.LinkedList;
import java.util.List;


public class CustomerQueue {
	public static LinkedList<Customer>queue= new LinkedList<Customer>(); // This is the main queue 
	static LinkedList<Integer>waitTimes= new LinkedList<Integer>();		// This is the linkedlist where I store wait times of the customers that are serviced which I will use to calculate the average wait time
	static LinkedList<Integer>waitTimesForLongestWaitTime= new LinkedList<Integer>(); // This is the linkedlist where I store wait times of the customers that are serviced which I will use to calculate the longest wait time
	public static int maximumQueueLenght=0;
	public static int longestWaitTime =0;
	public static double averageWaitingTimeOfCustomers;
	
	
	
	
	public int getSize() {
		
		return queue.size();
	}
	
	
	public void enqueue(Customer newCustomer) {
		queue.add(newCustomer);
	}
	public static void dequeue() {
		queue.remove(0);
	}
	
	public static int returnMaximumQueueLength( ) {
		
		if(queue.size()>maximumQueueLenght) {
			maximumQueueLenght=queue.size();
		}
		return maximumQueueLenght;
		
	}
	public static LinkedList<Customer> getQueue() {
		return queue;
	}

	public static void setQueue(LinkedList<Customer> queue) {
		CustomerQueue.queue = queue;
	}

	
	public LinkedList<Integer> getWaitTimes() {
		return waitTimes;
	}

	public void setWaitTimes(LinkedList<Integer> waitTimes) {
		this.waitTimes = waitTimes;
	}

	public static int getMaximumQueueLenght() {
		return maximumQueueLenght;
	}

	public static void setMaximumQueueLenght(int maximumQueueLenght) {
		CustomerQueue.maximumQueueLenght = maximumQueueLenght;
	}

	public static int getLongestWaitTime() {
		return longestWaitTime;
	}

	public static void setLongestWaitTime(int longestWaitTime) {
		CustomerQueue.longestWaitTime = longestWaitTime;
	}

	public static double getAverageWaitingTimeOfCustomers() {
		return averageWaitingTimeOfCustomers;
	}

	public static void setAverageWaitingTimeOfCustomers(double averageWaitingTimeOfCustomers) {
		CustomerQueue.averageWaitingTimeOfCustomers = averageWaitingTimeOfCustomers;
	}
	
	public static void detectLongestWaitTime(int endTime,Customer c) {
		int waitTime = endTime-c.getArrivalTime();
		waitTimesForLongestWaitTime.add(waitTime);
		for(Integer x: waitTimesForLongestWaitTime) {
			if(x>longestWaitTime) {
				longestWaitTime=x;
			}
		}
		
	}
	public static void calculateTheAverageWaitingTime(int endTime,Customer c) {
		int waitTime = endTime-c.getArrivalTime();
		waitTimes.add(waitTime);
		double result;
		double total=0;
		for(Integer x: waitTimes) {
			total=total+x;
		}
		result=total/(double) waitTimes.size();
		averageWaitingTimeOfCustomers= result;
}
}
