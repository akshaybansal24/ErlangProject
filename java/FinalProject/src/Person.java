import java.util.ArrayList;
import java.util.Random;

/*public class Person extends Thread{
	public static Random randomNumber = new Random();
	String personName;
	boolean isWaitStarted = false;
	boolean isNotified = false;
	ArrayList<String> receiverList;
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public ArrayList<String> getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(ArrayList<String> receiverList) {
		this.receiverList = receiverList;
	}
	
	@Override
	public void run() {
		System.out.println("here : " + this.getPersonName());
		for(String receiver : receiverList) {
			try {
				Thread.sleep((long)randomNumber.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.sendMessage(receiver);
		}
		this.isWaitStarted = true;
		synchronized (this) {
			while(true) {
				try {
					this.wait(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(this.isNotified)
					this.isNotified = false;
				else
					break;
			}
		}
		System.out.println("\nProcess " +this.getPersonName()+" has received no calls for 1 second, ending...");
	}
	
    public void sendMessage(String receiver) {
		Person receiverObject = exchange.personMap.get(receiver);
		synchronized (receiverObject) {
			receiverObject.isNotified = true;
			receiverObject.notify();
		}
		receiverObject.receiveMessage(receiver, this.getPersonName(), "intro", Long.toString(System.currentTimeMillis()));
	}
	public void receiveMessage(String receiver,String sender,String type, String timestamp) {
		synchronized (exchange.masterThread) {
			exchange.masterThread.isNotified = true;
			exchange.masterThread.notify();
		}
		exchange.masterThread.printMessage(String.format("%s received %s message from %s [%s]", receiver,type,sender,timestamp));
		if(type.equalsIgnoreCase("intro")) {
			try {
				Thread.sleep((long)randomNumber.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Person receiverObject = exchange.personMap.get(sender);
			synchronized (receiverObject) {
				receiverObject.isNotified = true;
				receiverObject.notify();
			}
			receiverObject.receiveMessage(sender, receiver, "reply", timestamp);
		}
	}
	
}*/
