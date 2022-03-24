package foodClass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTests {

	private Food egg;
	private Food apple;
	
	@BeforeEach
	void setup() {
		egg = new Food("egg", 78, .6, 5, 6);
		apple = new Food("apple", 95);
	}
	
	
	@Test
	void testName() {
		assertTrue("egg".equals(egg.getName()));
	}
	@Test
	void testCalories() {
		assertEquals(78, egg.getCalories(), 0.05);
	}
	@Test
	void testCarbs() {
		assertEquals(0.6, egg.getCarbs(), 0.05);
	}
	@Test
	void testFat() {
		assertEquals(5, egg.getFat(), 0.05);
	}
	@Test
	void testProtein() {
		assertEquals(6, egg.getProtein(), 0.05);
	}
	
	
	@Test
	void testNameSimple() {
		assertTrue("apple".equals(apple.getName()));
	}
	@Test
	void testCaloriesSimple() {
		assertEquals(95, apple.getCalories(), 0.05);
	}
	@Test
	void testCarbsSimple(){
		assertEquals(-1, apple.getFat(), 0.05);
	}
	@Test
	void testFatSimple(){
		assertEquals(-1, apple.getCarbs(), 0.05);
	}
	@Test
	void testProteinSimple(){
		assertEquals(-1, apple.getProtein(), 0.05);
	}
	
	
	

}
