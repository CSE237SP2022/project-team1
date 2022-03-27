package foodClass;

public class Food {
	
	private String name;
	private double calories;
	private double carbs;
	private double fat;
	private double protein;
	
	public Food(String name, double calories, double carbs,
			double fat, double protein) {
		this.name = name;
		this.calories = calories;
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
		
		return name + "\nCalories: " + calories;
	}

}
