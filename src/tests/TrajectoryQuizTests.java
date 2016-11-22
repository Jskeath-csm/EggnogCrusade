package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.GameFrame;

public class TrajectoryQuizTests {

	private GameFrame gameFrame;
	
	@Before
	public void init(){
		gameFrame = GameFrame.getInstance();
		gameFrame.loadQuiz();
	}
	
	//Load in quiz questions and answers
	@Test
	public void testQuizReadIn() {
		
		Map<String, String> questions = gameFrame.getQuestions();
		
		//Test to see that all of the questions were loaded in from the config
		assertEquals(0, questions.size());
		
		//Test to see that answers are mapped to questions
		assertEquals("Answer 1", questions.get("Sample Question 1?"));
		
		//Test to see that answers are mapped to questions
		assertEquals("Answer 2", questions.get("Sample Question 2?"));
		
		//Test to see that answers are mapped to questions
		assertEquals("Answer 3", questions.get("Sample Question 3?"));
		
		
	}
	
	
	//display quiz questions


}
