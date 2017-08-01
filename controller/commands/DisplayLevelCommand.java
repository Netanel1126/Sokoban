package controller.commands;

import common.Level;
import model.Model;
import view.View;
// Create a Display Command and call to execute it
public class DisplayLevelCommand extends A_Command implements Commands {

		public DisplayLevelCommand(Model new_model,View view,Level my_level ) {
			super(new_model,view,my_level);
		}
		
	@Override
	public void execute() {
		view.Display(my_level);
	}
}
