package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import common.Hibernate_UsersAndQuery;
import common.Level;
import model.hibernate.My_Sokoban_Hibernate;
import model.policy.DisplayFile_Name;
import model.policy.LoadFile_Name;
import model.policy.MoveFile_Name;
import model.policy.SaveFile_Name;

public class MyModel extends Observable implements Model {
	private MoveFile_Name mfn;
	private SaveFile_Name sfn;
	private LoadFile_Name lfn;
	private DisplayFile_Name dfn;
	private List<String> params;
	private My_Sokoban_Hibernate msb;

	public MyModel() {
		params = new LinkedList<String>();
		mfn = new MoveFile_Name();
		sfn = new SaveFile_Name();
		lfn = new LoadFile_Name();
		dfn = new DisplayFile_Name();
		msb = new My_Sokoban_Hibernate();
	}

	// updates the observer
	public void updateObserver(String string_params) {
		this.setChanged();
		params.add(string_params);
		this.notifyObservers(params);
	}

	// move with display
	@Override
	public void move2(Level my_level, String moveTo) {
		mfn.move(my_level, moveTo);
		updateObserver("Display");
	}

	// save with display
	@Override
	public void save(Level my_level, String name) {
		sfn.Save(name, my_level);
		updateObserver("Display_Save");
	}

	@Override
	public void display(Level my_level) {
		dfn.Display(my_level);
	}

	// load with display
	@Override
	public void load(Level my_level, String name) {
		my_level.getList_sprite().clear();
		lfn.Load(my_level, name);
		mfn = new MoveFile_Name();
		updateObserver("Display");
	}

	// exit with display
	@Override
	public void exit() {
		updateObserver("Display");
	}
	@Override
	public void addUser(Hibernate_UsersAndQuery user) {
		msb.AddPlayerToSQL(user);
	}
	@Override
	public void levelQuery(Hibernate_UsersAndQuery querylist,String prefix){
		msb.ScoreBoard_Level(querylist, prefix);
	}
	@Override
	public void UserQuery(Hibernate_UsersAndQuery querylist,String prefix){
		msb.ScoreBoard_Player(querylist, prefix);
	}
}