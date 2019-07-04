import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class exchange extends Thread{

	public static HashMap<String, Person> personMap = new HashMap<String,Person>();
	public static exchange masterThread;
	@Override
	public void run() {
		for(Map.Entry<String, Person> childThread : personMap.entrySet()) {
			childThread.getValue().start();
		}
		try {
			Thread.sleep(1500L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			if(Thread.activeCount()<=2)
				break;
			//System.out.println(Thread.activeCount());
		}
		System.out.println("\nMaster Thread Finished");
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner readFile = new Scanner(new File("calls.txt"));
		StringBuffer input = new StringBuffer();
		while(readFile.hasNext())
			input.append(readFile.nextLine());
		String inputData = input.toString().replace("{", "");
		inputData = inputData.replace("}", "");
		String[] inputDataSplit = inputData.split("\\.");
		System.out.println("**Calls to be made**");
		for(String record : inputDataSplit) {
			record = record.replace("[", "");
			record = record.replace("]", "");
			String[] recordSplit = record.split(",");
			String personName = new String(recordSplit[0].trim());
			ArrayList<String> receiverList = new ArrayList<String>();
			for(int i=1;i<recordSplit.length;++i) {
				receiverList.add(recordSplit[i].trim());
			}
			System.out.println(personName + " : " + receiverList);
			Person person = new Person();
			person.setPersonName(personName);
			person.setReceiverList(receiverList);
			personMap.put(personName, person);
		}
		System.out.println();
		masterThread = new exchange();
		masterThread.start();
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
}