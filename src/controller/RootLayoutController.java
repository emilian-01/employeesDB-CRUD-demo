package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController {

	public void handleExit(ActionEvent actionEvent) {
		System.exit(0);
	}

	public void handleHelp(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Program Information");
		alert.setHeaderText("Program created by Emilian Cikalleshi and Mirel Hoxha");
		alert.setContentText("Java Program with DataBase");
		alert.show();
	}

	@FXML
	private void searchEmployees(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Program Information");
		alert.setHeaderText("All right reserved");
		alert.show();
	}
}
