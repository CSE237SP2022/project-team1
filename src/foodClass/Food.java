package foodClass;

public class Food {
	
	private String name;
	private double calories;
	private double carbs;
	private double fat;
	private double protein;
	
	public Food(String name, double caloriess, double carbs,
			double fat, double protein) {
		this.name = name;
		this.calories = caloriess;
		this.carbs = carbs;
		this.fat = fat;
		this.protein = protein;
	}
	
	public Food(String name, double calories) {
		this.name = name;
		this.calories = calories;
		
		this.carbs = -1;
		this.fat = -1;
		this.protein = -1;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getCalories() {
		return this.calories;
	}
	
	public double getCarbs() {
		return this.carbs;
	}
	
	public double getFat() {
		return this.fat;
	}
	
	public double getProtein() {
		return this.protein;
	}
	
	public String toString() {
		String foodToString = this.getName() + " has: " + this.getCalories() + " calories, " + 
				this.getCarbs() + "grams carbs, " + this.getFat() + "grams fat, " +
				this.getProtein() + "grams protein.";
		return foodToString;
	}
	
	public String[] toArrayOfStats() {
		String[] arr = new String[5];
		
		arr[0] = this.getName();
		arr[1] = String.valueOf(this.getCalories());
		arr[2] = String.valueOf(this.getCarbs());
		arr[3] = String.valueOf(this.getFat());
		arr[4] = String.valueOf(this.getProtein());
		
		return arr;
	}

}
