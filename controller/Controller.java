package controller;

import java.util.Observable;
import java.util.Observer;

import controller.commands.A_Command;

public interface Controller extends Observer{
	public void insertCommand(A_Command new_command);
	public void update(Observable o, Object arg); 
	public void start();
    public void stop();
}