                                                      package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import common.Hibernate_UsersAndQuery;
import common.Level;
import common.User;
import controller.commands.A_Command;
import controller.commands.AddUserCommand;
import controller.commands.DisplayLevelCommand;
import controller.commands.LevelQueryCommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveLevelCommand;
import controller.commands.SaveLevelCommand;
import controller.commands.UserQueryCommand;
import controller.server.MyServer;
import model.Model;
import view.ClientCLI;
import view.View;
import org.hibernate.query.Query;

public class SokobanController implements Observer{
	private Model model;
	private View view;
	private MyController controller;
	private Map<String, A_Command> commands;
	private Level my_level;
	private MyServer my_server;
	private Hibernate_UsersAndQuery user_or_query;
	
	public SokobanController(Model model, View view) {
		this.model = model;
		this.view = view;
		my_level = new Level();
		controller = new MyController();
		controller.start();
		user_or_query = new Hibernate_UsersAndQuery();
		initCommands();
	}

	// hash map for the commands
	private void initCommands() {
		commands = new HashMap<String, A_Command>();
		commands.put("Move", new MoveLevelCommand(model,view, my_level));
		commands.put("Display", new DisplayLevelCommand(model,view, my_level));
		commands.put("Save",new SaveLevelCommand(model,view, my_level));
		commands.put("Load", new LoadLevelCommand(model,view, my_level));
		commands.put("AddUser", new AddUserCommand(model, view, my_level, user_or_query));
		commands.put("levelQuery", new LevelQueryCommand(model, view, my_level, user_or_query));
		commands.put("UserQuery", new UserQueryCommand(model, view, my_level, user_or_query));
	}

	@Override
	public void update(Observable o, Object arg) {	
		@SuppressWarnings("unchecked")
		LinkedList<String> params = (LinkedList<String>) arg;
		String commandKey = params.removeFirst();
		if(commandKey.equals("Exit")){
			controller.stop();
			//try to stop the server
			try{
				my_server.stopServer();
			}catch (Exception e) {}
		}
		
		else if (commandKey.equals("AddUser")) {
			A_Command c = commands.get(commandKey);
			controller.insertCommand(c);
		}
		
		else{
			A_Command c = commands.get(commandKey);
			if (c == null) {
				view.displayMessage("Command not found");
				return;
			}
			c.setParams(params);
			controller.insertCommand(c);
		}
	}
	
	public void runServer(int port){
		//start the server
	    my_server = new MyServer();
		try {
			my_server.startMy_server((ClientCLI)view,port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}