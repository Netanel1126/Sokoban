package controller.commands;

import java.util.List;

import common.Level;
import model.Model;
import view.View;

public abstract class A_Command implements Commands{
	protected List<String> params;
	protected Model model;
	protected View view;
	protected Level my_level;

	public A_Command(Model model,View view,Level my_level) {
		this.model = model;
		this.view = view;
		this.my_level = my_level;
	}
	
	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}
	
	public void execute(){};
}
