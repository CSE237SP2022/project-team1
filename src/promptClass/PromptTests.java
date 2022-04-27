package promptClass;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodClass.Food;

class PromptTests {

	private List<Double> totalCalories = new ArrayList<Double>();
	private ArrayList<Food> storedFood = new ArrayList<>();
	private Scanner reader = new Scanner(System.in);
	private ArrayList<String> foodStats = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		Prompt p = new Prompt();
	}
	
	@Test
	void testSum() {
		totalCalories.add(22.0);
		totalCalories.add(26.1);
		totalCalories.add(10.0);
		double sumOfAllCalories = Prompt.sum(totalCalories);
		assertEquals(58.10, sumOfAllCalories, 0.05);
	}
	
	@Test
	void testSumZero() {
		double sumOfAllCalories = Prompt.sum(totalCalories);
		assertEquals(0.00, sumOfAllCalories, 0.05);
	}	
	
	@Test
	void testStatsSameDay() {
		LocalDate date = LocalDate.of(2022, 4, 25);
		foodStats.add(date.toString());
		foodStats.add("bagel");
		foodStats.add("calories: 250");
		foodStats.add("carbohydrates: 100");
		foodStats.add("fat: 14.7");
		foodStats.add("protein: 1");
		
		LocalDate date2 = LocalDate.of(2022, 4, 25);
		foodStats.add(date2.toString());
		foodStats.add("donut");
		foodStats.add("calories: 80");
		foodStats.add("carbohydrates: 10");
		foodStats.add("fat: 20");
		foodStats.add("protein: 5");
		assertTrue(foodStats.get(0).equals(foodStats.get(6)));
	}
	
	@Test
	void testStatsDiffDays() {
		LocalDate date = LocalDate.of(2022, 4, 24);
		foodStats.add(date.toString());
		foodStats.add("egg");
		foodStats.add("calories: 78");
		foodStats.add("carbohydrates: 6");
		foodStats.add("fat: 5");
		foodStats.add("protein: 15");
		
		LocalDate date2 = LocalDate.of(2022, 4, 26);
		foodStats.add(date2.toString());
		foodStats.add("burger");
		foodStats.add("calories: 300");
		foodStats.add("carbohydrates: 180");
		foodStats.add("fat: 200");
		foodStats.add("protein: 150");
		assertFalse(foodStats.get(0).equals(foodStats.get(6)));
	}
	

}