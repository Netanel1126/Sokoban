package model.hibernate;

import java.util.ArrayList;

import org.hibernate.query.Query;

import common.Hibernate_UsersAndQuery;
import common.User;

public interface Sokoban_Hibernate {

	public void ScoreBoard_Level(Hibernate_UsersAndQuery querylist, String prefix);

	public void ScoreBoard_Player(Hibernate_UsersAndQuery querylist, String prefix);

	void AddPlayerToSQL(Hibernate_UsersAndQuery user);
}
