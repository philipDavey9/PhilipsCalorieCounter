package application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.scene.layout.*;

	public class CalorieController {
		Stage applicationStage;

	    @FXML
	    private TextField usernameTextfield;
	    
	     @FXML
	     private Label usernameErrorLabel;
	     
	     @FXML
	     private Label mealsErrorLabel;
	     
	     @FXML
	     private Label othersErrorLabel;
	     
	     @FXML
	     private Label exerciseErrorLabel;
	     
	     @FXML 
	     private TextField exerciseTextfield;
	     
	     @FXML 
	     private Slider exerciseIntensity;
	     
	     @FXML 
	     private ChoiceBox<Integer> mealNumber;
	     
	     @FXML 
	     private ChoiceBox<Integer> otherNumber;

	    @FXML
	    void enterInputScreen(ActionEvent inputScreenEvent) {
	    	Scene mainScene = applicationStage.getScene();
	    	VBox userScreenContainer = new VBox();
	    	
	    	HBox usernameLine = new HBox();
	    	Label username = new Label("Enter New Username:");
	    	TextField usernameText = new TextField();
	    	usernameLine.getChildren().addAll(username,usernameText);
	    	
	    	HBox caloriesLine = new HBox();
	    	Label calories = new Label("Enter Target Daily Calories:");
	    	TextField caloriesText = new TextField();
	    	caloriesLine.getChildren().addAll(calories, caloriesText);
	    	
	    	HBox exerciseLine = new HBox();
	    	Label exerciseTime = new Label("Enter Target Daily Exercise Time:");
	    	TextField exerciseText = new TextField();
	    	exerciseLine.getChildren().addAll(exerciseTime, exerciseText);
	    	
	    	Button doneButton = new Button("Done");
	    	doneButton.setOnAction(doneEvent -> newUser(mainScene, usernameText, caloriesText, exerciseText));
	    	userScreenContainer.getChildren().addAll(usernameLine, caloriesLine, exerciseLine, doneButton);
	    	Scene inputScreenScene = new Scene(userScreenContainer);
	    	
	    	applicationStage.setScene(inputScreenScene);
	    }
	    void newUser(Scene mainScene, TextField usernameText, TextField caloriesText, TextField exerciseText) {
	    	applicationStage.setScene(mainScene);
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
	    void enterOthers(ActionEvent othersEvent) {
Scene mainScene = applicationStage.getScene();
	    	
	    	int numberOfOtherItems = otherNumber.getValue();
	    	int rowsCreated = 0;
	    	VBox othersContainer = new VBox();
	    	ArrayList<TextField> othersList = new ArrayList<TextField>();
	    	ArrayList<TextField> othersPortion = new ArrayList<TextField>();
	    	
	    	while(rowsCreated < numberOfOtherItems) {
	    		
	    	HBox enterScreenContainer = new HBox();
	    	Label mealLabel = new Label("Enter Other Item:");
	    	TextField mealText = new TextField();
	    	Label mealLabel2 = new Label("Serving Size:");
	    	TextField mealText2 = new TextField();
	    	Label lastLabel2 = new Label("Servings");
	    	
	    	othersList.add(mealText);
	    	othersPortion.add(mealText2);
	    	
	    	enterScreenContainer.getChildren().addAll(mealLabel, mealText, mealLabel2, mealText2, lastLabel2);
	    	rowsCreated++;
	    	
	    	othersContainer.getChildren().add(enterScreenContainer);
	    	}
	    	
	    	Button enterDoneButton = new Button("Done");
	    	enterDoneButton.setOnAction(doneEvent -> addOtherCalories(mainScene, othersList, othersPortion));
	    	othersContainer.getChildren().add(enterDoneButton);
	    	Scene enterScreenScene = new Scene(othersContainer);
	    	applicationStage.setScene(enterScreenScene);
	    }
	    void addOtherCalories(Scene mainScene, ArrayList<TextField> othersList, ArrayList<TextField> othersPortion) {
	    	applicationStage.setScene(mainScene);
	    }
	    
	    @FXML 
	    void enterMeals(ActionEvent enterEvent) {
	    	Scene mainScene = applicationStage.getScene();
	    	
	    	int numberOfMeals = mealNumber.getValue();
	    	int rowsCreated = 0;
	    	VBox mealContainer = new VBox();
	    	ArrayList<TextField> mealList = new ArrayList<TextField>();
	    	ArrayList<TextField> mealPortion = new ArrayList<TextField>();
	    	
	    	while(rowsCreated < numberOfMeals) {
	    		
	    	HBox enterScreenContainer = new HBox();
	    	Label mealLabel = new Label("Enter Meal:");
	    	TextField mealText = new TextField();
	    	Label mealLabel2 = new Label("Serving Size:");
	    	TextField mealText2 = new TextField();
	    	Label lastLabel2 = new Label("Servings");
	    	
	    	mealList.add(mealText);
	    	mealPortion.add(mealText2);
	    	
	    	enterScreenContainer.getChildren().addAll(mealLabel, mealText, mealLabel2, mealText2, lastLabel2);
	    	rowsCreated++;
	    	
	    	mealContainer.getChildren().add(enterScreenContainer);
	    	}
	    	
	    	Button enterDoneButton = new Button("Done");
	    	enterDoneButton.setOnAction(doneEvent -> addMealCalories(mainScene, mealList, mealPortion));
	    	mealContainer.getChildren().add(enterDoneButton);
	    	Scene enterScreenScene = new Scene(mealContainer);
	    	applicationStage.setScene(enterScreenScene);
	    }
	    void addMealCalories(Scene mainScene, ArrayList<TextField> mealList, ArrayList<TextField> mealPortion) {
	    	applicationStage.setScene(mainScene);
	    }

	   
	    
}
