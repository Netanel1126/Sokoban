package model;

import org.hibernate.query.Query;

import common.Hibernate_UsersAndQuery;
import common.Level;
import common.User;

public interface Model {
	public void move2(Level new_level,String moveTo);
	public void save(Level new_level,String name);
	public void display(Level new_level);
	public void load(Level new_level ,String name);
	public void exit();
	public void updateObserver(String string_params);
	public void levelQuery(Hibernate_UsersAndQuery querylist, String prefix);
	public void UserQuery(Hibernate_UsersAndQuery querylist, String prefix);
	void addUser(Hibernate_UsersAndQuery user);
}
