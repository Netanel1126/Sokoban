package controller.commands;

import model.Model;
import view.View;
import common.Hibernate_UsersAndQuery;
import common.Level;

public class UserQueryCommand extends A_Command implements Commands {
	
	private Hibernate_UsersAndQuery querylist;
	private String prefix;
	
	public UserQueryCommand(Model model, View view, Level my_level,Hibernate_UsersAndQuery querylist) {
		super(model, view, my_level);
		this.querylist = querylist;
	}
	
	@Override
	public void execute() {
		prefix = params.get(0);
		model.UserQuery(querylist, prefix);
		view.Display_UserQuery(querylist);
	}
}
