package largeDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodClass.Food;

public class LargeDatabaseTests {

	private largeDatabase largeDatabase;
	private String[][] largeDataArray;
	
	@BeforeEach
	void setup() {
		largeDatabase = new largeDatabase();
		
		File largeData = largeDatabase.largeDataTester;
		largeDataArray = largeDatabase.csvTo2dArray(largeData);
		
	}
	
	
	@Test
	void testConstructor0() {
		String expected = "Name";
		String result = largeDataArray[0][0];
				
		assertTrue(result.equals(expected));
	}
	@Test
	void testConstructor1() {
		String expected = "George Weston Bakeries Thomas English Muffins";
		String result = largeDataArray[4][0];
				
		assertTrue(result.equals(expected));
	}
	@Test
	void testConstructor2() {
		String expected = "19.16";
		String result = largeDataArray[93][2];
				
		assertTrue(result.equals(expected));
	}
	@Test
	void testConstructor4() {
		String expected = "0";
		String result = largeDataArray[14164][4];
				
		assertTrue(result.equals(expected));
	}
	
	@Test
	void testMakeFoodObjectRow3() {
		Food testFood = largeDatabase.makeFoodObjectTester(2);
		Double testFoodCalories = Double.valueOf(testFood.getCalories());
		assertEquals(330, testFoodCalories, 0.05);
	}
	@Test
	void testMakeFoodObjectRow1738() {
		Food testFood = largeDatabase.makeFoodObjectTester(1737);
		Double testFoodCalories = Double.valueOf(testFood.getProtein());
		assertEquals(0.9, testFoodCalories, 0.05);
	}
	
	@Test
	void testSearchBreadFry() {
		ArrayList<Food> searchResults = largeDatabase.searchTester("bread fry");
		//largeDatabase.printSearchResult(searchResults);
		assertEquals(searchResults.size(), 2);
	}
	@Test
	void testSearchGrainWholeWheat() {
		ArrayList<Food> searchResults = largeDatabase.searchTester("grain whole wheat");
		//largeDatabase.printSearchResult(searchResults);
		assertEquals(searchResults.size(), 7);
	}
	
	
	
}
