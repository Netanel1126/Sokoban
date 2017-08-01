package model.data;

import common.Sprite;
import javafx.scene.canvas.GraphicsContext;

@SuppressWarnings("serial")
public class Wall extends Sprite {
	
	private String sprite ="#";

	public Wall(){
		super();
		setMy_image("./resources/wall.png");
	}
	
	public Wall(int y,int x) {
		super(x,y);
		setMy_image("./resources/wall.png");
	}
	
	public void draw(GraphicsContext gc, double y, double x,double dx,double dy) {
		gc.drawImage(getMy_image(), x, y, dx, dy);
	}
	
	public void printSprite(){
		System.out.print(sprite);
	}
	
	// SET & GET //
	
	public String getSprite() {
		return sprite;
	}
	
	
	public String returnSpriteString(){
		return sprite;
	}
	
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

}