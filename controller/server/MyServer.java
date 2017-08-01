package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import view.ClientCLI;

public class MyServer {
	
	private ServerSocket server = null;
	
	// start the server with client and port
	public void startMy_server(ClientCLI view,int port) throws IOException{
		try {
			server = new ServerSocket(port);			
		} catch (IOException e1) {
			System.out.println("Error - Port Not Availble");
			server.close();
			e1.printStackTrace();
		}
		try{
			Socket client = server.accept();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					//try to create a new CLI
					try {
						view.handleclient(client.getInputStream(), client.getOutputStream());			
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}).start();
		} catch (SocketTimeoutException e) {
		}
	}

	public void stopServer(){
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}