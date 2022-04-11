package foodCSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import foodClass.Food;

public class FoodCSV {
	
	private int foodCount;
	private String[][] dataArr;
	File dataCSV;
	
	public FoodCSV() {
		this.foodCount = 0;
	}
	
	public int getFoodCount() {
		return foodCount;
	}

	
	
	public void generateFoodCSV(String fileName) {
		 
	     //FileWriter writer = null;

		 try {
		     FileWriter writer = new FileWriter(fileName);
		     writer.append("Food");
		     writer.append(',');
		     writer.append("Calories");
		     writer.append(',');
		     writer.append("Carbs");
		     writer.append(',');
		     writer.append("Fat");
		     writer.append(',');
		     writer.append("Protein");
		     writer.append(',');
		     writer.append('\n');
		     
		     writer.flush();
	         writer.close();
	
		     //System.out.println("CSV file is created... " + getCurDirectory());
	
		  } 
		 catch (IOException e) {
		     e.printStackTrace();
		  } 
		 
		String curDir = System.getProperty("user.dir");
		dataCSV = new File(curDir + "/" + fileName);
		  
	}
	
	public void addFood(Food food) {
		
		writeToFile(dataCSV, food);
	    foodCount++;
	    setDataArr(csvTo2dArray(dataCSV));
	
		  
	}
	
	public static void writeToFile(File file, Food food) {
		String[] foodStats = food.toArrayOfStats();
		//FileWriter writer = null;
		
		 try {
	
			 FileWriter writer = new FileWriter(file, true);
		     		     
		     writer.append(foodStats[0]);
		     writer.append(',');
		     writer.append(foodStats[1]);
		     writer.append(',');
		     writer.append(foodStats[2]);
		     writer.append(',');
		     writer.append(foodStats[3]);
		     writer.append(',');
		     writer.append(foodStats[4]);
		     writer.append(',');
		     writer.append('\n');
	
		     writer.flush();
	         writer.close();
	
		  } catch (IOException e) {
		     e.printStackTrace();
		  } 
		 
		 
	}
	
	public String[][] csvTo2dArray(File file){
		String[][] arr = new String[getFoodCount()+1][5];
		
		try {
			Scanner sc = new Scanner(file);
			sc.useDelimiter(",");
			for(int row = 0; row<getFoodCount()+1; row++) {
				for(int col = 0; col < 5; col++) {
					if(sc.hasNext()) {
						arr[row][col] = sc.next();
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
		
	public static String getCurDirectory() {
		return System.getProperty("user.dir");
	}
	
	public void print2dArray(String[][] arr) {
		for(int row = 0; row < arr.length; row++) {
			for(int col = 0; col < arr[0].length; col++) {
				System.out.print(arr[row][col] + ", ");
			}
			//System.out.println();
		}
	}

	public String[][] getDataArr() {
		return dataArr;
	}

	public void setDataArr(String[][] dataArr) {
		this.dataArr = dataArr;
	}

}
