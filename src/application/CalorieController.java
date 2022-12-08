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
	     private Label exerciseErrorLabel;
	     
	     @FXML 
	     private TextField exerciseTextfield;
	     
	     @FXML 
	     private Slider exerciseIntensity;
	     
	     @FXML 
	     private ChoiceBox<Integer> mealNumber;
	     
	     @FXML 
	     private TextField ingredientsText;
	     
	     @FXML 
	     private Label ingredientNumError;
	     
	     @FXML
	     private Label caloriesDisplay;
	     
	     @FXML 
	     private Label finalMessage;
	     
	     /**
	      * method called when 'Create new user' is pressed. Will create a scene to allow the user to input information
	      * that will go into making a new user. Will not allow user to leave scene until a valid user is created.
	      * @param inputScreenEvent
	      */
	     @FXML
	  	    void enterInputScreen(ActionEvent inputScreenEvent) {
	    	 //sets mainscene to the main application scene for later use
	    	Scene mainScene = applicationStage.getScene();
	    	
	    	VBox userScreenContainer = new VBox();
	    	
	    	//Next three blocks of code all create an input for some element a user.
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
	    	
	    	//Creates the button in the new scene that will activate the newUser method and take us back to the mainscene.
	    	Button doneButton = new Button("Done");
	    	doneButton.setOnAction(doneEvent -> newUser(mainScene, usernameText, caloriesText, exerciseText));
	    	userScreenContainer.getChildren().addAll(usernameLine, caloriesLine, exerciseLine,errorLabel, doneButton);
	    	Scene inputScreenScene = new Scene(userScreenContainer);
	    	
	    	//With the new scene created, the method will now take the user to said scene.
	    	applicationStage.setScene(inputScreenScene);
	    }//error label to be used within the scene created above by enterInputScreen but can be altered outside that method by newUser.
	     Label errorLabel = new Label("");
	     
	    /**
	     * Creates a new instance of User with input from 'Create new user' scene and saves it. 
	     *Will not allow user to get back to mainScene until all errors are resolved.
	     * @param mainScene Taken to allow user to return to main scene.
	     * @param usernameText Text to become username
	     * @param caloriesText text to become target calories
	     * @param exerciseText text become target exercise time
	     */ 
	    void newUser(Scene mainScene, TextField usernameText, TextField caloriesText, TextField exerciseText) {
	    	//If no errors in input, this variable should remain unchanged and allow users to return to the mainscene.
	    	Boolean errorInInput = false;
	    	//Creates new user through a constructor.
	    	try {User currentuser = new User(usernameText,caloriesText,exerciseText);
	    	//saves user to a file for any future use.
	    	currentuser.saveUser();}
	    	catch(IOException IOE) {
	    		errorLabel.setText("Something with the file went wrong");
	    		errorInInput = true;
	    	}
	    	catch(Error E) {
	    		errorInInput = true;
	    		errorLabel.setText((E.getMessage()));}
	    	
	    	if (!errorInInput) applicationStage.setScene(mainScene);}

	    
	    /**
	     * method used to create meal input screen and send users to it. If errors are detected the method will not allow 
	     * users to enter the newly created scene until errors are corrected. This is where users will create new meals to be 
	     * saved and used when needed.
	     * @param mealScreenEvent
	     */
	    @FXML
	    void enterMealScreen(ActionEvent mealScreenEvent) {
	    	//set up mainScene for later use
	    	Scene mainScene = applicationStage.getScene();
	    	
	    	//main container for holding all components of interface.
	    	VBox enterMealContainer = new VBox();
	    	
	    	//creates HBox to get 'name of meal' from users and adds it to the container.
	    	HBox mealScreenContainer = new HBox();
	    	Label mealName = new Label("Name of Meal:");
	    	TextField newMealText = new TextField();
	    	mealScreenContainer.getChildren().addAll(mealName, newMealText);
	    	
	    	//Initializing variables.
	    	int ingredientNumber = 0;
	    	boolean errorDetected = false;
	    	//using the checkUserTextbox method(see at bottom of file), get the number of ingredients in some new meal.
	    	try{ ingredientNumber = (int)checkUserTextbox(ingredientsText, 0);
	    	//check for a valid username to save to using the checkUserExists method.(in User class)
	    	if (usernameTextfield.getText() == "")throw new Error("Please enter a username.");
    		else if (User.checkUserExists(usernameTextfield.getText())==false) {
    		throw new Error("This user does not exist.");
    		//reset label to empty if no errors are detected.
    	}else ingredientNumError.setText("");}
	    	
	    	//catch any errors that may have been found above.
    	catch(IOException IOE) {
    		errorDetected = true; 
    		ingredientNumError.setText("Something went wrong with the file");
    	}
	    	catch(Error E) {
	    		ingredientNumError.setText(E.getMessage());
	    		errorDetected = true;
	    	}
	    	int rowsCreated = 0;
	    	//create arraylists to store all user input.
	    	ArrayList<TextField> ingredientNamesList = new ArrayList<TextField>();
	    	ArrayList<TextField> ingredientCaloriesList = new ArrayList<TextField>();
	    	ArrayList<TextField> portions = new ArrayList<TextField>();
	    	ArrayList<TextField> portionsUsed = new ArrayList<TextField>();
	    	
	    	//labels indicating what to enter in every column
	    	Label nameOfIngredient = new Label("Ingredient Name: 					");
	    	Label caloriesOfIngredient = new Label("Calories per Serving:						");
	    	Label amountUsed = new Label("	Amount Used:");
	    	HBox infoContainer = new HBox();
	    	//add all created components to main container.
	    	infoContainer.getChildren().addAll(nameOfIngredient,caloriesOfIngredient,amountUsed);
	    	enterMealContainer.getChildren().addAll(mealScreenContainer, infoContainer);
	    	
	    	//loop to create a row for every ingredient to be entered along with its information.
	    	while (rowsCreated<ingredientNumber) {
	    		HBox container = new HBox();
	    		TextField name = new TextField();
	    		TextField calories = new TextField();
	    		Label per = new Label("per");
	    		TextField measurement = new TextField();
	    		TextField used = new TextField();
	    		
	    		//adding each component to its respective list for later use.
	    		ingredientNamesList.add(name);
	    		ingredientCaloriesList.add(calories);
	    		portions.add(measurement);
	    		portionsUsed.add(used);
	    		
	    		//adding component to container.
	    		container.getChildren().addAll(name, calories, per, measurement, used);
	    		enterMealContainer.getChildren().add(container);
	    		rowsCreated++;
	    	}
	    	//Textbox to input servings made.
	    	Label servings = new Label("Servings made:");
	    	TextField enterServings = new TextField();
	    	HBox serve = new HBox();
	    	serve.getChildren().addAll(servings, enterServings);
	    	
	    	//creating button to allow users to leave scene and send all information to the newMeal method.
	    	Button mealDoneButton = new Button("Done");
	    	mealDoneButton.setOnAction(doneEvent -> newMeal(mainScene, ingredientNamesList, ingredientCaloriesList, portions, portionsUsed, newMealText, enterServings));
	    	
	    	//add remaining components to the container and container to scene
	    	enterMealContainer.getChildren().addAll(serve, mealDoneButton, errorInNewMeal);
	    	Scene mealScreenScene = new Scene(enterMealContainer);
	    	
	    	// if any errors are detected in the method, the new scene won't be displayed
	    	if (!errorDetected) {
	    		applicationStage.setScene(mealScreenScene);
	    		ingredientNumError.setText(""); 
	    	}
	    	//label in create meal meathod to be accessed in newMeal method.
	    }Label errorInNewMeal = new Label("");
	    /**
	     * Method to create and save a new meal to a specific user based on input from user. Accessed through button in scene 
	     * seperate from mainscene so will not allow user to return to mainscene until errors are resolved. All lists taken as 
	     * parameters are ordered such that index numbers match between corresponding inputs.
	     * @param mainScene taken to allow users to return to mainscene. 
	     * @param ingredientNamesList list of all ingredient names inputed.
	     * @param ingredientCaloriesList list of all calorie amounts for each ingredient.
	     * @param portions lists of the portions that the calories are based off of
	     * @param portionsUsed list of the amount used for every ingredient. 
	     * @param newMealText the name of the meal.
	     * @param enterServings servings this meal makes.
	     */
	    void newMeal(Scene mainScene, ArrayList<TextField> ingredientNamesList, ArrayList<TextField> ingredientCaloriesList, ArrayList<TextField> portions, ArrayList<TextField> portionsUsed, TextField newMealText, TextField enterServings) {
	    	//lists for initialized values to be stored.
	    	ArrayList<Double> caloriesList = new ArrayList<Double>();
	    	ArrayList<Double> perPortion = new ArrayList<Double>();
	    	ArrayList<Double> portionsUsedPer = new ArrayList<Double>();
	    	boolean error = false;
	    	
	    	//the next three for loops all use the chekcUsertextbox method to validate users input then add the values to 
	    	//corresponding arraylist.
	    	for(TextField num: ingredientCaloriesList) {
	    		try { Double ex = checkUserTextbox(num, 1);
	    		caloriesList.add(ex);}
	    		catch(Error E) {
	    			error = true;
	    			errorInNewMeal.setText(E.getMessage()+ " Error within calories input.");
	    		}
	    	}
	    	for (TextField num2: portions) {
	    		try {double exe = checkUserTextbox(num2, 1);
	    		perPortion.add(exe);}
	    		catch(Error E) {
	    			error = true;
	    			errorInNewMeal.setText(E.getMessage()+ " Error within portion sizing.");
	    		}
	    	}
	    	for (TextField num3 : portionsUsed) {
	    		try {double exa = checkUserTextbox(num3, 1);
	    		portionsUsedPer.add(exa);}
	    		catch(Error E) {
	    			error = true;
	    			errorInNewMeal.setText(E.getMessage()+ " Error within amount used.");
	    		}
	    	}
	    	
	    	//checks that the name of meal is not empty.
	    	if (newMealText.getText() =="") {
	    		error = true;
	    		errorInNewMeal.setText("Please enter a meal name.");
	    	}
	    	// checks that the name of ingredients are not empty.
	    	for (TextField name: ingredientNamesList) {
	    		if (name.getText() == "") {
	    			error = true;
	    			errorInNewMeal.setText("Please enter all ingredient names.");
	    		}
	    	}
	    	Double amount = 0.0;
	    	
	    	//checks that the serving amounts are valid for the meal
	    	try {amount = checkUserTextbox(enterServings,1);}
	    	catch(Error E) {
	    		error = true;
	    		errorInNewMeal.setText(E.getMessage()+" Error within serving size.");
	    	}
	    	//uses meal constructor to create the meal as an instance
	    	Meal newMeal = new Meal(ingredientNamesList, caloriesList, perPortion, portionsUsedPer, newMealText, amount);
	    
	    	//attempts to save the new instance of meal through the saveMeal method(in Meal class)
	    	try{newMeal.saveMeal(usernameTextfield.getText());}		
	    	catch(IOException ioe) {
	    		error = true;
	    		errorInNewMeal.setText("Error within file.");}
	    	
	    	//if no errors are found, method will set the scene to the original scene.
	    	if (!error) applicationStage.setScene(mainScene);
	    }
	    
	    /**
	     * Method that creates and opens scene to input meals(ones that have already been saved) and calculates calories across them
	     * @param enterEvent 
	     */
	    @FXML 
	    void enterMeals(ActionEvent enterEvent) {
	    	Scene mainScene = applicationStage.getScene();
	    	
	    	//setting the number of meals to the number selected in the choice box beside.
	    	int numberOfMeals = mealNumber.getValue();
	    	int rowsCreated = 0;
	    	
	    	//creates container for all components to be in.
	    	VBox mealContainer = new VBox();
	    	//arraylists for name of meals and portion sizes to be stored.
	    	ArrayList<TextField> mealList = new ArrayList<TextField>();
	    	ArrayList<TextField> mealPortion = new ArrayList<TextField>();
	    	
	    	//While loop to create a row for every meal.
	    	while(rowsCreated < numberOfMeals) {
	    		
	    	//creating display
	    	HBox enterScreenContainer = new HBox();
	    	Label mealLabel = new Label("Enter Meal:");
	    	TextField mealText = new TextField();
	    	Label mealLabel2 = new Label("Serving Size:");
	    	TextField mealText2 = new TextField();
	    	Label lastLabel2 = new Label("Servings");
	    	
	    	//adding inputed information to corresponding lists.
	    	mealList.add(mealText);
	    	mealPortion.add(mealText2);
	    	
	    	//add display to container
	    	enterScreenContainer.getChildren().addAll(mealLabel, mealText, mealLabel2, mealText2, lastLabel2);
	    	rowsCreated++;
	    	
	    	mealContainer.getChildren().add(enterScreenContainer);
	    	}
	    	
	    	//Creates button to be used to activate addMealCalories method.
	    	Button enterDoneButton = new Button("Done");
	    	enterDoneButton.setOnAction(doneEvent -> addMealCalories(mainScene, mealList, mealPortion));
	    	mealContainer.getChildren().addAll(enterDoneButton,newLabelError);
	    	Scene enterScreenScene = new Scene(mealContainer);
	    	//set scene to scene created 
	    	applicationStage.setScene(enterScreenScene);
	    }//label in enterMeal display, to be accessed by addMealCalories method
	    Label newLabelError = new Label("");
	    //track total calories outside the method
	    double totalCalories =0;
	    /**
	     * method activated by button in enterMeal scene. Will add calculate the calories of the meals inputed 
	     * @param mainScene to allow users to return to mainscene
	     * @param mealList list of all meal names
	     * @param mealPortion list of portion of meal consumed
	     */
	    void addMealCalories(Scene mainScene, ArrayList<TextField> mealList, ArrayList<TextField> mealPortion) {
	    	double total = 0;
	    	boolean error = false;
	    	
	    	//checks that the inputed meal portions are all valid numbers.
	    	for (TextField list: mealPortion) {
	    		try {checkUserTextbox(list,1);}
	    		catch (Error E) {
	    			error = true;
	    			newLabelError.setText(E.getMessage());
	    		}}
	    	//will calculate the total calories by using findMeal method(in Meal class) to access information about the meal 
	    	// and adding them to the total calorie count one by one.
	    	for(int n=0; n< mealList.size(); n++) {
	    		String name = mealList.get(n).getText();
	    		try {double num = Meal.findMeal(name,usernameTextfield.getText());
	    		total += num * Double.parseDouble(mealPortion.get(n).getText());
	    		//set the totalCalories to the calories calculated.
	    		totalCalories = total;}
	    		catch(Error E) {
	    			error = true;
	    			newLabelError.setText(E.getMessage());
	    		}
	    		// if an error is found, scene wont change back to mainscene. 
	    	}if (!error) {
	    		applicationStage.setScene(mainScene);
	    		caloriesDisplay.setText(String.format("Total calories consumed: %.0f", total));
	    	}
	    }
	    
	    /**
	     * method used to calculate net calories with both consumption and the amount burned.
	     * uses methods from the User class and Meal class to achieve this.
	     */
	    @FXML
	    void calculateCalories() {
	    	//Checks for a valid username and throws error otherwise.
	    	try{if (usernameTextfield.getText() == "")throw new Error("Please enter a username.");
	    		else if (User.checkUserExists(usernameTextfield.getText())==false) {
	    		throw new Error("This user does not exist.");
	    	}else usernameErrorLabel.setText("");
	    	}
	    	catch(Error E) {
	    		
	    		usernameErrorLabel.setText(E.getMessage());}
	    	catch(IOException IOE) {
	    		 
	    		usernameErrorLabel.setText("Something went wrong with the file");
	    	}
	    	
	    	double exerciseMins = 0;
	    	exerciseErrorLabel.setText("");
	    	try {exerciseMins = checkUserTextbox(exerciseTextfield, 1);}
	    	catch (Error E) {
	    		exerciseErrorLabel.setText(E.getMessage());
	    	}
	    	//calculates calories burned by exercise by taking the intensity of the exercise, dividing it by 2.5 and 
	    	//multiplying the result with three times the exercise time.
	    	double intensity = exerciseIntensity.getValue() / 2.5;
	    	 double caloriesBurned = exerciseMins*3*intensity;
	    	
	    	 //net calories taken by subtracting the calories burned from the calories consumed.
	    	 double netCalories = totalCalories - caloriesBurned;
	    	 
	    	 double goals = 0;
	    	 //gets the target calories of the user through the method getGoalCalories
	    	 try{goals = User.getGoalCalories(usernameTextfield.getText());}
	    	 catch (Error E) {
	    		 finalMessage.setText(E.getMessage());
	    	 }
	    	 // prints a final message on how your net calories compared to your goals.
	    		if (goals>netCalories) finalMessage.setText(String.format("You were %.0f calories away from achieving your goal(Need more calories to achieve your goal).", goals-netCalories));
	    		else if (netCalories>goals) finalMessage.setText(String.format("You exceded your goal by %.0f calories(Need less calories to achieve your goal).", netCalories-goals));
	    		else if (goals==netCalories & netCalories!=0) finalMessage.setText("You matched your goals perfectly! Good job!");
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
	    	//checks that the textbox isn't empty.
	    	try {if (userInput.getText() == "") throw new NullPointerException();
	    		num = Double.parseDouble(userInput.getText());
	    	
	    	// checks that the number isn't negative
	    	if (num<0) throw new Error("No negative numbers please.");
	    	int periodCount = 0;
	    	// checks that the number doesn't contain more decimals than it should
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
	    		//checks that the textbox is a number and not something else
	    		for(char c : userInput.getText().toCharArray()) {
	    			if(!Character.isDigit(c) & c!='.') throw new Error("Please remove the character "+c+".");
	    			if(c=='.') counter++;
	    			if (counter>1) {
	    				if (period !=0)throw new Error("Only one decimal allowed.");
	    				else throw new Error("No decimal points please.");}
	    		}
	    	}
	    	//returns the inputed textfield as a double.
	    	return num;		}

	   
	    
}
