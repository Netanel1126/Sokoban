package model.policy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import common.Level;
//Load the level when the user write Load FILENAME.txt = FILENAME is changed to the actual name of the file
public class LoadFile_Name{
	
	private LevelLoader streamer = null; 
	private FileInputStream in = null;
	
	public void Load(Level new_Level,String name) {
		new_Level.clear();	
		if (name.contains(".xml"))
			streamer = new MyXMLLevel();
		else if (name.contains(".obj"))
			streamer = new MyObjectLevel();
		else if (name.contains(".txt"))
			streamer = new MyTextLevel();
		else{
			 System.out.println("Error File Not Supported");
		}
			
		try {
				in = new FileInputStream(name);
			} catch (FileNotFoundException e1) {
				System.out.println("Error - couldn't open");
				System.out.println("");
				e1.printStackTrace();
				return;
			}
		try {
			streamer.loadLevel(in,new_Level);
		} catch (ClassNotFoundException e) {
			System.out.println("Error - streamer in1");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error - streamer in2");
			e.printStackTrace();
		}
	}

}