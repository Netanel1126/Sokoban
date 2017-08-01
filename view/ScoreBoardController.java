package view;

import common.Hibernate_UsersAndQuery;
import common.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ScoreBoardController {
	private ObservableList<User> data;
	private String username;
	private int time;
	private int steps;
	private String fullLevel_name = "";
	private String[] level_name;
	private Hibernate_UsersAndQuery query;
	private String usernameToSend = null;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn usernameColumn, stepsColumn, timeColumn, levelColumn, buttonColumn;
	@FXML
	private TableView<User> table;

	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addToColumn() {

		table = new TableView<User>();
		table.setEditable(true);

		usernameColumn = new TableColumn("UserName");
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("user_name"));

		stepsColumn = new TableColumn("Steps");
		stepsColumn.setCellValueFactory(new PropertyValueFactory<>("steps"));

		timeColumn = new TableColumn("Time");
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

		levelColumn = new TableColumn("Level");
		levelColumn.setCellValueFactory(new PropertyValueFactory<>("level_name"));

		data = FXCollections.observableArrayList();
		for (int i = 0;i < query.getQuerylist().size(); i++) {
			username = query.getQuerylist().get(i).getUser_name();
			time = query.getQuerylist().get(i).getTime();
			steps = query.getQuerylist().get(i).getSteps();
			fullLevel_name = query.getQuerylist().get(i).getLevel_name();
			
			level_name = fullLevel_name.split("\\\\");
			fullLevel_name = level_name[level_name.length -1].substring(0, level_name[level_name.length -1].length() - 4);
			
			data.add(new User(username, time, steps, fullLevel_name));
		}

		addButtonCol();
		table.setItems(data);
		table.getColumns().addAll(usernameColumn, stepsColumn, timeColumn, levelColumn, buttonColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addButtonCol() {

		buttonColumn = new TableColumn("More Info");
		buttonColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

		Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = new Callback<TableColumn<User, String>, TableCell<User, String>>() {
			@Override
			public TableCell call(final TableColumn<User, String> param) {
				final TableCell<User, String> cell = new TableCell<User, String>() {
					final Button moreInfoBtn = new Button("More Info");

					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							moreInfoBtn.setOnAction((ActionEvent event) -> {
								User user = getTable().getItems().get(getIndex());
								usernameToSend = user.getUser_name();
								Stage stage = (Stage) moreInfoBtn.getScene().getWindow();
								stage.close();
							});
							setGraphic(moreInfoBtn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		buttonColumn.setCellFactory(cellFactory);
	}

	public ObservableList<User> getData() {
		return data;
	}

	public void setData(ObservableList<User> data) {
		this.data = data;
	}

	public TableView<User> getTable() {
		return table;
	}

	public void setTable(TableView<User> table) {
		this.table = table;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public Hibernate_UsersAndQuery getQuery() {
		return query;
	}

	public void setQuery(Hibernate_UsersAndQuery query) {
		this.query = query;
	}

	@SuppressWarnings("rawtypes")
	public TableColumn getUsernameColumn() {
		return usernameColumn;
	}

	@SuppressWarnings("rawtypes")
	public void setUsernameColumn(TableColumn usernameColumn) {
		this.usernameColumn = usernameColumn;
	}

	@SuppressWarnings("rawtypes")
	public TableColumn getStepsColumn() {
		return stepsColumn;
	}

	public void setStepsColumn(TableColumn stepsColumn) {
		this.stepsColumn = stepsColumn;
	}

	public TableColumn getTimeColumn() {
		return timeColumn;
	}

	public void setTimeColumn(TableColumn timeColumn) {
		this.timeColumn = timeColumn;
	}

	public TableColumn getLevelColumn() {
		return levelColumn;
	}

	public void setLevelColumn(TableColumn levelColumn) {
		this.levelColumn = levelColumn;
	}

	public String getUsernameToSend() {
		return usernameToSend;
	}

	public void setUsernameToSend(String usernameToSend) {
		this.usernameToSend = usernameToSend;
	}

	public TableColumn getButtonColumn() {
		return buttonColumn;
	}

	public void setButtonColumn(TableColumn buttonColumn) {
		this.buttonColumn = buttonColumn;
	}

}
