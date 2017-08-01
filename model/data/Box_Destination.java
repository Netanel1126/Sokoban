package model.data;

import common.Sprite;
import javafx.scene.canvas.GraphicsContext;

@SuppressWarnings("serial")
public class Box_Destination extends Sprite{
	
	private String sprite ="o";

	public Box_Destination(){
		super();
		setMy_image("./resources/box_destination.png");
	}
	
	public Box_Destination(int x ,int y) {
		super(x,y);
		setMy_image("./resources/box_destination.png");
	}
	
	public void draw(GraphicsContext gc, double y, double x,double dx,double dy) {
		gc.drawImage(getMy_image(), x, y, dx, dy);
	}
	
	// GET & SET //
	
	public String returnSpriteString(){return sprite;}
	
	public String getSprite() {
		return sprite;
	}
	
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
	
	public void printSprite(){
		System.out.print(sprite);
	}
}