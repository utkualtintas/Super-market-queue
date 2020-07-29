import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SimulationFrame extends JFrame {
	
	
	Cashier cashier = new Cashier();
	int timeOfTheDay=0;
	CustomerQueue queue = new CustomerQueue();
	private  JLabel maxSimTimeLabel;
	private  JTextField maxSimTimeTextField;
	private  JLabel maxArrTimeLabel;
	private  JLabel maxServTimeLabel;
	private  JLabel timerSpeedLabel;
	private  JTextField maxArrTimeTextField;
	private  JTextField maxServTimeTextField;
	public  JButton startButton;
	private  JComboBox<Integer> intervalsComboBox;
	int simulationInterval; 
	int maxSimTime;
	int maxArrTime;
	public int getMaxArrTime() {
		return maxArrTime;
	}
	public void setMaxArrTime(int maxArrTime) {
		this.maxArrTime = maxArrTime;
	}
	public int getMaxServTime() {
		return maxServTime;
	}
	public void setMaxServTime(int maxServTime) {
		this.maxServTime = maxServTime;
	}
	public int getTimerSpeed() {
		return timerSpeed;
	}
	public void setTimerSpeed(int timerSpeed) {
		this.timerSpeed = timerSpeed;
	}

	int maxServTime;
	int timerSpeed;
	
	public int getSimulationInterval() {
		return simulationInterval;
	}
	public void setSimulationInterval(int simulationInterval) {
		this.simulationInterval = simulationInterval;
	}
	public int getMaxSimTime() {
		return maxSimTime;
	}
	public void setMaxSimTime(int maxSimTime) {
		this.maxSimTime = maxSimTime;
	}

	public SimulationFrame() throws HeadlessException {
		super("COMP132-HW2");
		setLayout(new GridLayout(5,2));
		maxSimTimeLabel = new JLabel("Max Simulation Time");
		maxSimTimeTextField= new JTextField(20);
		maxArrTimeLabel = new JLabel("Max arrival Time");
		maxServTimeLabel = new JLabel("Max service Time");
		timerSpeedLabel = new JLabel("Timer Speed (milliseconds)");
		maxArrTimeTextField= new JTextField(20);
		maxServTimeTextField= new JTextField(20);
		startButton = new JButton("START SIMULATION");
		Integer[] intervals = {200,400,500,1000};
		intervalsComboBox= new JComboBox<Integer>(intervals);
		add(maxSimTimeLabel);
		add(maxSimTimeTextField);
		add(maxArrTimeLabel);
		add(maxArrTimeTextField);
		add(maxServTimeLabel);
		add(maxServTimeTextField);
		add(timerSpeedLabel);
		add(intervalsComboBox);
		add(startButton);
		comboBoxHandler cBoxHandler = new comboBoxHandler();
		textFieldHandler handler = new textFieldHandler();
		maxSimTimeTextField.addActionListener(handler);
		intervalsComboBox.addItemListener(cBoxHandler);
		maxArrTimeTextField.addActionListener(handler);
		maxServTimeTextField.addActionListener(handler);
		listenerForButton listener = new listenerForButton();
		startButton.addActionListener(listener);
		int nextArrivalTime;
		

	}
	 class comboBoxHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) 
				simulationInterval = (int) intervalsComboBox.getSelectedItem();
			
		}
		 
		
	}
	 class textFieldHandler implements ActionListener {
		 
		  public void actionPerformed(ActionEvent event)
		    {
		         String string = ""; 
		         // user pressed Enter in JTextField textField1
		         if (event.getSource() == maxSimTimeTextField) {
		            string = String.format(event.getActionCommand());
		         maxSimTime=Integer.parseInt(string);
		         }
		         else if (event.getSource() == maxServTimeTextField) {
			            string = String.format(event.getActionCommand());
			         maxServTime=Integer.parseInt(string);
		         }
		         else if (event.getSource() == maxArrTimeTextField) {
			            string = String.format(event.getActionCommand());
			         maxArrTime=Integer.parseInt(string);
		         } 
		      } 
	 }
	 
	 
	 
	 class listenerForButton implements ActionListener {
		 	int currentTime=0;
			int queueLenght=0;
			int totalServiceTime=0;
			int nextArrivalTime=0;
			int serviceEndTime;
			int numberOfCustomers=0;
			int maximumQueueLength=CustomerQueue.returnMaximumQueueLength();
			int longestWait = CustomerQueue.getLongestWaitTime();
		
		public void actionPerformed(ActionEvent e) {
			getContentPane().removeAll();
			getContentPane().repaint();
			JLabel currentSimTimeLabel = new JLabel("Current simulation Time: "+currentTime);
			JLabel currentQueueLenghtLabel = new JLabel("Current queue length is: "+queueLenght);
			JLabel totalServiceTimeLabel = new JLabel("Total service time is: "+totalServiceTime);
			setLayout(new GridLayout(3,1));
			add(currentSimTimeLabel);
			add(currentQueueLenghtLabel);
			add(totalServiceTimeLabel);
			Cashier cashier = new Cashier();
			CustomerQueue queue = new CustomerQueue();
			int roof = maxArrTime;
			Random rand = new Random();
			int randomNumber =1+ rand.nextInt(roof);
			nextArrivalTime=randomNumber;
			currentTime = nextArrivalTime;
			cashier.setReadyToRecieveCustomer(false);
			serviceEndTime =cashier.decideServiceTimeAndRecordIt(maxServTime)+currentTime;
			randomNumber =1+ rand.nextInt(roof);
			nextArrivalTime=randomNumber+currentTime;			

			

			
			
			Timer timer = new Timer(simulationInterval, new ActionListener() {
			
				public void actionPerformed(ActionEvent e) {
			
					

					if(currentTime==nextArrivalTime) {
						cashier.setReadyToRecieveCustomer(false);

						Customer customer = new Customer(currentTime);
						numberOfCustomers++;
						queue.enqueue(customer);
						int roof = maxArrTime;
						Random rand = new Random();
						int randomNumber =1+ rand.nextInt(roof);
						nextArrivalTime=randomNumber+currentTime;
						
					
						

					}
					if(currentTime==serviceEndTime) {
						if(queue.getSize()!=0) {
							Customer c = queue.getQueue().get(0);
							CustomerQueue.calculateTheAverageWaitingTime(serviceEndTime, c);
							CustomerQueue.detectLongestWaitTime(serviceEndTime, c);
							
							queue.dequeue();
						
						} else {
							cashier.setReadyToRecieveCustomer(true);
						}
						int roof = maxServTime;
						int nextEnd=cashier.decideServiceTimeAndRecordIt(roof)+currentTime;
						cashier.calculateAvergaServiceTime();
						 serviceEndTime=nextEnd;
					}
					if(cashier.isReadyToRecieveCustomer()==false) {
						totalServiceTime+=1;
					}

					currentTime=currentTime+1;
					maximumQueueLength=CustomerQueue.returnMaximumQueueLength();
					int lenghtOfQueue=queue.getSize();	
					currentSimTimeLabel.setText("Current simulation Time: "+currentTime);	
					currentQueueLenghtLabel.setText("Current queue length: "+lenghtOfQueue);
					totalServiceTimeLabel.setText("Total service time: "+totalServiceTime);
					
					if(currentTime==maxSimTime) {
						((Timer) e.getSource()).stop();
						String message = String.format("Number of "
								+ "customers: %d\n Average Wait Time: %f\n Average Service Time"
								+ " %f \n Maximum Wa"
								+ "it Time: %d\n Maximum Queue Length: %d", numberOfCustomers,CustomerQueue.averageWaitingTimeOfCustomers,cashier.averageServiceTime,CustomerQueue.longestWaitTime,maximumQueueLength);
						JOptionPane.showMessageDialog(null, message,"Message",JOptionPane.PLAIN_MESSAGE);
					}
				
				}
			
				
			});
			timer.start();
			
}
		
		 
	 }
	 
	 
	 
	 
			}
	


	






