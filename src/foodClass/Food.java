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
	
	public double[] toArrayOfStats() {
		double[] arr = new double[4];
		arr[0] = this.getCalories();
		arr[1] = this.getCarbs();
		arr[2] = this.getFat();
		arr[3] = this.getProtein();
		return arr;
	}

}
