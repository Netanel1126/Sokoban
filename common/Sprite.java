package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.data.Location;

@SuppressWarnings("serial")
public class Sprite extends Canvas implements Serializable{
	private Location my_location;
	private String sprite = "Empty_Sprite";
	private boolean wasChanged;
	private Location previousLocation;
	private Image my_image;
	private boolean wasBdChanged;
	
	public Sprite() {}
	
	public Sprite(int x,int y) {
		my_location = new Location(y, x);
		wasChanged = true;
		wasBdChanged = false;
		previousLocation = new Location(y, x);
		//setMy_image("./resources/background.png");
	}

	public void printSprite(){}
	
	// draw image
	public void draw(GraphicsContext gc, double y, double x,double dx,double dy){
		gc.drawImage(getMy_image(), y, x, dx, dy);
	}
	
	// GET & SET //
	
	public Image getMy_image() {
		return my_image;
	}

	public void setMy_image(Image my_image) {
		this.my_image = my_image;
	}

	public void setMy_image(String my_image) {
		try {
			this.my_image = new Image(new FileInputStream(my_image));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getSprite() {
		return sprite;
	}

	public String returnSpriteString(){
		return sprite;
	}
	
	public Location getMy_location() {
		return my_location;
	}
	
	public boolean isWasChanged() {
		return wasChanged;
	}
	
	public Location getPreviousLocation() {
		return previousLocation;
	}
	
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public void setMy_location(Location my_location) {
		this.my_location = my_location;
	}
	
	public void setLoction (int width,int height) {
		my_location.setWidth(width);
		my_location.setHeight(height);
	}

	public void setWasChanged(boolean wasChanged) {
		this.wasChanged = wasChanged;
	}

	public void setPreviousLocation(Location previousLocation) {
		this.previousLocation = previousLocation;
	}

	public boolean isWasBdChanged() {
		return wasBdChanged;
	}

	public void setWasBdChanged(boolean wasBdChanged) {
		this.wasBdChanged = wasBdChanged;
	}

}