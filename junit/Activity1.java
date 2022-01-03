package activities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Activity1 {
 static ArrayList<String> list;

	@BeforeEach
	void setUp() throws Exception {
		// Initialize a new ArrayList
		list = new ArrayList<String>();

		// Add values to the list
		list.add("alpha"); // at index 0
		list.add("beta"); // at index 1
	}

	@Test
	void insertTest() {
	
		// Assert size of list
		assertEquals(2, list.size(), "Wrong size");
		
		list.add(2,"theta");

		// Assert individual elements
		assertEquals(3, list.size(), "Wrong element");
		
		// Assert individual elements
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("beta", list.get(1), "Wrong element");
        assertEquals("theta", list.get(2), "Wrong element");
	}
	
	@Test
	void replaceTest() {
		
		list.set(1, "charlie");
		 
        // Assert size of list
        assertEquals(2, list.size(), "Wrong size");
        // Assert individual elements
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("charlie", list.get(1), "Wrong element");
	}

}
