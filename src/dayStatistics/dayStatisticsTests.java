package dayStatistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodClass.Food;
import mealClass.Meal;

public class dayStatisticsTests {
	private Food egg;
	private Food apple;
	private Food yogurt;
	private dayStatistics testFoodStatistics;
	double testCalories;
	double testCarbs;
	double testFat;
	double testProtein;
	
	@BeforeEach
	void setup() {
		egg = new Food("egg", 78, .6, 5, 6);
		apple = new Food("apple", 95, 25, 0.3, 0.5);
		yogurt = new Food("yogurt", 100, 6, 0.7, 17);
		testFoodStatistics = new dayStatistics();
		double testCalories = 0;
		double testCarbs = 0;
		double testFat = 0;
		double testProtein = 0;
		
	}
	
	@Test
	void testAddFood() {
		
		testFoodStatistics.addFood(egg);
		testFoodStatistics.addFood(apple);
		testFoodStatistics.addFood(yogurt);
		int numberOfFoodItems = testFoodStatistics.numberOfFoodItems();
		assertEquals(3, numberOfFoodItems);
	}
	
	@Test
	void testTotalCalories() {
		testFoodStatistics.addFood(egg);
		testFoodStatistics.addFood(apple);
		testFoodStatistics.addFood(yogurt);
		double calorieTotal = testFoodStatistics.totalCalories(testCalories);
		assertEquals(273, calorieTotal);
	}
	
	@Test
	void testTotalCarbs() {
		testFoodStatistics.addFood(egg);
		testFoodStatistics.addFood(apple);
		testFoodStatistics.addFood(yogurt);
		double carbTotal = testFoodStatistics.totalCarbs(testCarbs);
		assertEquals(31.6, carbTotal);
	}
	
	@Test
	void testTotalFat() {
		testFoodStatistics.addFood(egg);
		testFoodStatistics.addFood(apple);
		testFoodStatistics.addFood(yogurt);
		double fatTotal = testFoodStatistics.totalFat(testFat);
		assertEquals(6, fatTotal);
	}
	
	@Test
	void testTotalProtein() {
		testFoodStatistics.addFood(egg);
		testFoodStatistics.addFood(apple);
		testFoodStatistics.addFood(yogurt);
		double proteinTotal = testFoodStatistics.totalProtein(testProtein);
		assertEquals(23.5, proteinTotal);
	}
	
}
