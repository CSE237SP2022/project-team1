package mealClass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodClass.Food;

class MealTests {

	private Food egg;
	private Food apple;
	private Food yogurt;
	private Meal testMeal;
	
	@BeforeEach
	void setup() {
		egg = new Food("egg", 78, .6, 5, 6);
		apple = new Food("apple", 95);
		yogurt = new Food("yogurt", 100, 6, 0.7, 17);
		testMeal = new Meal();
	}
	
	@Test
	void testAddFood() {
		
		testMeal.addFood(egg);
		int numberOfFoodInMeals = testMeal.numberOfFoodItems();
		assertEquals(1, numberOfFoodInMeals);
	}
	
	@Test
	void testTotalCalories() {
		testMeal.addFood(egg);
		testMeal.addFood(apple);
		int calorieTotal = testMeal.totalCalories();
		assertEquals(173, calorieTotal);
	}
	
	@Test
	void testTotalCarbs() {
		testMeal.addFood(egg);
		testMeal.addFood(yogurt);
		double carbTotal = testMeal.totalCarbs();
		assertEquals(6.6, carbTotal);
	}
	
	@Test
	void testTotalFat() {
		testMeal.addFood(egg);
		testMeal.addFood(yogurt);
		double fatTotal = testMeal.totalFat();
		assertEquals(5.7, fatTotal);
	}
}
