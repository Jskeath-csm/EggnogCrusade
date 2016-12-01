package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.imageio.ImageIO;

public class GameFrame extends JPanel{
	static final int TRAJECTORY_NUM_POINTS = 50;
	
	private static GameFrame gameInstance = new GameFrame();
	

	//Size of the gameBoard in Pixels
	public static final int gameXSize = 600;
	public static final int gameYSize = 600;
	
	private String quizFileName;
	private ArrayList<String> imageFileNames;
	private BufferedImage bucketImage;
	private BufferedImage catapultImage;
	private BufferedImage backdropImage;
	private BufferedImage coolSantaImage;
	private BufferedImage eggnongImage;
	private BufferedImage normalSantaImage;
	private BufferedImage buttonImage;
	
	private QuizDialog quiz;
	private ControlGUI controlGUI;
	
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
		controlGUI = new ControlGUI();
		setSize(1000,900);
		drawImages();
	}
	
	public static GameFrame getInstance() {
		return gameInstance;
	}
	
	public void setConfigFiles(String quizFileNameIn, ArrayList<String> imageFileNamesIn) {
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

	public void loadImages() throws IOException {
		// TODO Auto-generated method stub
		for(int i = 0; i < imageFileNames.size(); i++) {
			String filename = imageFileNames.get(i);
			BufferedImage image = ImageIO.read(new File(filename));
			if(filename.contains("Bucket")) bucketImage = image;
			else if (filename.contains("Backdrop")) backdropImage = image;
			else if (filename.contains("Button")) buttonImage = image;
			else if (filename.contains("Catapult")) catapultImage = image;
			else if (filename.contains("CoolSanta")) coolSantaImage = image;
			else if (filename.contains("Nog")) eggnongImage = image;
			else if (filename.contains("NormalSanta")) normalSantaImage = image;
			System.out.println(image);
		}
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
	
	public BufferedImage getBucketImage() {
		return bucketImage;
	}

	public BufferedImage getCatapultImage() {
		return catapultImage;
	}

	public BufferedImage getBackdropImage() {
		return backdropImage;
	}

	public BufferedImage getCoolSantaImage() {
		return coolSantaImage;
	}

	public BufferedImage getEggnongImage() {
		return eggnongImage;
	}
	
	public ControlGUI getControlGUI() {
		return controlGUI;
	}
	
	public void drawImages() {
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GameFrame gf = GameFrame.getInstance();
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add("images/Backdrop.png");
		filenames.add("images/Bucket.png");
		filenames.add("images/Button.png");
		filenames.add("images/Catapult.png");
		filenames.add("images/CoolSanta.png");
		filenames.add("images/Nog.png");
		filenames.add("images/NormalSanta.png");
		gf.setConfigFiles("", filenames);
		try{
			gf.loadImages();
		} catch(IOException e) {
			e.printStackTrace();
		}
		frame.setSize(1920, 1080);
		frame.add(gf.getControlGUI(), BorderLayout.SOUTH);
		frame.add(gf, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public BufferedImage getButtonImage() {
		return buttonImage;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//TODO - draw the images (buckets, wall, background etc.)
		g.drawImage(getCoolSantaImage(), 100, 100, null);

		//Draws the trajectory
		for(int i=0;i<trajectoryPoints.size()-1;i++){
			g.setColor(Color.GREEN);
			g.drawLine(trajectoryPoints.get(i).x,
					trajectoryPoints.get(i).y,
					trajectoryPoints.get(i+1).x,
					trajectoryPoints.get(i+1).y);
		}
		
		repaint();
	}
}
