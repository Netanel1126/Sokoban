package controller.commands;

import common.Hibernate_UsersAndQuery;
import common.Level;
import model.Model;
import view.View;

public class LevelQueryCommand extends A_Command implements Commands {

	private Hibernate_UsersAndQuery querylist;
	private String prefix;
	
	public LevelQueryCommand(Model model, View view, Level my_level,Hibernate_UsersAndQuery querylist) {
		super(model, view, my_level);
		this.querylist = querylist;
	}
	
	@Override
	public void execute() {
		prefix = params.get(0);
		model.levelQuery(querylist, prefix);
		view.Display_levelQuery(querylist);
	}

}
