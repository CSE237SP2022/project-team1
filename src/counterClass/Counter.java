package counterClass;

import foodCSV.FoodCSV;
import promptClass.Prompt;

public class Counter {

	public static void main(String[] args) {
		
		FoodCSV database = new FoodCSV();
		String fileName = "database(1).csv";
		database.generateFoodCSV(fileName);
		
		Prompt p = new Prompt();
		p.run();
		
	}
}