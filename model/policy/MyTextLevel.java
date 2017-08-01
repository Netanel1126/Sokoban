// Test
package model.policy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import common.Level;
import common.Sprite;
import model.data.Box;
import model.data.Box_Destination;
import model.data.Player;
import model.data.Space;
import model.data.Wall;

//load & save a level via text
public class MyTextLevel implements LevelLoader {
	
	@Override
	public void loadLevel(InputStream in,Level my_level) {

       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
       String line;
       int x = 0,y = 0;
      
       //Level my_level = new Level();
       try {
		while ((line = reader.readLine()) != null) {
			my_level.getList_sprite().add(new ArrayList<Sprite>());	
		    x = 0;
			   while (line.length() > x) {
					switch (line.charAt(x)) {
					case '#': my_level.getList_sprite().get(y).add(new Wall(x,y));	
						break;
					case ' ': my_level.getList_sprite().get(y).add(new Space(x,y));	
						break;
					case '@': my_level.getList_sprite().get(y).add(new Box(x,y));	
				    	break;
				    case 'o': my_level.getList_sprite().get(y).add(new Box_Destination(x,y));
			        	break;			
				    case 'A': my_level.getList_sprite().get(y).add(new Player(x,y));	
				    	break;	
					}
					x++;
			}
			   y++;
		   }
	} catch (IOException e) {
		System.out.println("Error - File Corrupted");
		e.printStackTrace();
		}
       try {
		reader.close();
	} catch (IOException e) {
		System.out.println("Error - Can't Close File");
		e.printStackTrace();
		}	
	}

	@Override
	public void saveLevel(OutputStream out, Level new_level) throws IOException, ClassNotFoundException {
		PrintWriter p = new PrintWriter(new OutputStreamWriter(out));
		for (int i = 0; i <new_level.getList_sprite().size() ; i++) {
			for(int j=0; j<new_level.getList_sprite().get(i).size();j++){
				 p.write(new_level.getList_sprite().get(i).get(j).returnSpriteString());
			}
			p.println("");
		}
		p.close(); 
	}
}