package dayStatistics;

import java.util.ArrayList;
import java.util.Scanner;

import foodClass.Food;
import mealClass.Meal;

public class dayStatistics {

	ArrayList<Food> foodItems = new ArrayList<Food>();
	String name;

	public void addFood(Food food) {
		foodItems.add(food);
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public double totalCalories(double totalCaloriesInMeal) {
		
//		double totalCaloriesInMeal = 0;
		for(Food f : foodItems) {
			totalCaloriesInMeal += f.getCalories();
		}
		return totalCaloriesInMeal;
	}
	
	public double totalCarbs(double totalCarbsInMeal) {
//		double totalCarbsInMeal = 0;
		for(Food f : foodItems) {
			totalCarbsInMeal += f.getCarbs();
		}
		return totalCarbsInMeal;
	}
	
	public int numberOfFoodItems() {
		return foodItems.size();
	}
	
	public double totalFat(double totalFatInMeal) {
//		double totalFatInMeal = 0;
		for(Food f : foodItems) {
			totalFatInMeal += f.getFat();
		}
		return totalFatInMeal;
	}
	
	public double totalProtein(double totalProteinInMeal) {
//		double totalProteinInMeal = 0;
		for (Food f : foodItems) {
			totalProteinInMeal += f.getProtein();
		}
		return totalProteinInMeal;
	}

//	public void reset() {
//		
//	}
}

