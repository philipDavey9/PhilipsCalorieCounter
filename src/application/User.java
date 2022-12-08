package application;

import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class User {
	private String username;
	private double targetCalories;
	private double targetExercise;
	
	/**
	 * Constructor for class User that takes inputs specifically from textfields. All validation of user input
	 * is done within the constructor.
	 * @param All parameters are taken from textfields within the Calorie Calculator.
	 * @throws Error, errors thrown come with a message specific to the type of error. This is to allow 
	 * easy handling in outside methods and be given as feedback to the user.
	 */
	public User(TextField User, TextField calories, TextField exercise) throws Error {
		try { String name = new String(User.getText());
		username = name;
		
		//checks if the user already exists in which case we cannot make another instance with the same username
		if (this.checkUserExists(User.getText()) == true) throw new Error("This username is already in use.");
		//checks that the textfield isn't empty
		if (User.getText() == "") throw new NullPointerException();
		//checks that no spaces are contained within the username as to not mess with saveUser and checkUserExist methods.
		for(char c: this.username.toCharArray()) {
			if(Character.isWhitespace(c)) throw new Error("Cannot have spaces in username.");
		}
		}
		catch (IOException IOE) {
			throw new Error("Something is wrong with the file.");
		}
		catch(NullPointerException npe) {
			throw new Error("Please enter a username.");
		} 
		//makes sure the textbox contains a valid number
		try {targetCalories = new Double(calories.getText());
		//checks that the amount of calories isn't unrealistic
		if (targetCalories < 0) throw new Error("Calories cannot be negative.");
		if (targetCalories > 10000 | targetCalories < 1000) throw new Error(String.format("%.0f calories in a day is unreasonable.",targetCalories));
		}
		catch(NumberFormatException nfw) {
			//checks that the textbox contains something
			if ( calories.getText()=="") throw new Error("Please enter your target calories.");
			int counter = 0;
			//checks that all characters are digits within the input and that not to many periods were used.
			for ( char C : calories.getText().toCharArray()) {
				if (C=='.') counter++;
				if (!Character.isDigit(C) && C!='.') throw new Error("Please remove the character "+C+".(In calories)");
			} if (counter>1) throw new Error("Only one decimal point please(calories).");
		}
		//checks that exercise time a valid number and not something unreasonible.
		try {targetExercise = new Double(exercise.getText());
		if (targetExercise < 0) throw new Error("Time cannot be negative.");
		if (targetExercise > 1440) throw new Error("There are only 1440 minutes in a day.");
		} 
		catch(NumberFormatException nfe) {
			//checks that textbox has something in it.
			if (exercise.getText()=="") throw new Error("Please enter a target exercise time.");
			int counter = 0;
			//checks that characters are all digits and that there are not to many periods.
			for( char C : exercise.getText().toCharArray()){
				if(C=='.') counter++;
				if(!Character.isDigit(C) && C!='.') throw new Error("Please remove the character "+C+".(In exercise time)");
			} if(counter>1) throw new Error("Only one decimal point please(exercise).");
		}
	
		} 
	
	/**
	 * Method to save instances of a user to a file. The file will be created by the code and appended to thereafter.	
	 * @throws IOException, all exceptions associated with problems with the file.
	 */
	public void saveUser() throws IOException { 
		//opens writer in file 'Users.txt'
		BufferedWriter writer;
		//tries to append to existing file, if no file exists it will create one
		//appends
		try{writer = new BufferedWriter(new FileWriter("Users.txt", true));}
		catch(IOException IOE) {
			//creates new file
			writer = new BufferedWriter(new FileWriter("Users.txt"));
		}
		//writes to file the instances username targetcalories and targetExercise
		writer.write(this.username+" "+ this.targetCalories+ " "+ this.targetExercise+"\n");
		//close file.
		writer.close();
		
	}
	/**
	 * Method to check if some username is already in use by a previous instance of User. Accomplishes this by searching 
	 * the file that saveUser() saves to.
	 * @param username, any string intended to be used as a username
	 * @return true if instance with same username already exists within the file, false otherwise
	 * @throws IOException, all exceptions associated with file handling.
	 */
	public static boolean checkUserExists(String user) throws IOException{
		//goes to read 'Users.txt' file
		BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
		//set line to first line
		String line = reader.readLine();
		boolean exists = false;
		
		while (line != null) {
			int marker = 0;
			int counter = 0;
			//finds the end of the username by serching for the first whitespace and marking it
				for(char C: line.toCharArray()) {
					if(Character.isWhitespace(C) && marker==0) {
						marker = new Integer(counter);
						
					}
					counter++;
				}
				// checks that the username on the file- which will be from the begining to the first whitespace- is 
				//somewhere on the file
				if (line.substring(0, marker).equals(user)) exists=true;
				//set reader to read next line
			line = reader.readLine();
			}
		reader.close();
		//if not found on the file, the method will return false.
		return exists;
	}
	
	/** 
	 * method to find the users calorie goals. Does this by first finding the username in the file then the second space as it 
	 * will be the end of calories string.
	 * @param user will return this usernames corresponding target calories
	 * @return
	 * @throws Error
	 */
	public static Double getGoalCalories(String user) throws Error {
		String line =" ";
		Double num = 0.0;
		boolean found = false;
		//opens the file for reading
		try{BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
		//sets line equal to the first line
		line = reader.readLine();
		int mark =0;
		//loop to find the username and begining of calories string
		while (line != null && mark==0) {
			int marker = 0;
			int counter = 0;
			//will search for spaces as they are the end of the username
				for(char C: line.toCharArray()) {
					if(Character.isWhitespace(C) && marker==0) {
						marker = new Integer(counter);
					}
					counter++;
				}
				//if true the position of the space will be marked and loop will end
				if (line.substring(0, marker).equals(user)) {
					found = true;
					mark = marker;
					}
				//all other times the next line will be read
				else	line = reader.readLine();
			}reader.close();
			//can't continue with code if username not found
		if (!found) throw new Error("Could not find calories. Make sure you are spelling your username correctly.");
		int end = 0;
		int X = 0;
		//will check to find the next space starting where the last loop left off
		for (char c: line.substring(mark+1).toCharArray()) {
			//once found it will also be marked
			if (Character.isWhitespace(c)) end = mark+X+1;
			X++;
			
		}
		//we now know where the calories string is so we extract it and turn it into a type double
		num = Double.parseDouble(line.substring(mark+1, end));
		
	} 	catch(FileNotFoundException fnfe) {
		throw new Error("File not found, input username correctly please.");
	}
	catch (IOException ioe) {
		throw new Error("Error occured when accessing file.");
	}
		//now the number will be returned
		return num;}

}


