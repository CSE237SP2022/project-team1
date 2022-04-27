package promptClass;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dayStatistics.dayStatistics;
import foodCSV.FoodCSV;
import foodClass.Food;
import mealClass.Meal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

public class Prompt {
	
	static Scanner reader = new Scanner(System.in);
	static ArrayList<Food> storedFood = new ArrayList<>();
	static List<Double> statsTracker = new ArrayList<>();
	
	
	public void run() {

		String optionMenuSelection = "";
		FoodCSV database = new FoodCSV();
		String fileName = "database(1).csv";
		database.generateFoodCSV(fileName);
		

		viewOptionMenu(optionMenuSelection, database);

	}

	private void viewOptionMenu(String optionMenuSelection, FoodCSV database) {
		try (Scanner reader = new Scanner(System.in)) {
			while (!optionMenuSelection.equalsIgnoreCase("quit") && !optionMenuSelection.equals("4")) {

				System.out.println("\nWelcome to Calorie Counter! Please enter a number from the options below:"
						+ "\n1. Enter a food\n2. Check food stats\n3. Create a meal\n4. Quit ");

				optionMenuSelection = chooseFromOptions(database);
			}
		}
		System.out.println("Tracker ended.");
	}

	private String chooseFromOptions(FoodCSV database) {
		String optionMenuSelection;
		optionMenuSelection = reader.nextLine();

		switch (optionMenuSelection) {

		case "1":

			createFood(database);
			break;

		case "2":

			statisticsView();

			break;

		case "3":

			createMeal(database);

			break;
		}
		return optionMenuSelection;
	}

	private void statisticsView() {
		String optionMenuSelection2 = chooseFromStatsOptions();
		switch (optionMenuSelection2) {

		case "1":

			checkFoodStats();
			break;

		case "2":

			double totalStats = sum(statsTracker);
			System.out.println(totalStats);
			break;
		}
	}

	private String chooseFromStatsOptions() {
		String optionMenuSelection2;
		System.out.println("\n1. Enter the name of the food to view nutrition statistics\n2. Check total calories");

		optionMenuSelection2 = reader.nextLine();
		return optionMenuSelection2;
	}

	private void createMeal(FoodCSV database) {
		boolean doneEnteringFood = false;

		Meal trackedMeal = new Meal();

		System.out.println("Enter a name for your meal: ");
		String mealName = reader.nextLine();

		trackedMeal.setName(mealName);
		enterFoodForMeal(doneEnteringFood, trackedMeal, database);

		System.out.println("Meal complete");
	}

	private void enterFoodForMeal(boolean doneEnteringFood, Meal trackedMeal, FoodCSV database) {

		while (doneEnteringFood != true) {

			enterFood(trackedMeal, database);

			System.out.println("Would you like to enter another food? Y/N");

			String anotherFood = reader.nextLine();

			if (anotherFood.equalsIgnoreCase("N") || anotherFood.equalsIgnoreCase("no")) {
				doneEnteringFood = true;
			}
		}

	}

	private void enterFood(Meal trackedMeal,FoodCSV database) {
		System.out.println("Enter a food to add to a meal.");

		createFood(database);
		trackedMeal.addFood(storedFood.get(storedFood.size() - 1));
	}

	private void checkFoodStats() {
		System.out.println("Enter the food name now: ");
		String foodToView = reader.nextLine();

		for (Food f : storedFood) {

			if (f.getName().equalsIgnoreCase(foodToView)) {
				System.out.println(f.toString());
			}
		}
	}
	public void createFood(FoodCSV database) {

			ArrayList<String> foodData = new ArrayList<>();
			dayStatistics newStats = new dayStatistics();
			String foodName = enterName(foodData, newStats);
			
			if(checkDuplicate(foodName) != true) {
				try {
					sanitizeCaloriesInput();
					double caloriesInFood = reader.nextDouble();
					double totalCals = updateTotalCals(foodData, newStats, caloriesInFood);
	
					sanitizeCarbsInput();
					double carbsInFood = reader.nextDouble();
					updateTotalCarbs(foodData, newStats, carbsInFood);
					
					sanitizeFatInput();
					double fatInFood = reader.nextDouble();
					updateTotalFat(foodData, newStats, fatInFood);
					
					sanitizeProteinInput();
					double proteinInFood = reader.nextDouble();
					updateTotalProtein(foodData, newStats, fatInFood, proteinInFood);

					writeToFile(foodData);
					Food foodTracked = new Food(foodName, caloriesInFood, carbsInFood, fatInFood, proteinInFood);
					storedFood.add(foodTracked);
	
					addFoodToStats(database, totalCals, foodTracked);
				}

			catch (InputMismatchException ex) {
				System.err.println("Invalid input1, please enter again");
		        reader.nextLine();
			}
			// empty reader to flush remaining new line
			reader.nextLine();
			}
			else {
				System.out.println("Food of same type has been entered before and stats were remembered");
				Food duplicateFood = findDuplicateFood(foodName);
				double totalCals = updateTotalCals(foodData, newStats, duplicateFood.getCalories());
				addFoodToStats(database, totalCals, duplicateFood);
			}
	
	}

	private String enterName(ArrayList<String> foodData, dayStatistics newStats) {
		System.out.println("Enter the name of the food: ");
		String foodName = reader.nextLine();
		newStats.setName(foodName);
		foodData.add(foodName);
		return foodName;
	}

	private Food findDuplicateFood(String foodName) {
		for(Food f : storedFood) {
			if(f.getName().equals(foodName)) {
				return f;
			}
		}
		
		return null;
	}

	private void updateTotalProtein(ArrayList<String> foodData, dayStatistics newStats, double fatInFood, double proteinInFood) {
		newStats.totalProtein(proteinInFood);
		String protein = Double.toString(fatInFood);
		foodData.add("protein: " + protein);
	}

	private void updateTotalFat(ArrayList<String> foodData, dayStatistics newStats, double fatInFood) {
		newStats.totalFat(fatInFood);
		String fat = Double.toString(fatInFood);
		foodData.add("fat: " + fat);
		reader.nextLine();
	}

	private void updateTotalCarbs(ArrayList<String> foodData, dayStatistics newStats,
			double carbsInFood) {
		newStats.totalCarbs(carbsInFood);
		String carbs = Double.toString(carbsInFood);
		foodData.add("carbs: " + carbs);
		reader.nextLine();
	}

	private double updateTotalCals(ArrayList<String> foodData, dayStatistics newStats,
			double caloriesInFood) {
		Scanner reader = new Scanner(System.in);
		double totalCals = newStats.totalCalories(caloriesInFood);
		String cals = Double.toString(caloriesInFood);
		foodData.add("calories: " + cals);
		return totalCals;
	}

	private void sanitizeProteinInput() {
		System.out.println("Finally, enter the amount of protein: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void sanitizeFatInput() {
		System.out.println("Enter the amount of fat: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void sanitizeCarbsInput() {
		System.out.println("Enter the number of carbs: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void sanitizeCaloriesInput() {
		System.out.println("Enter the number of calories: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void addFoodToStats(FoodCSV database, double totalCals,
			Food foodTracked) {
		statsTracker.add(totalCals);
		
		database.addFood(foodTracked);

		System.out.println("Food entered.");
	}

	public double sum(List<Double> list) {
		double sum = 0;
		for (double i : list) {
			sum += i;
		}
		return sum;
	}
	
	public static void writeToFile(ArrayList<String> foodStats) {
		LocalDate localDate = java.time.LocalDate.now();
		String lastDate = localDate.toString();
		
		try (FileWriter lastDateEntered = new FileWriter("lastDateRan.txt", false)){
			lastDateEntered.write(lastDate);			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try (FileWriter dayFoodStats = new FileWriter("dailyFoodStatistics2.csv", true)) {
			try {
				File date = new File("lastDateRan.txt");
				Scanner dateReader = new Scanner(date);
				while (dateReader.hasNextLine()) {
					String compareDate = dateReader.nextLine();
					if (compareDate.equals(lastDate)) {
						dayFoodStats.append(foodStats + "\n");
					}
					else {
						dayFoodStats.append(lastDate + "\r" + foodStats + "\n");
					}
				}	
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
		


	
	public boolean checkDuplicate(String name) {
		for(Food f: storedFood) {
			if(f.getName().equals(name)) {
				makeCopy(f);
				return true;
			}
		}
		
		return false;
		
	}
	
	public void makeCopy(Food foodToCopy) {
		Food foodToAdd = foodToCopy;
		storedFood.add(foodToAdd);
		
		
	}

}
