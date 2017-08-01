package model.data;

import common.Sprite;
import javafx.scene.canvas.GraphicsContext;

@SuppressWarnings("serial")
public class Player extends Sprite {

	private String sprite ="A";
	
	public Player(){
		super();
		setMy_image("./resources/player.png");
	}
	
	public Player(int x , int y) {
		super(x,y);
		setMy_image("./resources/player.png");
	}
	
	public void draw(GraphicsContext gc, double y, double x,double dx,double dy) {
		gc.drawImage(getMy_image(), x, y, dx, dy);
	}

	public void printSprite(){
		System.out.print(sprite);
	}
	
	// GET & SET //
	
	public String returnSpriteString(){
		return sprite;
	}
	
	public String getSprite() {
		return sprite;
	}
	
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
}