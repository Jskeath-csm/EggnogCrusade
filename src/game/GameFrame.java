package game;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public class GameFrame {
	private static GameFrame gameInstance = new GameFrame();
	
	//Size of the gameBoard in Pixels
	public static final int gameXSize = 600;
	public static final int gameYSize = 600;
	
	private String quizFileName;
	private ArrayList<String> imageFileNames = new ArrayList<String>();
	
	private QuizDialog quiz;
	
	private GameFrame() {
		
	}
	
	public static GameFrame getInstance() {
		return gameInstance;
	}
	
	public void setConfigFiles(String quizFileName, String[] imageFileNames) {

	}

	public void calculateTrajectory(double d) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Point> getTrajectory() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadImages() throws FileNotFoundException {
		// TODO Auto-generated method stub
		throw new FileNotFoundException();
	}

	public Eggnog getProjectile() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadQuiz() {
		// TODO Auto-generated method stub
		
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, String> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	public void displayQuiz() {
		// TODO Auto-generated method stub
		
	}

	public QuizDialog getQuizJDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAmmoCount() {
		// TODO Auto-generated method stub
		return null;
	}
}
