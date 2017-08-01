package controller.commands;

import common.Level;
import model.Model;
import view.View;
//import receivers.LoadFile_Name;
//Create a Load Command and call to execute it
public class LoadLevelCommand extends A_Command implements Commands{
	
	private String file_name;
	
	public LoadLevelCommand(Model new_model,View view,Level my_level){
		super(new_model, view, my_level);
	}
	
	@Override
	public void execute() {
		 file_name = params.get(0);
		 model.load(my_level,file_name);
	}
}