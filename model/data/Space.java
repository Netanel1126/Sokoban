package model.data;

import common.Sprite;
import javafx.scene.canvas.GraphicsContext;

@SuppressWarnings("serial")
public class Space extends Sprite {
	
	private String sprite =" ";
	
	public Space(){
		super();
		setMy_image("./resources/floor.png");
	}
	
	public Space(int x, int y) {
		super(x,y);
		setMy_image("./resources/floor.png");
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