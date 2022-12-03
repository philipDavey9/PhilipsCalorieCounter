package application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.scene.layout.*;

	public class CalorieController {
		Stage applicationStage;

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    void enterInputScreen(ActionEvent inputScreenEvent) {
	    	Scene mainScene = applicationStage.getScene();
	    	HBox inputScreenContainer = new HBox();
	    	Button doneButton = new Button("Done");
	    	doneButton.setOnAction(doneEvent -> applicationStage.setScene(mainScene));
	    	inputScreenContainer.getChildren().add(doneButton);
	    	Scene inputScreenScene = new Scene(inputScreenContainer);
	    	applicationStage.setScene(inputScreenScene);
	    }

	    @FXML
	    void enterMealScreen(ActionEvent mealScreenEvent) {
	    	Scene mainScene = applicationStage.getScene();
	    	HBox mealScreenContainer = new HBox();
	    	Button mealDoneButton = new Button("Done");
	    	mealDoneButton.setOnAction(doneEvent -> applicationStage.setScene(mainScene));
	    	mealScreenContainer.getChildren().add(mealDoneButton);
	    	Scene mealScreenScene = new Scene(mealScreenContainer);
	    	applicationStage.setScene(mealScreenScene);
	    }

	    @FXML
	    void initialize() {

	    }
}
