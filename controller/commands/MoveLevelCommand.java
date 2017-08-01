package controller.commands;

import common.Level;
import model.Model;
//import receivers.MoveFile_Name;
import view.View;

//Create a Move Command and call to execute it
public class MoveLevelCommand extends A_Command implements Commands{

	private String moveTo;
	
	public MoveLevelCommand(Model new_model,View view,Level my_level){
		super(new_model, view, my_level);
	}
	
	@Override
	public void execute(){
		moveTo = params.get(0);
		model.move2(my_level,moveTo);
	}
}