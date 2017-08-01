package model.data;

import javafx.scene.canvas.GraphicsContext;

public interface GameCharacter {	
	void draw(GraphicsContext gc, int i, int j, double cellWidth, double cellHeight);
}

