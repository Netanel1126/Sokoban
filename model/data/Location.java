package model.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Location implements Serializable{
	
	private int width;
	private int height;
	
	public Location() {
		height = 0;
		width = 0;
	}
	
	public Location(int height , int width) {
		this.width = width;
		this.height = height;
	}
	
	// GET & SET //

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setLocation(int y,int x){
		this.width = x;
		this.height = y;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}