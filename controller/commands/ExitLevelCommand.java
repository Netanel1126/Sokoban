package controller.commands;

import common.Level;
import model.Model;
import view.View;;

//Create the Exit Command and call to execute it
public class ExitLevelCommand extends A_Command implements Commands{
	
	public ExitLevelCommand(Model new_model,View view,Level my_level){
		super(new_model, view, my_level);
	}
	
	@Override
	public void execute() {
		model.exit();
	}
}
