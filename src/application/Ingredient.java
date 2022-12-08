package application;

public class Ingredient {
	private String name;
	private double calories;
	private double portions;
	
	//constructor never used
	public Ingredient() {
	}
	 
	/**
	 * constructor used by Meal class to create new meals. No real purpose outside of that but can be used to 
	 * create new instances of ingredient
	 * @param ingredientName
	 * @param ingredientCalories
	 * @param ingredientPortions
	 */
	public Ingredient(String ingredientName, Double ingredientCalories, Double ingredientPortions) {
		name = new String(ingredientName);
		calories = new Double(ingredientCalories);
		portions = new Double(ingredientPortions);
	}
	/**
	 * formats a string to give information about the ingredient
	 * @return all this information as a string
	 */
	public String getIngredientInfo() {
		String info;
		info = name+","+String.valueOf(calories)+","+String.valueOf(portions);
		return info;
	}
	

}
