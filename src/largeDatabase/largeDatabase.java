package largeDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import foodClass.Food;

public class largeDatabase {
	
	File largeData; File largeDataTester;
	String[][] largeDataArray; String[][] largeDataTesterArray;
	
	private static final int ROWS = 14165;
	private static final int COLS = 5;
	
	public largeDatabase() {
		String curDir = System.getProperty("user.dir");
		String fileName = "large_database.csv";
		
		this.largeData = new File(curDir + "/" + fileName);
		this.largeDataTester = new File(curDir + "/src/counterClass/" + fileName);
		
		this.largeDataArray = csvTo2dArray(largeData);
		this.largeDataTesterArray = csvTo2dArray(largeDataTester);
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
						
						// account for fact that csv doesn't end lines w commas
						if(next.contains("\n")) {
							String[] parts = next.split("\\n");							
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
			//e.printStackTrace();
		}
		return arr;
	}
	
	
	public ArrayList<Food> search(String input) {
		ArrayList<Food> result = new ArrayList<>();
				
		String[] inputKeywords = input.split(" ");
		
		try {
			for(int j=0; j<largeDataArray.length; j++) {
				String foodInDatabase = largeDataArray[j][0].toLowerCase();
				int containsCounter = 1;
				boolean contains = false;
				int threshold = inputKeywords.length+1;			
			
				for(int i=0; i<inputKeywords.length; i++) {
					
					String inputKeyword = inputKeywords[i].toLowerCase();
	
					if(foodInDatabase.contains(inputKeyword)) {
						containsCounter++;
					}
					if(containsCounter == threshold) {
						contains = true;
					}
					if(contains) {
						Food foodToAdd = makeFoodObject(j);
						result.add(foodToAdd);
						containsCounter = 1;
						contains = false;
					}
				}
			}
		}
		catch (NullPointerException e) {
			System.out.println("Run program in command line with \'./run.sh\'");
		}
		return result;
	}	
	
	
	public void printSearchResult(ArrayList<Food> result) {
		
		System.out.println("Results: " + result.size());
		
		for(Food food : result) {
			String toPrint = printSearchResultHelper(food);
			System.out.print(toPrint);
		}
		
	}
	public String printSearchResultHelper(Food food) {
		String[] arrayOfStats = food.toArrayOfStats();
		
		String name = arrayOfStats[0];
		String calories = arrayOfStats[1];
		String carbs = arrayOfStats[2];
		String fat = arrayOfStats[3];
		String protein = arrayOfStats[4];
		
		return name + " | " + calories + " | " + carbs + " | " + fat + " | " + protein + "\n";
	}
	
	public Food makeFoodObject(int row) {
		//row--;
		String name = this.largeDataArray[row][0];
		double calories = Double.valueOf(this.largeDataArray[row][1]);
		double carbs = Double.valueOf(this.largeDataArray[row][2]);
		double fat = Double.valueOf(this.largeDataArray[row][3]);
		double protein = Double.valueOf(this.largeDataArray[row][4]);
		
		Food food = new Food(name, calories, carbs, fat, protein);
		return food;
	}
	
	
	////////////////////////////////////////////////////////////
//	Methods to help with unit tests since the test runs 
//	from a different working directory than the main 
//	method. The only difference is that the tests access 
//	largeDataTester and largeDataTesterArray since those
//	are accessed by a different relative path from
//	the current working directory
	
	
	public ArrayList<Food> searchTester(String input) {
		ArrayList<Food> result = new ArrayList<>();
				
		String[] inputKeywords = input.split(" ");
		
		for(int j=0; j<largeDataTesterArray.length; j++) {
			String foodInDatabase = largeDataTesterArray[j][0].toLowerCase();
			int containsCounter = 1;
			boolean contains = false;
			int threshold = inputKeywords.length+1;			
		
			for(int i=0; i<inputKeywords.length; i++) {
				
				String inputKeyword = inputKeywords[i].toLowerCase();

				if(foodInDatabase.contains(inputKeyword)) {
					containsCounter++;
				}
				if(containsCounter == threshold) {
					contains = true;
				}
				if(contains) {
					Food foodToAdd = makeFoodObjectTester(j);
					result.add(foodToAdd);
					containsCounter = 1;
					contains = false;
				}
			}
		}
		return result;
	}	
	public Food makeFoodObjectTester(int row) {
		//row--;
		String name = this.largeDataTesterArray[row][0];
		double calories = Double.valueOf(this.largeDataTesterArray[row][1]);
		double carbs = Double.valueOf(this.largeDataTesterArray[row][2]);
		double fat = Double.valueOf(this.largeDataTesterArray[row][3]);
		double protein = Double.valueOf(this.largeDataTesterArray[row][4]);
		
		Food food = new Food(name, calories, carbs, fat, protein);
		return food;
	}
	
	public void print1dArray(String[] arr) {
		for(String s : arr) {
			System.out.println(s);
		}
	}
	
	
	
	 

}
