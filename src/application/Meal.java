package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.control.TextField;

public class Meal extends Ingredient{
	private String mealName;
	private ArrayList<Ingredient> ingredients;
	private Double totalCaloriesPerServing;
	
	/**
	 * constructor used to create instance of meal. takes in arraylists that are assumed to be ordered as to have corresponding 
	 * info to each other. 
	 * @param ingredientNamesList
	 * @param ingredientCaloriesList
	 * @param portions
	 * @param portionsUsed
	 * @param newMealText
	 * @param amount
	 */
	public Meal(ArrayList<TextField> ingredientNamesList, ArrayList<Double> ingredientCaloriesList, ArrayList<Double> portions, ArrayList<Double>portionsUsed, TextField newMealText, Double amount) {
		mealName = new String(newMealText.getText());
		//create a new arraylist to add instances of ingredient to
		ArrayList<Ingredient> composure = new ArrayList<Ingredient>(); 
		Double cal = 0.0;
		
		//loop that will create instance of ingredient and calculate the calories for the amount used in a meal. Uses superclass
		//to achieve this
		for(int n = 0; n<ingredientNamesList.size(); n++) {
			// creates instance of ingredient through constructor connected to the super constructor
			Ingredient newIngredient = new Meal(ingredientNamesList.get(n).getText(), ingredientCaloriesList.get(n), portions.get(n));
			//adds each ingredient to the new arraylist
			composure.add(newIngredient);
			//calculates the calories contributed by the ingredient and adds them to the total
			cal += (ingredientCaloriesList.get(n) * portionsUsed.get(n)/ portions.get(n));
			
		} 
		//sets the meals calories per serving and ingredients
		totalCaloriesPerServing = new Double(cal/amount);
		ingredients = new ArrayList<Ingredient>(composure);
	}
	//constructor only used to access super constructor
	public Meal(String names, Double caloriesEach, Double servingSize) {
		super(names,caloriesEach,servingSize);
	}
	/**
	 * Will take any meal and format a string such that the meals info as well as all ingredient info is displayed.
	 * @return this string
	 */
	public String getMeal() {
		String meal = "{"+mealName+","+totalCaloriesPerServing.toString()+",\n";
		for(Ingredient ingredient : this.ingredients) {
			meal += ingredient.getIngredientInfo()+"\n";
		}
		meal += "}\n";
		return meal;
	}
	/**
	 * uses getMeal method to save the output from getMeal to a file specific to the user
	 * @param username
	 * @throws IOException
	 */
	public void saveMeal(String username)throws IOException {
		BufferedWriter writer;
		//will append to file if file is found
		try{writer = new BufferedWriter(new FileWriter(String.format("%s.txt", username), true));}
		catch(IOException IOE) {
			//create new file if file is missing
			writer = new BufferedWriter(new FileWriter(String.format("%s.txt", username)));
		}
		//writes to the file
		writer.write(this.getMeal());
		writer.close();
	}
	/**
	 * finds meal within the file specified by the username and returns its calories per serving
	 * @param nameOfMeal
	 * @param username
	 * @return calories per serving for purpose of using to calculate calorie intake
	 * @throws Error
	 */
	public static Double findMeal(String nameOfMeal, String username) throws Error {
		String line =" ";
		Double num = 0.0;
		boolean found = false;
		//opens file given by username to read. throws error otherwise
		try{BufferedReader reader = new BufferedReader(new FileReader(String.format("%s.txt", username)));
		//sets line equal to the first line in the file
		line = reader.readLine();
		int mark =0;
		//loop to find the beginning of the calories as a string 
		while (line != null && mark==0) {
			int marker = 0;
			int counter = 0;
			//loop to find first ',' which will be the end of the name of the meal and beginning of calories
				for(char C: line.toCharArray()) {
					if(C==',' && marker==0) {
						marker = new Integer(counter);
					}
					counter++;
				}
				//checks to see if the meal name is the same as the meal we are looking for
				if (line.substring(0, marker).equals("{"+nameOfMeal)) {
					found = true;
					//marks this down if true
					mark = marker;
					}
				//reads next line otherwise
				else	line = reader.readLine();
			}
		//if meal is not found than the rest of the code will not work as intended
		if (!found) throw new Error("Could not find meal. Make sure you are spelling it correctly.");
		int end = 0;
		int X = 0;
		//finds the next ',' which will be the end of calories as a string
		for (char c: line.substring(mark+1).toCharArray()) {
			if (c==',') end = mark+X+1;
			X++;
			
		}
		//assuming this all is formated as intended, we can now retrieve the number as a string and convert it to double
		num = Double.parseDouble(line.substring(mark+1, end));
		reader.close();
		
	} 	catch(FileNotFoundException fnfe) {
		throw new Error("File not found, input username correctly please.");
	}
	catch (IOException ioe) {
		throw new Error("Error occured when accessing file.");
	}
		return num;}
	
}


