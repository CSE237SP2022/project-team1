package promptClass;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodClass.Food;

class PromptTests {

	private List<Double> totalCalories = new ArrayList<Double>();
	private ArrayList<Food> storedFood = new ArrayList<>();
	private Scanner reader = new Scanner(System.in);
	
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
	

}