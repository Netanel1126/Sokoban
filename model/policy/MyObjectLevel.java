package model.policy;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import common.Level;

// load & save a level via object
public class MyObjectLevel implements LevelLoader{
	
	public void loadLevel(InputStream in,Level my_level) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(in);
		Level new_level = (Level)ois.readObject();
		my_level.setList_sprite(new_level.getList_sprite());
		ois.close();
		//return new_level;
}

	public void saveLevel(OutputStream out, Level new_level) throws IOException, ClassNotFoundException{
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(new_level);
		oos.close();
	}
}