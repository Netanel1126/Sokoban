package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;
import common.Hibernate_UsersAndQuery;
import common.Level;

public class MyView extends Observable implements View{

	private BufferedReader reader;
	private PrintWriter writer;
	private String exitString;
	
	public void CLI(BufferedReader reader, PrintWriter writer, String exitString) {
		this.reader = reader;
		this.writer = writer;
		this.exitString = exitString;
	}

	public void displayMessage(String msg) {
		writer.println(msg);	
		writer.flush();
	}


	public void start2() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String commandLine = "";
				do {
					writer.print("Enter command: " );
					writer.flush();
					try {
						commandLine = reader.readLine();
						String[] arr = commandLine.split(" ");
						LinkedList<String> params = new LinkedList<String>();
						for (String param : arr) {
							params.add(param);
						}
						setChanged();
						notifyObservers(params);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				} while (!commandLine.equals(exitString));				
			}
		}).start();	
	}

	@Override
	public void Display(Level my_level) {}

	@Override
	public void Display_addUser(Level my_level,Hibernate_UsersAndQuery user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Display_levelQuery(Hibernate_UsersAndQuery querylisty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Display_UserQuery(Hibernate_UsersAndQuery querylisty) {
		// TODO Auto-generated method stub
		
	}
}