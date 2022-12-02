package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

	public class CalorieController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    void enterInputScreen(ActionEvent event) {
	    	System.out.println("Input Screen");
	    }

	    @FXML
	    void enterMealScreen(ActionEvent event) {
	    	System.out.println("Meal Screen");
	    }

	    @FXML
	    void initialize() {

	    }
}
