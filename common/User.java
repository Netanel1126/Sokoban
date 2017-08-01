package common;

import javax.persistence.*;

@Entity(name = "ScoreBoard")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scoreid;
	@Column(name = "UserName")
	private String user_name;
	@Column(name = "Time_ToComplete")
	private int time;
	@Column(name = "Steps")
	private int steps;
	@Column(name = "Level_Name")
	private String level_name;

	public String getUser_name() {
		return user_name;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String user_name, int time, int steps, String level_name) {
		this.user_name = user_name;
		this.time = time;
		this.steps = steps;
		this.level_name = level_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	@Override
	public String toString() {
		return "User [UserName=" + user_name + ", Time_ToComplete=" + time + ", Steps=" + steps
				+ ", Level_Name=" + level_name + "]";
	}
}
