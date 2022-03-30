package promptClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dayStatistics.dayStatistics;
import foodClass.Food;
import mealClass.Meal;

public class Prompt {
	public void run() {

		String optionMenuSelection = "";
		String optionMenuSelection2 = "";
		ArrayList<Food> storedFood = new ArrayList<>();
		List<Double> statsTracker = new ArrayList<>();

		try (Scanner reader = new Scanner(System.in)) {
			while (!optionMenuSelection.equalsIgnoreCase("quit") && !optionMenuSelection.equals("4")) {

				System.out.println("\nWelcome to Calorie Counter! Please enter a number from the options below:"
						+ "\n1. Enter a food\n2. Check food stats\n3. Create a meal\n4. Quit ");

				optionMenuSelection = reader.nextLine();

				switch (optionMenuSelection) {

				case "1":

					createFood(storedFood, statsTracker);

					break;

				case "2":
					System.out.println(
							"\n1. Enter the name of the food to view nutrition statistics\n2. Check total calories");
					optionMenuSelection2 = reader.nextLine();
					switch (optionMenuSelection2) {
					case "1":
						System.out.println("Enter the food name now: ");
						String foodToView = reader.nextLine();

						for (Food f : storedFood) {

							if (f.getName().equalsIgnoreCase(foodToView)) {
								System.out.println(f.toString());
							}
						}

						break;

					case "2":
						double totalStats = sum(statsTracker);
						System.out.println(totalStats);
					}

					break;

				case "3":

					boolean doneEnteringFood = false;

					Meal trackedMeal = new Meal();

					System.out.println("Enter a name for your meal: ");
					String mealName = reader.nextLine();

					trackedMeal.setName(mealName);

					while (doneEnteringFood != true) {

						System.out.println("Enter a food to add to a meal.");

						createFood(storedFood, statsTracker);
						trackedMeal.addFood(storedFood.get(storedFood.size() - 1));

						System.out.println("Would you like to enter another food? Y/N");

						String anotherFood = reader.nextLine();

						if (anotherFood.equalsIgnoreCase("N") || anotherFood.equalsIgnoreCase("no")) {
							doneEnteringFood = true;
						}

					}

					System.out.println("Meal complete");
					break;

				}

			}
		}
		System.out.println("Tracker ended.");

	}

	public static void createFood(ArrayList<Food> storedFood, List<Double> statsTracker) {

		try (Scanner reader = new Scanner(System.in)) {
			dayStatistics newStats = new dayStatistics();
			System.out.println("Enter the name of the food: ");
			String foodName = reader.nextLine();
			newStats.setName(foodName);
			System.out.println("Enter the number of calories: ");
			double caloriesInFood = reader.nextDouble();
			double totalCals = newStats.totalCalories(caloriesInFood);
			System.out.println("Enter the number of carbs: ");
			double carbsInFood = reader.nextDouble();
			newStats.totalCarbs(carbsInFood);
			System.out.println("Enter the amount of fat: ");
			double fatInFood = reader.nextDouble();
			newStats.totalFat(fatInFood);
			System.out.println("Finally, enter the amount of protein: ");
			double proteinInFood = reader.nextDouble();
			newStats.totalProtein(proteinInFood);

			Food foodTracked = new Food(foodName, caloriesInFood, carbsInFood, fatInFood, proteinInFood);
			storedFood.add(foodTracked);

			statsTracker.add(totalCals);

			System.out.println("Food entered.");
		}

	}

	public static double sum(List<Double> list) {
		double sum = 0;
		for (double i : list) {
			sum += i;
		}
		return sum;
	}

//public static void foodStats() {
//	stats.setName(foodName);
//	
//	}

}
