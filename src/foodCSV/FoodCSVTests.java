package foodCSV;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodClass.Food;
import foodCSV.FoodCSV;

public class FoodCSVTests {
	
	private Food egg;
	private Food apple;
	
	private String[][] dataArr;
	private String[] appleArray;
	private String[] eggArray;
	
	private FoodCSV data;
	
	private String fileName;
	String curDir;
	File csv1;
	
	@BeforeEach
	void setup() {
		egg = new Food("egg", 78, .6, 5, 6);
		apple = new Food("apple", 95);
		
		data = new FoodCSV();
		
		fileName = "TESTdatabase.csv";
		data.generateFoodCSV(fileName);
		
		data.addFood(apple);
		
		data.addFood(egg);
		
		curDir = System.getProperty("user.dir");
		csv1 = new File(curDir + "/" + fileName);
		dataArr = data.csvTo2dArray(csv1);
		
		appleArray = apple.toArrayOfStats();
		eggArray = egg.toArrayOfStats();
		
	}
	
	
	@Test
	void testGenCSV() {
		assertTrue(dataArr[1][1].equals(appleArray[1]));
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
