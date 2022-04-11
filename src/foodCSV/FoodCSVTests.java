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
	
	@BeforeEach
	void setup() {
		egg = new Food("egg", 78, .6, 5, 6);
		apple = new Food("apple", 95);
				
//		FoodCSV foodCSV = new FoodCSV();
//		foodCSV.generateFoodCSV("FoodCSV");
//		
//		String curDir = System.getProperty("user.dir");
//		File csv1 = new File(curDir + "/FoodCSV");
//		
//		foodCSV.addFood(foodCSV.getFileWriter(), "FoodCSV", apple);
	}
	
	
	@Test
	void testGenCSV() {
		String fileName = "foodCSV";
		
		FoodCSV data = new FoodCSV();
		
		data.generateFoodCSV(fileName);
		data.addFood(data.getFileWriter(), fileName, apple);
		data.addFood(data.getFileWriter(), fileName, egg);
		
		String curDir = System.getProperty("user.dir");
		File csv1 = new File(curDir + "/" + fileName);
				
		String[][] dataArr = data.csvTo2dArray(csv1);
		
		String[] appleArray = apple.toArrayOfStats();
		
		data.print2dArray(dataArr);
		//System.out.println(dataArr[1][1]);
		//System.out.println(appleArray[1]);
		
		assertTrue(dataArr[1][1].equals(appleArray[1]));
	}
	

}
