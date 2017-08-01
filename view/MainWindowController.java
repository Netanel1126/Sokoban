package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import common.Hibernate_UsersAndQuery;
import common.Level;
import common.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends Observable implements Initializable, View {
	protected Text textArea = new Text();
	private LinkedList<String> command = new LinkedList<>();
	private StringProperty Counter;
	private int count;
	private boolean levelExist = false;
	private Timer t;
	protected MediaPlayer mediaPlayer;
	private StringProperty steps;
	private int stepsCounter = 0;
	private ObservableList<String> choiceList = FXCollections.observableArrayList("UserName", "Level");
	private File chosen;
	private String username = null;
	private UserPopupController upc;
	private ScoreBoardController sbc;
	private boolean flag = true;

	@FXML
	private GameDisplayer sd;
	@FXML
	private Text myText;
	@FXML
	private Text mySteps;
	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox choiceBox;
	@FXML
	Button but, searchButton;
	@FXML
	TextField searchText;

	public MainWindowController() {
	}

	// set the keys from the keyboard
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.setValue("UserName");
		choiceBox.setItems(choiceList);

		sd.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> sd.requestFocus());
		sd.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				command.clear();
				command.add("Move");
				if (event.getCode() == KeyCode.UP) {
					command.add("up");
				}
				if (event.getCode() == KeyCode.DOWN) {
					command.add("down");
				}
				if (event.getCode() == KeyCode.RIGHT) {
					command.add("right");
				}
				if (event.getCode() == KeyCode.LEFT) {
					command.add("left");
				} else
					command.add("ERROR");
				setChanged();
				notifyObservers(command);
			}
		});

		but.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				command.clear();
				command.add("levelQuery");
				if (chosen != null) {
					command.add(chosen.toString());
					setChanged();
					notifyObservers(command);
				} else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Alert");
					alert.setHeaderText(null);
					alert.setContentText("Please Open a New Level before accessing the Score Board");
					@SuppressWarnings("unused")
					Optional<ButtonType> result = alert.showAndWait();
				}
			}
		});

		searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String prefix = searchText.getText();
				search(prefix);
			}
		});
	}

	// calls for loader in the gui
	public void loadFile() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*txt"));
		fc.setTitle("Open Sokoban File");
		fc.setInitialDirectory(new File("./resources"));
		try {
			sd.clearCanvas();
		} catch (Exception e) {
		}
		try {
			chosen = fc.showOpenDialog(null);
			command.clear();
			command.add("Load");
			command.add(chosen.toString());
			// if you win the game than the music stops
			// and than when you load another level
			// the music turn on again
			if (mediaPlayer.getStatus().toString() == "STOPPED")
				CallOnMusic();
			// timer & binding
			if(levelExist){
				t.cancel();
				t.purge();
			}
			Counter = new SimpleStringProperty();
			steps = new SimpleStringProperty();
			stepsCounter = -1;
			steps.set("" + (stepsCounter));
			t = new Timer();
			t.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					Counter.set("" + (++count));
				}
			}, 0, 1000);
			count = 0;
			myText.textProperty().bind(Counter);
			mySteps.textProperty().bind(steps);
			levelExist = true;

			this.setChanged();
			this.notifyObservers(command);
		} catch (RuntimeException e) {
		}
	}

	// calls for saver in the gui
	public void saveFile() {
		FileChooser fn = new FileChooser();
		fn.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*txt"));
		fn.setTitle("Save Sokoban File");
		fn.setInitialDirectory(new File("./resources/save"));
		try {
			File chosen = fn.showSaveDialog(null);
			command.clear();
			command.add("Save");
			command.add(chosen.toString());
			this.setChanged();
			this.notifyObservers(command);
		} catch (RuntimeException e) {
		}
	}

	// calls for exit in the gui
	// when you click Exit or the X than you see a confirmation
	// so you won't close the game by accident
	public void exitFile() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Close Sokoban");
		alert.setHeaderText(null);
		alert.setContentText("Do You Want To Exit The Game?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			command.clear();
			command.add("Exit");
			setChanged();
			notifyObservers(command);
			Platform.exit();
			System.exit(0);
		}
	}

	// when you click on about a popup opens with this
	public void about() throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About Sokoban");
		alert.setHeaderText(null);
		alert.setContentText("Hello this is a test for our Sokoban Project");
		alert.showAndWait();
	}

	// display with timer and music
	@Override
	public void Display(Level myLevel) {
		if (levelExist) {
			if (sd.checkIfChanged(myLevel))
				steps.set("" + (++stepsCounter));
			if (sd.redraw(myLevel)) {
				mediaPlayer.stop();
				t.cancel();
				t.purge();
				sd.drawWinner();
				
				command.clear();
				command.add("AddUser");
				this.setChanged();
				this.notifyObservers(command);
			}
		}
	}
//Show the add user screen and take the user name
	@Override
	public void Display_addUser(Level my_level, Hibernate_UsersAndQuery user) {
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPopup.fxml"));
					BorderPane root1 = (BorderPane) loader.load();
					root1.setPrefWidth(Control.USE_COMPUTED_SIZE);
					root1.setPrefHeight(Control.USE_COMPUTED_SIZE);
					upc = loader.getController();

					Stage stage = new Stage();
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setTitle("Add");

					Scene scene = new Scene(root1, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
					// scene.getStylesheets().add(getClass().getResource("UserPopupApplication.css").toExternalForm());
					stage.setScene(scene);
					stage.initStyle(StageStyle.DECORATED);

					stage.showAndWait();

					upc.enterUserName();
					username = upc.getUserName();
					user.getUser_list().clear();
					if (username.length() > 1)
						user.getUser_list().add(new User(username, count, stepsCounter, chosen.toString()));
					flag = false;

				} catch (Exception e) {
					System.out.println("ERROR - opes");
				}
			}
		});
//make the thread sleep until the "Add User" screen is finished 
		while (flag == true)
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		flag = true;
	}
//Show the ScoreBord screen 
	public void Display_query(Hibernate_UsersAndQuery querylist, String title) {

		Platform.runLater(new Runnable() {
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreBoard.fxml"));
					BorderPane root1 = (BorderPane) loader.load();
					sbc = loader.getController();

					Stage stage = new Stage();
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setTitle(title);

					Scene scene = new Scene(new Group());
					scene.getStylesheets().add(getClass().getResource("UserPopupApplication.css").toExternalForm());
					stage.setScene(scene);
					stage.initStyle(StageStyle.DECORATED);

					sbc.setQuery(querylist);
					sbc.addToColumn();

					final VBox vbox = new VBox();
					vbox.setSpacing(5);
					vbox.setPadding(new Insets(10, 10, 10, 10));
					vbox.getChildren().addAll(sbc.getTable());

					((Group) scene.getRoot()).getChildren().addAll(vbox);

					stage.showAndWait();

					if (sbc.getUsernameToSend() != null) {
						command.clear();
						command.add("ScoreBoard ");
						command.add(sbc.getUsernameToSend() + " ");
						command.add("UserQuery");
						setChanged();
						notifyObservers(command);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR");
				}
			}
		});
	}

	@Override
	public void Display_levelQuery(Hibernate_UsersAndQuery querylist) {
		Display_query(querylist, "Score Board");
	}

	@Override
	public void Display_UserQuery(Hibernate_UsersAndQuery querylist) {
		Display_query(querylist, "User Info");
	}
//search by user prefix and his choice
	public void search(String prefix) {
		command.clear();
		if (choiceBox.getValue() == "UserName") {
			command.add("UserQuery");
			command.add(prefix);
		} else if (choiceBox.getValue() == "Level") {
			command.add("levelQuery");
			command.add(prefix);
		}
		setChanged();
		notifyObservers(command);
	}

	// a function that create the music from the file
	public void CallOnMusic() {
		String media = new File("resources/music.mp3").toURI().toString();
		mediaPlayer = new MediaPlayer(new Media(media));
		mediaPlayer.play();
		mediaPlayer.setVolume(0.2);
	}

	@Override
	public void displayMessage(String msg) {
		System.out.println(msg);
	}

}