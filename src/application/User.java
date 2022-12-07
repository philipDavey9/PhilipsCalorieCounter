package application;

import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class User {
	private String Username;
	private double targetCalories;
	private double targetExercise;
	
	
	public User(TextField User, TextField calories, TextField exercise) throws Error {

		try { String name = new String(User.getText());
		Username = name;
		if (this.checkUserExists(User.getText()) == true) throw new Error("This username is already in use.");
		if (User.getText() == "") throw new NullPointerException();
		for(char c: this.Username.toCharArray()) {
			if(Character.isWhitespace(c)) throw new Error("Cannot have spaces in username."); 
		}
		}
		catch (IOException IOE) {
			throw new Error("Something is wrong with the file.");
		}
		catch(NullPointerException npe) {
			npe.printStackTrace();
			throw new Error("Please enter a username.");
		} 
		try {targetCalories = new Double(calories.getText());
		if (targetCalories < 0) throw new Error("Calories cannot be negative.");
		if (targetCalories > 10000 | targetCalories < 1000) throw new Error(String.format("%.0f calories in a day is unreasonable.",targetCalories));
		}
		catch(NumberFormatException nfw) {
			if ( calories.getText()=="") throw new Error("Please enter your target calories.");
			int counter = 0;
			for ( char C : calories.getText().toCharArray()) {
				if (C=='.') counter++;
				if (!Character.isDigit(C) && C!='.') throw new Error("Please remove the character "+C+".(In calories)");
			} if (counter>1) throw new Error("Only one decimal point please(calories).");
		}
		try {targetExercise = new Double(exercise.getText());
		if (targetExercise < 0) throw new Error("Time cannot be negative.");
		if (targetExercise > 1440) throw new Error("There are only 1440 minutes in a day.");
		} 
		catch(NumberFormatException nfe) {
			if (exercise.getText()=="") throw new Error("Please enter a target exercise time.");
			int counter = 0;
			for( char C : exercise.getText().toCharArray()){
				if(C=='.') counter++;
				if(!Character.isDigit(C) && C!='.') throw new Error("Please remove the character "+C+".(In exercise time)");
			} if(counter>1) throw new Error("Only one decimal point please(exercise).");
		}
	
		} 
	
		
	public void saveUser() throws IOException { 
		BufferedWriter writer;
		try{writer = new BufferedWriter(new FileWriter("Users.txt", true));}
		catch(IOException IOE) {
			writer = new BufferedWriter(new FileWriter("Users.txt"));
		}
		writer.write(this.Username+" "+ this.targetCalories+ " "+ this.targetExercise+"\n");
		writer.close();
		
	}
	
	public static boolean checkUserExists(String username) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
		String line = reader.readLine();
		boolean exists = false;
		while (line != null) {
			int marker = 0;
			int counter = 0;
				for(char C: line.toCharArray()) {
					if(Character.isWhitespace(C) && marker==0) {
						marker = new Integer(counter);
						
					}
					counter++;
				}
				if (line.substring(0, marker).equals(username)) exists=true;
			line = reader.readLine();
			}
		reader.close();
		return exists;
	}
}


