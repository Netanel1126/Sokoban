//
package model.policy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import common.Level;
 
public interface LevelLoader {
	
	public void loadLevel(InputStream in,Level my_level) throws IOException, ClassNotFoundException;
	public void saveLevel(OutputStream out, Level new_level) throws IOException, ClassNotFoundException;
}