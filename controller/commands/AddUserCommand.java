package controller.commands;

import common.Hibernate_UsersAndQuery;
import common.Level;
import common.User;
import model.Model;
import view.View;

public class AddUserCommand extends A_Command implements Commands {

	private Hibernate_UsersAndQuery user;

	public AddUserCommand(Model model, View view, Level my_level, Hibernate_UsersAndQuery user) {
		super(model, view, my_level);
		this.user = user;
	}

	@Override
	public void execute() {
		view.Display_addUser(my_level, user);
		if (!user.getUser_list().isEmpty()) {
			if (user.getUser_list().getLast().getUser_name() != null)
				model.addUser(user);
		}
	}
}
