package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.GameFrame;
import game.Quiz;
import game.QuizDialog;

public class TrajectoryQuizTests {

	private GameFrame gameFrame;
	
	@Before
	public void init(){
		gameFrame = GameFrame.getInstance();
		ArrayList<String> imageFileNames = new ArrayList<String>();
		gameFrame.setConfigFiles("quizTest.txt", imageFileNames);
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
	
	@Test
	public void testGradingQuiz(){
		
		//Test the starting ammo		
		assertEquals(5, gameFrame.getControlGUI().getAmmoCount());
		
		Quiz quiz = new Quiz();
		//Grade fake quiz with the correct answer
		String answer1 = quiz.getAnswer1();
		String answer2 = quiz.getAnswer2();
		String answer3 = quiz.getAnswer3();
		
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		

		if(		answer1.equals("90") ||
				answer1.equals("180") ||
				answer1.equals("360") ||
				answer1.equals("yes") ||
				answer1.equals("no") ||
				answer1.equals("acute") ||
				answer1.equals("obtuse") ||
				answer1.equals("right") ||
				answer1.equals("8") ||
				answer1.equals("rudolph"))
			b1 = true;
		else
			b1 = false;
		
		if(		answer2.equals("90") ||
				answer2.equals("180") ||
				answer2.equals("360") ||
				answer2.equals("yes") ||
				answer2.equals("no") ||
				answer2.equals("acute") ||
				answer2.equals("obtuse") ||
				answer2.equals("right") ||
				answer2.equals("8") ||
				answer2.equals("rudolph"))
			b2 = true;
		else
			b2 = false;
		
		if(		answer3.equals("90") ||
				answer3.equals("180") ||
				answer3.equals("360") ||
				answer3.equals("yes") ||
				answer3.equals("no") ||
				answer3.equals("acute") ||
				answer3.equals("obtuse") ||
				answer3.equals("right") ||
				answer3.equals("8") ||
				answer3.equals("rudolph"))
			b3 = true;
		else
			b3 = false;
	
		
		assertTrue(b1 && b2 && b3);
		
	}
}
