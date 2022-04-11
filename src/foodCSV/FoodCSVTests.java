package foodCSV;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodClass.Food;
import foodCSV.FoodCSV;

public class FoodCSVTests {
	
	private FoodCSV data;
	private String fileName;
	private Food egg;
	private Food apple;
	
	@BeforeEach
	void setup() {
		
		data = new FoodCSV();
		
		fileName = "TESTdatabase.csv";
		data.generateFoodCSV(fileName);
		
		egg = new Food("egg", 78, .6, 5, 6);
		apple = new Food("apple", 95);
		data.addFood(apple);
		data.addFood(egg);
		
	}
	
	
	@Test
	void testGenCSV() {
		assertTrue(data.getDataArr()[1][1].equals(apple.toArrayOfStats()[1]));
	}
	
	@Test
	void testFoodCountInitial() {
		assertEquals(data.getFoodCount(), 2);
	}
	@Test
	void testFoodCountIncrement() {
		Food pizza = new Food("pizza", 285, 36, 10, 12);
		Food bagel = new Food("bagel", 250, 100, 14.7, 1);
		
		data.addFood(pizza);
		data.addFood(bagel);
		
		assertEquals(4, data.getFoodCount());
	}
	
	@Test
	void testAddFood1() {
		Food pizza = new Food("pizza", 285, 36, 10, 12);
		Food bagel = new Food("bagel", 250, 100, 14.7, 1);
		
		data.addFood(pizza);
		data.addFood(bagel);
		
		assertEquals(data.getDataArr()[3][2], String.valueOf(pizza.toArrayOfStats()[2]));
	}
	
	@Test
	void testAddFood2() {
		Food tomato = new Food("tomato", 40, 20, 10, 25);		
		
		data.addFood(tomato);
						
		assertEquals(data.getDataArr()[3][2], String.valueOf(tomato.toArrayOfStats()[2]));
	}


}
