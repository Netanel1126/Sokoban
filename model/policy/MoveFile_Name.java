package model.policy;

import java.util.ArrayList;

import common.Level;
import common.Sprite;
import model.policy.MySokobanPolicy;
import model.data.Box_Destination;
import model.data.Location;
//Move the player when the user write Move {up/down/right/left} - only one Move at any time
public class MoveFile_Name{

	private Location playerLocation = new Location(); 
	private MySokobanPolicy new_policy;
	private Location playerOn_bd;
	private ArrayList<Location> locationOfmissingBD = new ArrayList<Location>();
	private ArrayList<Location> previousLocation = new ArrayList<Location>();
	
	public MoveFile_Name() {}
	
	// Finds the player = A
	public void findPlayer(Level my_level) {
		for (int i = 0; i < my_level.getList_sprite().size(); i++) {
			for (int j = 0; j < my_level.getList_sprite().get(i).size(); j++) {
				if (my_level.getList_sprite().get(i).get(j).returnSpriteString() == "A"){
					playerLocation = new Location(i,j);
					i = my_level.getList_sprite().size();
					break;	
				}
			}
		}
	}
	
	// basic replacement of object
	public void basicMove(Level my_level,int y,int x,int new_y,int new_x){
		Sprite tempSprite = new Sprite();
		tempSprite = my_level.getList_sprite().get(y).get(x);
		
		my_level.getList_sprite().get(y).set(x, my_level.getList_sprite().get(new_y).get(new_x));
		my_level.getList_sprite().get(y).get(x).setWasChanged(true);
		my_level.getList_sprite().get(new_y).get(new_x).getMy_location().setLocation(new_y, new_x);
		
		my_level.getList_sprite().get(new_y).set(new_x, tempSprite);
		my_level.getList_sprite().get(new_y).get(new_x).setWasChanged(true);
		my_level.getList_sprite().get(y).get(x).getMy_location().setLocation(y,x);
	}
	
	// Check if A can move and than move it in basicMove
	public void movePlayer(Level my_level,int y,int x,int new_y,int new_x){	
		if (new_policy.canIPush(new_y, new_x, "A")){
			basicMove(my_level, y, x, new_y, new_x);
		}
	}
	
	// Moves the box
	public boolean moveBox(Level my_level,int y,int x,int new_y,int new_x){
		if(new_policy.canIPush(new_y, new_x, "@")){
			 if(new_policy.isBoxDestination(new_y, new_x)) {
				 moveBox_Destination(my_level, new_y, new_x);
				 basicMove(my_level, y, x, new_y, new_x);
				 return true;
			 }
			basicMove(my_level, y, x, new_y, new_x);
			return true;
		}
		return false;
	}
	//Move the box_Destination and change it to " "
	public void moveBox_Destination(Level my_level,int new_y,int new_x){
		playerOn_bd = new Location(new_y, new_x);
		my_level.getList_sprite().get(new_y).get(new_x).setPreviousLocation(playerOn_bd);
		my_level.getList_sprite().get(new_y).get(new_x).setSprite(" ");
		my_level.getList_sprite().get(new_y).get(new_x).setMy_image("./resources/floor.png");
		my_level.getList_sprite().get(new_y).get(new_x).setWasBdChanged(true);
		my_level.getList_sprite().get(new_y).get(new_x).setWasChanged(true);
	}
	
	// check if box destination can move to it's original location
	public void isThereAChanged(Level my_level){
		int new_x = 0,new_y = 0,y = 0,x = 0;
		searchChanged(my_level);
		for (int i = 0; i < locationOfmissingBD.size(); i++) {		
			y= locationOfmissingBD.get(i).getHeight();
			x= locationOfmissingBD.get(i).getWidth();
		    new_y = previousLocation.get(i).getHeight();
			new_x = previousLocation.get(i).getWidth();
			
			try{
				if(my_level.getList_sprite().get(new_y).get(new_x).returnSpriteString() == " "){
					my_level.getList_sprite().get(y).get(x).setSprite("o");
					my_level.getList_sprite().get(y).get(x).setMy_image("./resources/box_destination.png");
					basicMove(my_level, y, x, new_y, new_x);
					my_level.getList_sprite().get(new_y).get(new_x).setWasBdChanged(false);
					my_level.getList_sprite().get(new_y).get(new_x).setWasChanged(true);
				}
			}catch (Exception e) {
				
				System.out.println(my_level.getList_sprite().get(new_y).get(new_x).toString());}
		        //    System.out.println("TEST");
			}		
		
	}
	
	// Moves Box Destination back
	public void moveFromBox_DestinationLevel(Level my_level,int y,int x){
		int new_y = 0,new_x = 0;
		my_level.getList_sprite().get(y).get(x).setSprite("o");
		my_level.getList_sprite().get(new_y).get(new_x).setMy_image("./resources/box_destination.png");
		new_y = my_level.getList_sprite().get(y).get(x).getPreviousLocation().getHeight();
		new_x = my_level.getList_sprite().get(y).get(x).getPreviousLocation().getWidth();
		basicMove(my_level, y, x, new_y, new_x);
	}

	
	// finds all box destination that were changed
	public void searchChanged(Level my_level){
		for (int i = 0; i < my_level.getList_sprite().size() ; i++) {
			for(int j=0; j<my_level.getList_sprite().get(i).size();j++){
				if(my_level.getList_sprite().get(i).get(j).isWasChanged() && my_level.getList_sprite().get(i).get(j) instanceof Box_Destination){
					previousLocation.add(my_level.getList_sprite().get(i).get(j).getPreviousLocation());
					locationOfmissingBD.add(my_level.getList_sprite().get(i).get(j).getMy_location());
				}
			}
		}
	}
	
	public void moveToLocation(Level my_level,String moveTo){
		int y = playerLocation.getHeight();
		int x = playerLocation.getWidth();
		new_policy = new MySokobanPolicy(my_level);
		
		switch (moveTo) {
		case "up":
			 if(new_policy.canIPush(y - 1, x,"A")){ 
				 my_level.getList_sprite().get(y).get(x).setMy_image("./resources/player-up.png");
				if(new_policy.isBoxDestination(y - 1, x)) 
						 moveBox_Destination(my_level, y - 1, x);
				if (new_policy.isBox(y - 1, x)){
						 if(moveBox(my_level, y - 1, x, y - 2, x)){		
							 movePlayer(my_level, y, x, y - 1, x);	 
						 }
					 }
					 	else
					 		movePlayer(my_level, y, x, y - 1, x);
			 }
			 			 isThereAChanged(my_level);
			break;
		case "down":
			if(new_policy.canIPush(y + 1, x,"A")){ 
				 my_level.getList_sprite().get(y).get(x).setMy_image("./resources/player-down.png");
				if(new_policy.isBoxDestination(y + 1, x)) 
				moveBox_Destination(my_level, y + 1, x);
				if (new_policy.isBox(y + 1, x)){
					if(moveBox(my_level, y + 1, x, y + 2, x)){		
						movePlayer(my_level, y, x, y + 1, x);
					}
				}
				else
					movePlayer(my_level, y, x, y +  1, x);
			}
					isThereAChanged(my_level);
			break;
			
		case "left":
			if(new_policy.canIPush(y, x - 1,"A")){ 
				 my_level.getList_sprite().get(y).get(x).setMy_image("./resources/player-left.png");
				if(new_policy.isBoxDestination(y, x - 1)) 
					moveBox_Destination(my_level, y, x - 1);
				
				if (new_policy.isBox(y, x - 1)){
					if(moveBox(my_level, y, x - 1, y, x - 2)){
						movePlayer(my_level, y, x, y, x - 1);
					}
				}
				else
					movePlayer(my_level, y, x, y, x - 1);
			}
			isThereAChanged(my_level);
			break;
			
		case "right":
			if(new_policy.canIPush(y, x + 1,"A")){
				 my_level.getList_sprite().get(y).get(x).setMy_image("./resources/player-right.png");
				if(new_policy.isBoxDestination(y, x + 1)) 
					moveBox_Destination(my_level, y, x + 1);
				if (new_policy.isBox(y, x + 1)){
		 		 	if(moveBox(my_level, y, x + 1, y, x + 2)){
		 		 		movePlayer(my_level, y, x, y, x + 1);
		 		 	}
				}
					else
					movePlayer(my_level, y, x, y, x + 1);
			}
					isThereAChanged(my_level);
				break;
				
		default:
			break;
		}	
	}
	
	public void move(Level my_level,String moveTo){
		if(!my_level.getList_sprite().isEmpty()){
			findPlayer(my_level);
			moveToLocation(my_level, moveTo);	
			}
	}	
}