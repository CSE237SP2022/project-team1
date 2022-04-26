package promptClass;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dayStatistics.dayStatistics;
import foodCSV.FoodCSV;
import foodClass.Food;
import largeDatabase.largeDatabase;
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
		
		// need to add a new case to allow user to search in the large database
		largeDatabase largeData = new largeDatabase(); 
		

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
		String optionMenuSelection2;
		System.out.println("\n1. Enter the name of the food to view nutrition statistics\n2. Check total calories");

		optionMenuSelection2 = reader.nextLine();
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

			System.out.println("Enter a food to add to a meal.");

			createFood(storedFood, statsTracker, reader, database);
			trackedMeal.addFood(storedFood.get(storedFood.size() - 1));

			System.out.println("Would you like to enter another food? Y/N");

			String anotherFood = reader.nextLine();

			if (anotherFood.equalsIgnoreCase("N") || anotherFood.equalsIgnoreCase("no")) {
				doneEnteringFood = true;
			}
		}

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
	public static void createFood(ArrayList<Food> storedFood, List<Double> statsTracker, Scanner reader, FoodCSV database) {

			ArrayList<String> foodData = new ArrayList<>();
			dayStatistics newStats = new dayStatistics();
			System.out.println("Enter the name of the food: ");
			String foodName = reader.nextLine();
			newStats.setName(foodName);
			foodData.add(foodName);
			try {
				System.out.println("Enter the number of calories: ");
				if (! reader.hasNextDouble()) {
					System.err.println("Invalid input, please enter again");
					reader.nextLine();
				}
				double caloriesInFood = reader.nextDouble();
				double totalCals = newStats.totalCalories(caloriesInFood);
				String cals = Double.toString(caloriesInFood);
				foodData.add("calories: " + cals);
				reader.nextLine();

				System.out.println("Enter the number of carbs: ");
				if (! reader.hasNextDouble()) {
					System.err.println("Invalid input, please enter again");
					reader.nextLine();
				}
				double carbsInFood = reader.nextDouble();
				newStats.totalCarbs(carbsInFood);
				String carbs = Double.toString(carbsInFood);
				foodData.add("carbs: " + carbs);
				reader.nextLine();
				
				System.out.println("Enter the amount of fat: ");
				if (! reader.hasNextDouble()) {
					System.err.println("Invalid input, please enter again");
					reader.nextLine();
				}
				double fatInFood = reader.nextDouble();
				newStats.totalFat(fatInFood);
				String fat = Double.toString(fatInFood);
				foodData.add("fat: " + fat);
				reader.nextLine();
				
				System.out.println("Finally, enter the amount of protein: ");
				if (! reader.hasNextDouble()) {
					System.err.println("Invalid input, please enter again");
					reader.nextLine();
				}
				double proteinInFood = reader.nextDouble();
				newStats.totalProtein(proteinInFood);
				String protein = Double.toString(fatInFood);
				foodData.add("protein: " + protein);

				writeToFile(foodData);
				Food foodTracked = new Food(foodName, caloriesInFood, carbsInFood, fatInFood, proteinInFood);
				storedFood.add(foodTracked);

				statsTracker.add(totalCals);
				
				database.addFood(foodTracked);

				System.out.println("Food entered.");


			}
			catch (InputMismatchException ex) {
				System.err.println("Invalid input1, please enter again");
		        reader.nextLine();
			}
			// empty reader to flush remaining new line
			reader.nextLine();
	}

	public static double sum(List<Double> list) {
		double sum = 0;
		for (double i : list) {
			sum += i;
		}
		return sum;
	}
	
	public static void writeToFile(ArrayList<String> foodStats) {
		try (FileWriter dayFoodStats = new FileWriter("dailyFoodStatistics.csv", true)) {
			dayFoodStats.append(foodStats + "\n");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
