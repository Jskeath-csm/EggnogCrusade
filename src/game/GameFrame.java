package game;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.ImageIO;

public class GameFrame extends JPanel{
	static final int TRAJECTORY_NUM_POINTS = 50;

	private static GameFrame gameInstance = new GameFrame();


	//Size of the gameBoard in Pixels
	public static final int gameXSize = 600;
	public static final int gameYSize = 600;

	//image variables
	private String quizFileName;
	private ArrayList<String> imageFileNames;
	private BufferedImage bucketImage;
	private BufferedImage catapultImage;
	private BufferedImage backdropImage;
	private BufferedImage coolSantaImage;
	private BufferedImage eggnongImage;
	private BufferedImage normalSantaImage;
	private BufferedImage buttonImage;

	//quiz and controll variables.
	private QuizDialog quiz;
	private ControlGUI controlGUI;

	//Boundary and Objects to interact with
	private ArrayList<Boundary> boundaryList;
	private ArrayList<Drawable> imageList;
	private Eggnog projectile;

	//projectile and trajectory variables variables (trajectory uses the same)
	double projectileForce = 100;
	double gravityForce = -9.8;
	double projectileAngle = 45;

	Timer gameTimer;

	ArrayList<Point> trajectoryPoints;
	Point origin;


	private GameFrame() {
		imageFileNames = new ArrayList<String>();	
		trajectoryPoints = new ArrayList<Point>();
		origin = new Point();
		controlGUI = new ControlGUI();
		setSize(1000,900);
		drawImages();
		gameTimer = new Timer(30, new GameTimer(this));
		gameTimer.start();

		//Setup starting locations and projectile parameters
		projectile = new Eggnog(0,320, true);
		origin.setLocation(projectile.getPosition().x, projectile.getPosition().y);
		projectile.setOrigin(origin);
		projectile.setParameters(projectileForce, gravityForce);


		boundaryList = new ArrayList<Boundary>();
		imageList = new ArrayList<Drawable>();
		imageList.add(projectile);
		Wall wall = new Wall(950,500,20,200);
		boundaryList.add(wall);
		imageList.add(wall);
		Bucket bucket1 = new Bucket(1100,600,100,100);
		Bucket bucket2 = new Bucket(1400,600,100,100);
		Bucket bucket3 = new Bucket(1700,600,100,100);
		boundaryList.add(bucket1);
		boundaryList.add(bucket2);
		boundaryList.add(bucket3);
		imageList.add(bucket1);
		imageList.add(bucket2);
		imageList.add(bucket3);
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

		double rad = d*Math.PI/180;

		//clears point list
		trajectoryPoints.clear();

		//figures out dt value so that the trajectory can cover twice the width (more than enough)
		//double dt = ((float)this.getWidth() * 2.0 / (float)(TRAJECTORY_NUM_POINTS));
		double dt = 1;
		double t = 0;
		int x, y;
		for(int i=0; i<TRAJECTORY_NUM_POINTS;i++){
			t = dt*i;

			//note y calculation accounts for image coordinate system
			x = (int) Math.round((origin.x + this.projectileForce*Math.cos(rad)*t));
			y = (int) Math.round((origin.y - this.projectileForce*Math.sin(rad)*t - 0.5*gravityForce*t*t));

			trajectoryPoints.add(new Point(x,y));
			//System.out.println(x + " | " + y);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public BufferedImage getButtonImage() {
		return buttonImage;
	}

	private class GameTimer implements ActionListener{

		private GameFrame gf;
		GameTimer(GameFrame gf){
			this.gf = gf;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			//TIMER ACTIONS HERE
			//System.out.println("Timer triggered");
			if(gf.getControlGUI().isFired()){
				gf.getControlGUI().resetFired();
				projectile.reset();
				projectile.setVisible();
				projectile.setMoving();
				projectile.setAngle(gf.getControlGUI().getAngle());
			}

			if(projectile.isMoving()){
				projectile.updateMotion();
				//check collision here too
			}

			gf.repaint();
		}

	};

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Clear the panel first

		//TODO - draw the images (buckets, wall, background etc.)
		//Draw only the static images
		g.drawImage(getCoolSantaImage(), 150, 0, null);
		g.drawImage(getBackdropImage(), 1000, 0, null);
		g.drawImage(getCatapultImage(), 0, 400, null);

		//Draw each image
		for(Drawable d: imageList) {
			d.draw(g);
		}



		//Draws the trajectory
		if(!projectile.isMoving()){
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.GREEN);

			calculateTrajectory(getControlGUI().getAngle());
			for(int i=0;i<trajectoryPoints.size()-1;i++)
				g2.drawLine(trajectoryPoints.get(i).x,trajectoryPoints.get(i).y,trajectoryPoints.get(i+1).x,trajectoryPoints.get(i+1).y);
		}



		repaint();
	}
}
