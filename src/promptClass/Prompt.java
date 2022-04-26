package promptClass;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dayStatistics.dayStatistics;
import foodCSV.FoodCSV;
import foodClass.Food;
import mealClass.Meal;


import java.io.FileWriter;
import java.io.IOException;

public class Prompt {
	
	
	public void run() {

		String optionMenuSelection = "";
		String optionMenuSelection2 = "";
		ArrayList<Food> storedFood = new ArrayList<>();
		List<Double> statsTracker = new ArrayList<>();
		FoodCSV database = new FoodCSV();
		String fileName = "database(1).csv";
		database.generateFoodCSV(fileName);
		

		viewOptionMenu(optionMenuSelection, storedFood, statsTracker, database);

	}

	private void viewOptionMenu(String optionMenuSelection, ArrayList<Food> storedFood, List<Double> statsTracker, FoodCSV database) {
		try (Scanner reader = new Scanner(System.in)) {
			while (!optionMenuSelection.equalsIgnoreCase("quit") && !optionMenuSelection.equals("4")) {

				System.out.println("\nWelcome to Calorie Counter! Please enter a number from the options below:"
						+ "\n1. Enter a food\n2. Check food stats\n3. Create a meal\n4. Quit ");

				optionMenuSelection = chooseFromOptions(storedFood, statsTracker, reader, database);
			}
		}
		System.out.println("Tracker ended.");
	}

	private String chooseFromOptions(ArrayList<Food> storedFood, List<Double> statsTracker, Scanner reader, FoodCSV database) {
		String optionMenuSelection;
		String optionMenuSelection2;
		optionMenuSelection = reader.nextLine();

		switch (optionMenuSelection) {

		case "1":

			createFood(storedFood, statsTracker, reader, database);
			break;

		case "2":

			statisticsView(storedFood, statsTracker, reader);

			break;

		case "3":

			createMeal(storedFood, statsTracker, reader, database);

			break;
		}
		return optionMenuSelection;
	}

	private void statisticsView(ArrayList<Food> storedFood, List<Double> statsTracker, Scanner reader) {
		String optionMenuSelection2 = chooseFromStatsOptions(reader);
		switch (optionMenuSelection2) {

		case "1":

			checkFoodStats(storedFood, reader);
			break;

		case "2":

			double totalStats = sum(statsTracker);
			System.out.println(totalStats);
			break;
		}
	}

	private String chooseFromStatsOptions(Scanner reader) {
		String optionMenuSelection2;
		System.out.println("\n1. Enter the name of the food to view nutrition statistics\n2. Check total calories");

		optionMenuSelection2 = reader.nextLine();
		return optionMenuSelection2;
	}

	private void createMeal(ArrayList<Food> storedFood, List<Double> statsTracker, Scanner reader, FoodCSV database) {
		boolean doneEnteringFood = false;

		Meal trackedMeal = new Meal();

		System.out.println("Enter a name for your meal: ");
		String mealName = reader.nextLine();

		trackedMeal.setName(mealName);
		enterFoodForMeal(storedFood, statsTracker, reader, doneEnteringFood, trackedMeal, database);

		System.out.println("Meal complete");
	}

	private void enterFoodForMeal(ArrayList<Food> storedFood, List<Double> statsTracker, Scanner reader,
			boolean doneEnteringFood, Meal trackedMeal, FoodCSV database) {

		while (doneEnteringFood != true) {

			enterFood(storedFood, statsTracker, reader, trackedMeal, database);

			System.out.println("Would you like to enter another food? Y/N");

			String anotherFood = reader.nextLine();

			if (anotherFood.equalsIgnoreCase("N") || anotherFood.equalsIgnoreCase("no")) {
				doneEnteringFood = true;
			}
		}

	}

	private void enterFood(ArrayList<Food> storedFood, List<Double> statsTracker, Scanner reader, Meal trackedMeal,
			FoodCSV database) {
		System.out.println("Enter a food to add to a meal.");

		createFood(storedFood, statsTracker, reader, database);
		trackedMeal.addFood(storedFood.get(storedFood.size() - 1));
	}

	private void checkFoodStats(ArrayList<Food> storedFood, Scanner reader) {
		System.out.println("Enter the food name now: ");
		String foodToView = reader.nextLine();

		for (Food f : storedFood) {

			if (f.getName().equalsIgnoreCase(foodToView)) {
				System.out.println(f.toString());
			}
		}
	}
	public void createFood(ArrayList<Food> storedFood, List<Double> statsTracker, Scanner reader, FoodCSV database) {

			ArrayList<String> foodData = new ArrayList<>();
			dayStatistics newStats = new dayStatistics();
			String foodName = enterName(reader, foodData, newStats);
			
			if(checkDuplicate(storedFood, foodName) != true) {
				try {
					sanitizeCaloriesInput(reader);
					double caloriesInFood = reader.nextDouble();
					double totalCals = updateTotalCals(foodData, newStats, caloriesInFood);
	
					sanitizeCarbsInput(reader);
					double carbsInFood = reader.nextDouble();
					updateTotalCarbs(reader, foodData, newStats, carbsInFood);
					
					sanitizeFatInput(reader);
					double fatInFood = reader.nextDouble();
					updateTotalFat(reader, foodData, newStats, fatInFood);
					
					sanitizeProteinInput(reader);
					double proteinInFood = reader.nextDouble();
					updateTotalProtein(foodData, newStats, fatInFood, proteinInFood);

					writeToFile(foodData);
					Food foodTracked = new Food(foodName, caloriesInFood, carbsInFood, fatInFood, proteinInFood);
					storedFood.add(foodTracked);
	
					addFoodToStats(statsTracker, database, totalCals, foodTracked);
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
				Food duplicateFood = findDuplicateFood(storedFood, foodName);
				double totalCals = updateTotalCals(foodData, newStats, duplicateFood.getCalories());
				addFoodToStats(statsTracker, database, totalCals, duplicateFood);
			}
	
	}

	private String enterName(Scanner reader, ArrayList<String> foodData, dayStatistics newStats) {
		System.out.println("Enter the name of the food: ");
		String foodName = reader.nextLine();
		newStats.setName(foodName);
		foodData.add(foodName);
		return foodName;
	}

	private Food findDuplicateFood(ArrayList<Food> storedFood, String foodName) {
		for(Food f : storedFood) {
			if(f.getName().equals(foodName)) {
				return f;
			}
		}
		
		return null;
	}

	private void updateTotalProtein(ArrayList<String> foodData, dayStatistics newStats, double fatInFood,
			double proteinInFood) {
		newStats.totalProtein(proteinInFood);
		String protein = Double.toString(fatInFood);
		foodData.add("protein: " + protein);
	}

	private void updateTotalFat(Scanner reader, ArrayList<String> foodData, dayStatistics newStats,
			double fatInFood) {
		newStats.totalFat(fatInFood);
		String fat = Double.toString(fatInFood);
		foodData.add("fat: " + fat);
		reader.nextLine();
	}

	private void updateTotalCarbs(Scanner reader, ArrayList<String> foodData, dayStatistics newStats,
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

	private void sanitizeProteinInput(Scanner reader) {
		System.out.println("Finally, enter the amount of protein: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void sanitizeFatInput(Scanner reader) {
		System.out.println("Enter the amount of fat: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void sanitizeCarbsInput(Scanner reader) {
		System.out.println("Enter the number of carbs: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void sanitizeCaloriesInput(Scanner reader) {
		System.out.println("Enter the number of calories: ");
		if (! reader.hasNextDouble()) {
			System.err.println("Invalid input, please enter again");
			reader.nextLine();
		}
	}

	private void addFoodToStats(List<Double> statsTracker, FoodCSV database, double totalCals,
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
	
	public void writeToFile(ArrayList<String> foodStats) {
		try (FileWriter dayFoodStats = new FileWriter("dailyFoodStatistics.csv", true)) {
			dayFoodStats.append(foodStats + "\n");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkDuplicate(ArrayList<Food> storedFood, String name) {
		
		for(Food f: storedFood) {
			if(f.getName().equals(name)) {
				makeCopy(storedFood, f);
				return true;
			}
		}
		
		return false;
		
	}
	
	public void makeCopy(ArrayList<Food> storedFood, Food foodToCopy) {
		
		Food foodToAdd = foodToCopy;
		storedFood.add(foodToAdd);
		
		
	}

}
