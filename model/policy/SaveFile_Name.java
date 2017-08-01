package model.policy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import common.Level;
//Save the player when the user write Save
public class SaveFile_Name{

		private LevelLoader streamer = null; 
		private FileOutputStream out = null;
		
		public void Save(String name , Level my_level) {
			// save the level according to the suffix
			if (name.contains(".xml"))
				streamer = new MyXMLLevel();
			else if (name.contains(".obj"))
				streamer = new MyObjectLevel();
			else if (name.contains(".txt"))
				streamer = new MyTextLevel();
			else
				 System.out.println("Error Suffix not supported");
			
			try {
				out = new FileOutputStream(name);
			} catch (FileNotFoundException e) {
				System.out.println("Error - Couldn't Open File");
				e.printStackTrace();
			}
			try {
				streamer.saveLevel(out, my_level);
			} catch (ClassNotFoundException e) {
				System.out.println("Error - SaveFile - NotFoundException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error - SaveFile - IOException");
				e.printStackTrace();
			}
		}
	}