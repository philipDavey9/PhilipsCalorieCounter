package application;

public class Ingredient extends User {
	private String name;
	private double calories;
	private double portions;
	
	public Ingredient() {
		
	}
	
	public Ingredient(String ingredientName, Double ingredientCalories, Double ingredientPortions) {
		name = new String(ingredientName);
		calories = new Double(ingredientCalories);
		portions = new Double(ingredientPortions);
	}
	public String getIngredientInfo() {
		String info;
		info = name+","+String.valueOf(calories)+","+String.valueOf(portions);
		return info;
	}
	

}
