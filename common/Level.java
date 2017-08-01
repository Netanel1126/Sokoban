package common;

import java.io.Serializable;
import java.util.ArrayList;

import model.data.Box_Destination;

@SuppressWarnings("serial")
public class Level implements Serializable{
	private int previousLocation;
	private int numOfBD;
	
	private ArrayList<ArrayList<Sprite>> list_sprite;
	
	public Level() {
		list_sprite = new ArrayList<ArrayList<Sprite>>();
		previousLocation = 0;
		numOfBD = 0;
	}
	
	public void clear(){
		list_sprite.clear();
	}
	
	// search if sprite changes
	public void searchChanged(){
		previousLocation = 0;
		for (int i = 0; i < list_sprite.size() ; i++) {
			for(int j=0; j<list_sprite.get(i).size();j++){
				int pHeight = list_sprite.get(i).get(j).getPreviousLocation().getHeight();
				int pWidth = list_sprite.get(i).get(j).getPreviousLocation().getWidth();
				if(list_sprite.get(i).get(j).isWasBdChanged() && list_sprite.get(pHeight).get(pWidth).getSprite() == "@"){
					previousLocation++;
				}
			}
		}
	}
	
	// get the number of box destination
	public void numOf_bd(){
		numOfBD = 0;
		for (int i = 0; i < list_sprite.size() ; i++) {
			for(int j=0; j<list_sprite.get(i).size();j++){
				if(list_sprite.get(i).get(j)  instanceof Box_Destination )
					numOfBD++;
			}
		}
	}
	
	// Win Game Function
	public boolean WinGame(){
		searchChanged();
		numOf_bd();
		if(previousLocation == numOfBD)
			return true;
		return false;
	}
	
	// get the longest rows in the level
	public int getLongestRow(){
		int longest = 0 , big = 0;
		for (int i = 0; i < list_sprite.size() - 1; i++) {
			if(list_sprite.get(i).size() > big){
				big = list_sprite.get(i).size();
				longest = i;
			}
		}
		return longest;
	}
	
	public ArrayList<ArrayList<Sprite>> getList_sprite() {
		return list_sprite;
	}

	public void setList_sprite(ArrayList<ArrayList<Sprite>> list_sprite) {
		this.list_sprite = list_sprite;
	}
}