package game;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public class GameFrame {
	static final int TRAJECTORY_NUM_POINTS = 50;
	
	private static GameFrame gameInstance = new GameFrame();
	
	//Size of the gameBoard in Pixels
	public static final int gameXSize = 600;
	public static final int gameYSize = 600;
	
	private String quizFileName;
	private ArrayList<String> imageFileNames;
	
	private QuizDialog quiz;
	
	//projectile and trajectory variables variables (trajectory uses the same)
	float projectileForce = 20;
	float projectileAngle = 45;
	ArrayList<Point> trajectoryPoints;
	
	
	private GameFrame() {
		imageFileNames = new ArrayList<String>();
		trajectoryPoints = new ArrayList<Point>();
	}
	
	public static GameFrame getInstance() {
		return gameInstance;
	}
	
	public void setConfigFiles(String quizFileNameIn, String[] imageFileNamesIn) {
		this.quizFileName = quizFileNameIn;
		for(String s: imageFileNamesIn)
			imageFileNames.add(s);
	}

	public void calculateTrajectory(double d) {
		//clears point list
		trajectoryPoints.clear();
		
		
		
	}

	public ArrayList<Point> getTrajectory() {
		return trajectoryPoints;
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
