package view;

import common.Level;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameDisplayer extends Canvas {
	private GraphicsContext gc;

	// show the level in the canvas
	public boolean redraw(Level myLevel){
		if (!myLevel.getList_sprite().isEmpty()){
			
			gc = getGraphicsContext2D();
			int rows = myLevel.getList_sprite().size();
			int cols = myLevel.getList_sprite().get(myLevel.getLongestRow()).size();		
			double cellWidth = getWidth() / cols;
			double cellHeight = getHeight() / rows;
	    
			for (int i = 0; i < myLevel.getList_sprite().size() ; i++) {
				for(int j=0; j < myLevel.getList_sprite().get(i).size();j++){
					if(myLevel.getList_sprite().get(i).get(j).isWasChanged()== true){			
						myLevel.getList_sprite().get(i).get(j).draw(gc, i*cellHeight, j*cellWidth,cellWidth,cellHeight);
						myLevel.getList_sprite().get(i).get(j).setWasChanged(false);
					}
				}
			}
		}
		// win condition
		if(!(myLevel.getList_sprite().isEmpty()) && myLevel.WinGame()){
			myLevel.clear();
			return true;
		}
		return false;
	}
	
	public boolean checkIfChanged(Level myLevel)
	{
		for (int i = 0; i < myLevel.getList_sprite().size() ; i++)
			for(int j=0; j < myLevel.getList_sprite().get(i).size();j++)
				if(myLevel.getList_sprite().get(i).get(j).isWasChanged()== true)
					return true;
		return false;
	}
	
	// clear the canvas
	public void clearCanvas(){
		gc.clearRect(0, 0, getWidth(), getHeight());
	}
	
	// draw image when you won
	public void drawWinner(){
		gc.drawImage(new Image("file:resources/win.png"), 0, 0, getWidth(), getHeight());
		//saveScoreBoard();
	}
	
	public void saveScoreBoard(){
		
	}
}