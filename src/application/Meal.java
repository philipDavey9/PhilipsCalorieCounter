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
	
	public Meal(ArrayList<TextField> ingredientNamesList, ArrayList<Double> ingredientCaloriesList, ArrayList<Double> portions, ArrayList<Double>portionsUsed, TextField newMealText, Double amount) {
		mealName = new String(newMealText.getText());
		ArrayList<Ingredient> composure = new ArrayList<Ingredient>(); 
		Double cal = 0.0;
		
		for(int n = 0; n<ingredientNamesList.size(); n++) {
			Ingredient newIngredient = new Meal(ingredientNamesList.get(n).getText(), ingredientCaloriesList.get(n), portions.get(n));
			composure.add(newIngredient);
			
			cal += (ingredientCaloriesList.get(n) * portionsUsed.get(n)/ portions.get(n));
			
		} 
		totalCaloriesPerServing = cal/amount;
		ingredients = composure;
	}
	
	public Meal(String names, Double caloriesEach, Double servingSize) {
		super(names,caloriesEach,servingSize);
	}
	
	public String getMeal() {
		String meal = "{"+mealName+","+totalCaloriesPerServing.toString()+",\n";
		for(Ingredient ingredient : this.ingredients) {
			meal += ingredient.getIngredientInfo()+"\n";
		}
		meal += "}\n";
		return meal;
	}
	
	public void saveMeal(String username)throws IOException {
		BufferedWriter writer;
		try{writer = new BufferedWriter(new FileWriter(String.format("%s.txt", username), true));}
		catch(IOException IOE) {
			writer = new BufferedWriter(new FileWriter(String.format("%s.txt", username)));
		}
		writer.write(this.getMeal());
		writer.close();
	}
	public static Double findMeal(String nameOfMeal, String username) throws Error {
		String line =" ";
		Double num = 0.0;
		boolean found = false;
		try{BufferedReader reader = new BufferedReader(new FileReader(String.format("%s.txt", username)));
		line = reader.readLine();
		int mark =0;
		while (line != null && mark==0) {
			int marker = 0;
			int counter = 0;
				for(char C: line.toCharArray()) {
					if(C==',' && marker==0) {
						marker = new Integer(counter);
					}
					counter++;
				}
				if (line.substring(0, marker).equals("{"+nameOfMeal)) {
					found = true;
					mark = marker;
					}
				else	line = reader.readLine();
			}
		if (!found) throw new Error("Could not find meal. Make sure you are spelling it correctly.");
		int end = 0;
		int X = 0;
		for (char c: line.substring(mark+1).toCharArray()) {
			if (c==',') end = mark+X+1;
			X++;
			
		}
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


