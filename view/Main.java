package view;
	
import java.io.File;
import controller.SokobanController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();	
			root.setPrefWidth(Control.USE_COMPUTED_SIZE);
			root.setPrefHeight(Control.USE_COMPUTED_SIZE);
			MainWindowController view = loader.getController();
			// Icon & Title for the program
			primaryStage.setTitle("Sokoban");
			File f = new File("resources/icon.png");
			Image image = new Image(f.toURI().toString());
			primaryStage.getIcons().add(image);

			// Music
			view.CallOnMusic();
			
			Scene scene = new Scene(root,Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			init(view);
			
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> { 
				event.consume();
				view.exitFile(); });
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void init(MainWindowController view){
		MyModel model = new MyModel();
		SokobanController skb = new SokobanController(model, view);
		model.addObserver(skb);
		view.addObserver(skb);		
	}
	
	public static void main(String[] args) {
	     try{
			if(args[0].equals("server")){
				MyModel model = new MyModel();
				ClientCLI view = new ClientCLI();
				SokobanController skb = new SokobanController(model, view);
				model.addObserver(skb);
				view.addObserver(skb);
				System.out.println("server is live");
				skb.runServer((Integer.parseInt(args[1])));
			}
			
		} catch (Exception e) {launch(args);}  
	}
}