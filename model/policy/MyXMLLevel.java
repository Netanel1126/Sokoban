package model.policy;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

import common.Level;

public class MyXMLLevel implements LevelLoader {
	public void loadLevel(InputStream in,Level my_level) {
		XMLDecoder decoder = new XMLDecoder(in);
		Level new_level = (Level)decoder.readObject();
		my_level.setList_sprite(new_level.getList_sprite());
		decoder.close();
	}

	public void saveLevel(OutputStream out, Level new_level) {
		XMLEncoder encoder = new XMLEncoder(out);
		encoder.writeObject(new_level);
		encoder.close();
	}
}