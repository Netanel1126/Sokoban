package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserPopupController {
	private String userName;
	@FXML
	private Button closeButton;
	@FXML
	private TextField text;

	public void enterUserName(){
		//get user name 
		userName = text.getText();
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();	
	    }
	
	@FXML
	public void exitFile(){
		userName = null;
	    text.clear();
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(Button closeButton) {
		this.closeButton = closeButton;
	}

	public TextField getText() {
		return text;
	}

	public void setText(TextField text) {
		this.text = text;
	}


}
