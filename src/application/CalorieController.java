package application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.io.IOException;
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
	     private TextField ingredientsText;
	     
	     //Creates the scene when 'Create new user' button is pressed.
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
	    	Label exerciseTime = new Label("Enter Target Daily Exercise Time(Minutes):");
	    	TextField exerciseText = new TextField();
	    	exerciseLine.getChildren().addAll(exerciseTime, exerciseText);
	    	
	    	
	    	Button doneButton = new Button("Done");
	    	doneButton.setOnAction(doneEvent -> newUser(mainScene, usernameText, caloriesText, exerciseText));
	    	userScreenContainer.getChildren().addAll(usernameLine, caloriesLine, exerciseLine,errorLabel, doneButton);
	    	Scene inputScreenScene = new Scene(userScreenContainer);
	    	
	    	applicationStage.setScene(inputScreenScene);
	    }Label errorLabel = new Label("");
	     
	    //Creates a new instance of User with input from 'Create new user' scene and saves it. 
	    //Will not allow user to get back to mainScene until all errors are resolved.
	    void newUser(Scene mainScene, TextField usernameText, TextField caloriesText, TextField exerciseText) {
	    	Boolean errorInInput = false;
	    	try {User currentuser = new User(usernameText,caloriesText,exerciseText);
	    	currentuser.saveUser();}
	    	catch(IOException IOE) {
	    		errorLabel.setText("Something with the file went wrong");
	    		errorInInput = true;
	    	}
	    	catch(Error E) {
	    		errorInInput = true;
	    		errorLabel.setText((E.getMessage()));}
	    	
	    	if (!errorInInput) applicationStage.setScene(mainScene);}

	    
	    //Creates scene when 'add new meal' is pressed. 
	    @FXML
	    void enterMealScreen(ActionEvent mealScreenEvent) {
	    	Scene mainScene = applicationStage.getScene();
	    	VBox enterMealContainer = new VBox();
	    	
	    	HBox mealScreenContainer = new HBox();
	    	Label mealName = new Label("Name of Meal:");
	    	TextField newMealText = new TextField();
	    	mealScreenContainer.getChildren().addAll(mealName, newMealText);
	    	enterMealContainer.getChildren().addAll(mealScreenContainer);
	    	
	    	int ingredientNumber = (int) Double.parseDouble(ingredientsText.getText());
	    	int rowsCreated = 0;
	    	ArrayList<TextField> ingredientNamesList = new ArrayList<TextField>();
	    	ArrayList<TextField> ingredientCaloriesList = new ArrayList<TextField>();
	    	ArrayList<TextField> portions = new ArrayList<TextField>();
	    	ArrayList<TextField> portionsUsed = new ArrayList<TextField>();
	    	
	    	while (rowsCreated<ingredientNumber) {
	    		HBox container = new HBox();
	    		TextField name = new TextField("Enter Ingredient Name");
	    		TextField calories = new TextField("Enter Ingredient Calories");
	    		Label per = new Label("per");
	    		TextField measurement = new TextField("In ml or g");
	    		TextField used = new TextField("Amount Used In ml or g");
	    		
	    		ingredientNamesList.add(name);
	    		ingredientCaloriesList.add(calories);
	    		portions.add(measurement);
	    		portionsUsed.add(used);
	    		
	    		container.getChildren().addAll(name, calories, per, measurement, used);
	    		enterMealContainer.getChildren().add(container);
	    		rowsCreated++;
	    	}
	    	
	    	Button mealDoneButton = new Button("Done");
	    	mealDoneButton.setOnAction(doneEvent -> newMeal(mainScene, ingredientNamesList, ingredientCaloriesList, portions, portionsUsed));
	    	enterMealContainer.getChildren().add(mealDoneButton);
	    	Scene mealScreenScene = new Scene(enterMealContainer);
	    	applicationStage.setScene(mealScreenScene);
	    }
	    void newMeal(Scene mainScene, ArrayList<TextField> ingredientNamesList, ArrayList<TextField> ingredientCaloriesList, ArrayList<TextField> portions, ArrayList<TextField> portionsUsed) {
	    	applicationStage.setScene(mainScene);
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
	    
	    //Method to calculate calories when 'calculate' button is pressed. Handles user input from main scene in method.
	    @FXML
	    void calculateCalories() {
	    	boolean errorInCode = false;
	    	try{if (usernameTextfield.getText() == "")throw new Error("Please enter a username.");
	    		else if (User.checkUserExists(usernameTextfield.getText())==false) {
	    		throw new Error("This user does not exist.");
	    	}else usernameErrorLabel.setText("");
	    	}
	    	catch(Error E) {
	    		errorInCode = true;
	    		usernameErrorLabel.setText(E.getMessage());}
	    	catch(IOException IOE) {
	    		errorInCode = true; 
	    		usernameErrorLabel.setText("Something went wrong with the file");
	    	}
	    }
	    
	    /**
	     * Method to verify user input as an applicable number.
	     * @param userInput, the textfield to be verified
	     * @param period, ideally 0 or 1 as to effectively return integers and decimal numbers respectively.
	     * @return will return the number inputed by user, or if an error is detected, a 0
	     * @throws Error
	     */
	    public double checkUserTextbox(TextField userInput, int period) throws Error {
	    	double num = 0;
	    	try {num = Double.parseDouble(userInput.getText());
	    	int periodCount = 0;
	    	for(char C: userInput.getText().toCharArray()) {
	    		if (C=='.') periodCount++;} 
	    	if (periodCount > period) {
	    		if (period == 0)throw new Error("No decimal points please.");
	    		else throw new Error("Only one decimal allowed.");
	    	}}
	    	catch(NullPointerException npe) {
	    		throw new Error("Please enter a number.");
	    	}
	    	catch(NumberFormatException nfe) {
	    		int counter = 0;
	    		for(char c : userInput.getText().toCharArray()) {
	    			if(!Character.isDigit(c) & c!='.') throw new Error("Please remove the character "+c+".");
	    			if(c=='.') counter++;
	    			if (counter>1) throw new Error("Only one decimal allowed.");
	    		}
	    	}
	    	return num;		}

	   
	    
}
