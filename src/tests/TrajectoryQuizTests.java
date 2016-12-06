package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.GameFrame;
import game.Quiz;
import game.QuizDialog;

public class TrajectoryQuizTests {

	private static GameFrame gameFrame;
	private static Quiz testQuiz;
	
	@BeforeClass
	public static void setup(){
		gameFrame = GameFrame.getInstance();
		testQuiz = new Quiz();
	}
	
	//Load in quiz questions and answers
	@Test
	public void testQuizLoading() {
		try{
			testQuiz.loadQuizQuestions();
			testQuiz.loadQuizAnswers();
			assertTrue(true);
		} catch(FileNotFoundException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testGradingQuiz(){
		
		//Test the starting ammo		
		assertEquals(0, gameFrame.getAmmoCount());
		
		QuizDialog quizDialog = gameFrame.getQuizJDialog();
		//Grade fake quiz with the correct answer
		String answer = "Answer 1";
		
		assertTrue(quizDialog.gradeQuiz(answer));
		assertFalse(quizDialog.gradeQuiz("badAnswer"));
		
		//Test ammo count again. Should be full
		assertEquals(3, gameFrame.getAmmoCount());
		
	}
}
