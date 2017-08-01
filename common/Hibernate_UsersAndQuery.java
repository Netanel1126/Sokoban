package common;

import java.util.LinkedList;
import java.util.List;

public class Hibernate_UsersAndQuery {
	private LinkedList<User> user_list;
	private List<User> querylist;
	
	public Hibernate_UsersAndQuery() {
		user_list = new LinkedList<User>();
	}

	public LinkedList<User> getUser_list() {
		return user_list;
	}

	public void setUser_list(LinkedList<User> user_list) {
		this.user_list = user_list;
	}

	public List<User> getQuerylist() {
		return querylist;
	}

	public void setQuerylist(List<User> querylist) {
		this.querylist = querylist;
	}
}
