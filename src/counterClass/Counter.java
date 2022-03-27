package counterClass;

import java.util.ArrayList;
import java.util.Scanner;

import foodClass.Food;
import mealClass.Meal;

public class Counter {
	
	public static void main (String [] args) {
	
	Scanner reader = new Scanner(System.in);
	String optionMenuSelection = "";
	ArrayList<Food> storedFood = new ArrayList<Food>();
	
	while(!optionMenuSelection.equalsIgnoreCase("quit") && !optionMenuSelection.equals("4")) {
	
	System.out.println("\nWelcome to Calorie Counter! Please enter a number from the options below:"
			+ "\n1. Enter a food\n2. Check food stats\n3. Create a meal\n4. Quit ");
	
	optionMenuSelection = reader.nextLine();
	
	switch(optionMenuSelection) {
	
	case "1":
		
		createFood(storedFood);
		
		break;
		
	case "2":
		
		System.out.println("Enter the name of the food to view nutrition statistics");
		
		String foodToView = reader.nextLine();
		
		for(Food f : storedFood) {
			
			if(f.getName().equalsIgnoreCase(foodToView)) {
				System.out.println(f.toString());
			}
		}
		
		break;
		
	case "3":
		
		boolean doneEnteringFood = false;
		
		Meal trackedMeal = new Meal();
		
		System.out.println("Enter a name for your meal: ");
		String mealName = reader.nextLine();
		
		
		trackedMeal.setName(mealName);
		
		
		while(doneEnteringFood != true) {
		
		System.out.println("Enter a food to add to a meal.");
		
	
		createFood(storedFood);
		trackedMeal.addFood(storedFood.get(storedFood.size()-1));
		
		System.out.println("Would you like to enter another food? Y/N");
		
		String anotherFood = reader.nextLine();
		
		if(anotherFood.equalsIgnoreCase("N") || anotherFood.equalsIgnoreCase("no")) {
			doneEnteringFood = true;
		}
		
		}
		
		System.out.println("Meal complete");
		break;
		
	}
	
	

	}
	
	System.out.println("Tracker ended.");

}
	
	public static void createFood(ArrayList<Food> storedFood) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the name of the food: ");
		String foodName = reader.nextLine();
		System.out.println("Enter the number of calories: ");
		double caloriesInFood = reader.nextDouble();
		System.out.println("Enter the number of carbs: ");
		double carbsInFood = reader.nextDouble();
		System.out.println("Enter the amount of fat: ");
		double fatInFood = reader.nextDouble();
		System.out.println("Finally, enter the amount of protein: ");
		double proteinInFood = reader.nextDouble();
		
		Food foodTracked = new Food(foodName, caloriesInFood, carbsInFood, fatInFood, proteinInFood);
		storedFood.add(foodTracked);
		
		System.out.println("Food entered.");
		
	}

}
