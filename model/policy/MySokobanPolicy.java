package model.policy;

import common.Level;
//The policy for the game
public class MySokobanPolicy {
	
	private Level my_level = null;
	
	public MySokobanPolicy(Level new_level) {
		my_level = new_level;
	}
	// check if you can push the sprite
	public boolean canIPush(int new_y,int new_x,String sprite){
		if (exist(new_y, new_x) == false){
		return false;
		}
		if(isWall(new_y, new_x))
			return false;
		if(isBox(new_y, new_x) && sprite == "@")	
			return false;
		
		return true;
	}
	
	// check if the player move to an existing place
	public boolean exist(int new_y,int new_x){
		try {
			my_level.getList_sprite().get(new_y).get(new_x);
		} catch (Exception e) {
			return false;
		}			
		return true;	
	}
	
	//check if there is a player
	public boolean isPlayer(int new_y,int new_x){
		if(my_level.getList_sprite().get(new_y).get(new_x).returnSpriteString() == "A" )
			return true;
		
		return false;
	}
	// check if there is a wall
	public boolean isWall(int new_y,int new_x){
		if(my_level.getList_sprite().get(new_y).get(new_x).returnSpriteString() == "#" )
			return true;
		
		return false;
	}
	// check if there is a box
	public boolean isBox(int new_y,int new_x){
		if(my_level.getList_sprite().get(new_y).get(new_x).returnSpriteString() == "@" )
			return true;
		
		return false;
	}
	// check if there is a box destination
	public boolean isBoxDestination(int new_y,int new_x){
		if(my_level.getList_sprite().get(new_y).get(new_x).returnSpriteString() == "o" )
			return true;
		
		return false;
	}
	// check if the player can pull boxes
	public boolean pullBoxs(){
		return false;
	}
	
}