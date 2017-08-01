package controller.commands;

import common.Level;
import model.Model;
import view.View;

//Create a Save Command and call to execute it
public class SaveLevelCommand extends A_Command implements Commands{
	
	private String name;

	public SaveLevelCommand(Model new_model,View view,Level my_level){
		super(new_model, view, my_level);
	}
	
	@Override
	public void execute() {
		name = params.get(0);
		model.save(my_level,name);
	}	
}