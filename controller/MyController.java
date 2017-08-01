package controller;

import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.A_Command;

public class MyController {
	private BlockingQueue<A_Command> queue;
	private boolean stop = true;
	
	public MyController(){
		queue = new ArrayBlockingQueue<A_Command>(20);
	}
	
	// insert command
	public void insertCommand(A_Command cmd) {
		try {
			queue.put(cmd);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	public void start() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (stop == true) {
					try {
						A_Command cmd = queue.poll(1, TimeUnit.SECONDS);
						if (cmd != null)
							cmd.execute();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		thread.start();
	}
	
	public void stop() {
		stop = false;
	}

}