package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TrajectoryQuizTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	//Load in quiz questions and answers
	@Test
	public void testQuizReadIn() {
		
		Map<String, String> questions = new HashMap<String, String>();
		
		//Test to see that all of the questions were loaded in from the config
		assertEquals(0, questions.size());
		
		//Test to see that answers are mapped to questions
		assertEquals("Sample Question", questions.get("Sample Question"));
		
		//Test to see that answers are mapped to questions
		assertEquals("Sample Question", questions.get("Sample Question"));
		
		//Test to see that answers are mapped to questions
		assertEquals("Sample Question", questions.get("Sample Question"));
		
		
	}
	
	
	//display quiz questions


}
