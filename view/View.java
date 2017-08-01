package view;

import org.hibernate.query.Query;

import common.Hibernate_UsersAndQuery;
import common.Level;
import common.User;

public interface View {
	void Display(Level my_level);
	void displayMessage(String msg);
	//void Display_addUser(Level my_level, User user);
	void Display_levelQuery(Hibernate_UsersAndQuery querylisty);
	void Display_UserQuery(Hibernate_UsersAndQuery querylisty);
	void Display_addUser(Level my_level, Hibernate_UsersAndQuery user);
}
