package model.policy;

import common.Level;
// Display the level when the user write Display
public class DisplayFile_Name {
	
	public void Display (Level my_level){
		for (int i = 0; i < my_level.getList_sprite().size() ; i++) {
			for(int j=0; j<my_level.getList_sprite().get(i).size();j++){
				my_level.getList_sprite().get(i).get(j).printSprite();
				System.out.println(" ");
			}
		}
	}
}