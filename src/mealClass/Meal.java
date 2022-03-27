package mealClass;

import java.util.ArrayList;

import foodClass.Food;


public class Meal {

	ArrayList<Food> mealItems = new ArrayList<Food>();
	String name;
	
	public Meal() {
		this.name = name;
		this.mealItems = mealItems;
	}
	
	public void addFood(Food food) {
		mealItems.add(food);
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public int totalCalories() {
		
		int totalCaloriesInMeal = 0;
		for(Food f : mealItems) {
			
			totalCaloriesInMeal += f.getCalories();
		}
		
		return totalCaloriesInMeal;
	}
	
	public double totalCarbs() {
		
		double totalCarbsInMeal = 0;
		for(Food f : mealItems) {
			
			totalCarbsInMeal += f.getCarbs();
		}
		
		return totalCarbsInMeal;
	}
	
	public int numberOfFoodItems() {
		
		return mealItems.size();
	}
	
	public double totalFat() {
		
		double totalFatInMeal = 0;
		for(Food f : mealItems) {
			
			totalFatInMeal += f.getFat();
		}
		
		return totalFatInMeal;
	
	}
}
