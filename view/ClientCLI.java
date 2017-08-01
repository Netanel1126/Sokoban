package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;

import org.hibernate.query.Query;

import common.Hibernate_UsersAndQuery;
import common.Level;
import common.User;

public class ClientCLI extends Observable implements Clienthandler,View {

	private BufferedReader reader;
	private PrintWriter writer;
	private boolean stop = true;
	
	public void displayMessage(String msg) {
		writer.println(msg);	
		writer.flush();
	}
		
	public void start2() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String commandLine = "";
				LinkedList<String> params = new LinkedList<String>();
				//create a CLI screen in the client computer
				do {
					writer.println("> Load FILENAME");
					writer.println("> Display"); 
					writer.println("> Move"); 
					writer.println("> Save FILENAME");
					writer.println("> Exit");
					writer.flush();
					try {
						commandLine = reader.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//take the user input and put in params
					writer.println(commandLine);
					String[] commands = commandLine.split(" ");
					for (String param : commands) {
						params.add(param);
					}
					
					if(commands[0].equals("Exit"))
						stop = false;
					else
					{
						setChanged();
						notifyObservers(params);
					}
				}while(stop);
				
				setChanged();
				notifyObservers(params);
			}
		}).start();

	}

	// handle the client
	@Override
	public void handleclient(InputStream in, OutputStream out) {
		this.reader = new BufferedReader(new InputStreamReader(in));
		this.writer = new PrintWriter(out);
		start2();
	}
		
	@Override
	public void Display(Level my_level) {
		for (int i = 0; i < my_level.getList_sprite().size() ; i++) {
			for(int j = 0; j<my_level.getList_sprite().get(i).size();j++)
				writer.print(my_level.getList_sprite().get(i).get(j).getSprite());
			writer.println(" ");
			writer.flush();
		}
		
		
		//check to see if the client won
		Win(my_level);
	}
		
	// check if you Win the game
	public void Win(Level my_level){
		int	counter=0;
		for (int i = 0; i < my_level.getList_sprite().size() ; i++) {
			for(int j=0; j<my_level.getList_sprite().get(i).size();j++)
				if(my_level.getList_sprite().get(i).get(j).returnSpriteString() == "o")
					counter++;
		}
		if(counter==0){
			writer.println("WIN");
			stop =false;
		}
	}

	@Override
	public void Display_addUser(Level my_level,Hibernate_UsersAndQuery  user) {
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