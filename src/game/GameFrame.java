package game;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;

public class GameFrame extends JPanel{
	static final int TRAJECTORY_NUM_POINTS = 50;
	
	private static GameFrame gameInstance = new GameFrame();
	
	//Size of the gameBoard in Pixels
	public static final int gameXSize = 600;
	public static final int gameYSize = 600;
	
	private String quizFileName;
	private ArrayList<String> imageFileNames;
	
	private QuizDialog quiz;
	
	//projectile and trajectory variables variables (trajectory uses the same)
	double projectileForce = 20;
	double gravityForce = -9.8;
	double projectileAngle = 45;
	
	ArrayList<Point> trajectoryPoints;
	Point origin;
	
	
	private GameFrame() {
		imageFileNames = new ArrayList<String>();
		trajectoryPoints = new ArrayList<Point>();
		origin = new Point();
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
		
		double rad = d*180/Math.PI;
		
		//clears point list
		trajectoryPoints.clear();
		
		//figures out dt value so that the trajectory can cover twice the width (more than enough)
		double dt = ((float)this.getWidth() * 2.0 / (float)(TRAJECTORY_NUM_POINTS));
		
		double t = 0;
		int x, y;
		for(int i=0; i<TRAJECTORY_NUM_POINTS;i++){
			t = dt*i;
			
			//note y calculation accounts for image coordinate system
			x = (int) (origin.x + this.projectileForce*Math.cos(rad)*t);
			y = (int) (origin.y - this.projectileForce*Math.sin(rad)*t - 0.5*gravityForce*t*t);
			
			trajectoryPoints.add(new Point(x,y));
		}		
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
