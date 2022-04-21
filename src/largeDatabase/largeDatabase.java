package largeDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import foodClass.Food;

public class largeDatabase {
	
	File largeData;
	
	private static final int ROWS = 14165;
	private static final int COLS = 5;
	
	public largeDatabase() {
		String curDir = System.getProperty("user.dir");
		String fileName = "large_database.csv";
		
		// may need to change path when this is part of prompt class
		this.largeData = new File(curDir + "/src/counterClass/" + fileName);
	}
	

	
	public String[][] csvTo2dArray(File file){
		String[][] arr = new String[ROWS][COLS];
		
		try {
			Scanner sc = new Scanner(file);
			sc.useDelimiter(",");
			for(int row = 0; row<ROWS; row++) {
				for(int col = 0; col < COLS; col++) {
					if(sc.hasNext()) {
						String next = sc.next();
						if(next.contains("\n")) {
							String[] parts = next.split("\\n");
							
//							System.out.println(row + " -- " + col);
//							System.out.println(parts[0] + " -- " + parts[1]);
							
							arr[row][col] = parts[0];
							row++; col = 0;
							arr[row][col] = parts[1];
						}
						else {
							arr[row][col] = next;
						}
						
					}
				}
			}
			sc.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public ArrayList<Food> search(String input) {
		ArrayList<Food> result = new ArrayList<>();
		
		String[][] largeDataArray = csvTo2dArray(largeData);
		
		//ToDo
		String[] inputKeywords = input.split(" ");
		
		for(int i = 1; i<ROWS; i++) {
			for(int j = 0; j<inputKeywords.length; j++) {
				if(largeDataArray[i][0].contains(inputKeywords[j])) {
					// use getRow data method
				}
			}
			
		}
		
		
		return result;
		
	}
	
	public void printSearchResult(ArrayList<Food> result) {
		
		for(Food food : result) {
			String toPrint = food.toString();
			System.out.println(toPrint);
		}
		
	}
	
	public Food getFoodFromRow(int row) {
		Food food = new Food("test", 1);
		
		return food;
	}
	
	 

}
