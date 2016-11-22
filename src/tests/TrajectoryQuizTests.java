package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.GameFrame;
import game.QuizDialog;

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
		String firstQuestion = "Sample Question 1?";
		String secondQuestion = "Sample Question 2?";
		String thridQuestion = "Sample Question 3?";
		
		//Test to see that all of the questions were loaded in from the config
		assertEquals(0, questions.size());
		
		//Test to see that answers are mapped to questions
		assert(questions.containsKey(firstQuestion));
		assertEquals("Answer 1", questions.get(firstQuestion));
		
		//Test to see that answers are mapped to questions
		assert(questions.containsKey(secondQuestion));
		assertEquals("Answer 2", questions.get(secondQuestion));
		
		//Test to see that answers are mapped to questions
		assert(questions.containsKey(thridQuestion));
		assertEquals("Answer 3", questions.get(thridQuestion));
		
		
	}
	
	//display quiz questions
	@Test
	public void testQuizDisplay(){
		gameFrame.displayQuiz();
		
		//Make sure that the quiz display is not null
		assertNotNull(gameFrame.getQuizJDialog());
	}
	
	@Test
	public void testGradingQuiz(){
		
		//Test the starting ammo		
		//assertEquals(0, gameFrame.getAmmoCount());
		
		QuizDialog quizDialog = gameFrame.getQuizJDialog();
		//Grade fake quiz with 3 correct answers
		quizDialog.gradeQuiz();
		
		//Test ammo count again
		//assertEquals(3, gameFrame.getAmmoCount());
		
	}
}
