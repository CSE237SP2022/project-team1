package largeDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodCSV.FoodCSV;
import foodClass.Food;
import largeDatabase.largeDatabase;

public class LargeDatabaseTests {

	private largeDatabase largeDatabase;
	private String[][] largeDataArray;
	
	@BeforeEach
	void setup() {
		
		largeDatabase = new largeDatabase();
		File largeData = largeDatabase.largeData;
		
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
	
	
	
}
