package game;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.ImageIO;

public class GameFrame extends JPanel{
	static final int TRAJECTORY_NUM_POINTS = 50;

	private static GameFrame gameInstance = new GameFrame();


	//Size of the gameBoard in Pixels
	public static final int gameSizeX = 1400;
	public static final int gameSizeY = 850;


	//image variables
	private String quizFileName;
	private ArrayList<String> imageFileNames;
	private BufferedImage bucketImage;
	private BufferedImage catapultImage;
	private BufferedImage backdropImage;
	private BufferedImage coolSantaImage;
	private BufferedImage eggnongImage;
	private BufferedImage normalSantaImage;
	
	private BufferedImage l1;
	private BufferedImage l2;
	private BufferedImage l3;
	
	private ImageIcon buttonImage;

	//quiz and control variables.
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

		//Filenames for the images
		imageFileNames = new ArrayList<String>();
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add("/images/Backdrop.png");
		filenames.add("/images/Bucket.png");
		filenames.add("/images/Catapult2.png");
		filenames.add("/images/CoolSanta2.png");
		filenames.add("/images/Nog2.png");
		filenames.add("/images/NormalSanta.png");

		setConfigFiles("", filenames);
		try{
			loadImages();
		} catch(IOException e) {
			e.printStackTrace();
		}


		trajectoryPoints = new ArrayList<Point>();
		origin = new Point();
		controlGUI = new ControlGUI();
		setSize(1000,900);
		drawImages();
		gameTimer = new Timer(30, new GameTimer(this));
		gameTimer.start();

		//Setup starting locations and projectile parameters
		projectile = new Eggnog(0+72,230+121, true);
		origin.setLocation(projectile.getPosition().x, projectile.getPosition().y);
		projectile.setOrigin(origin);
		projectile.setParameters(projectileForce, gravityForce);


		boundaryList = new ArrayList<Boundary>();
		imageList = new ArrayList<Drawable>();
		imageList.add(projectile);
		Wall wall = new Wall((int) (950/1920.0*gameSizeX),(int) (400/1080.0*gameSizeY),20,400);
		boundaryList.add(wall);
		imageList.add(wall);
		Bucket bucket1 = new Bucket((int)(1100.0/1920.0*gameSizeX),(int)(650/1080.0*gameSizeY),100,100);
		Bucket bucket2 = new Bucket((int)(1400/1920.0*gameSizeX),(int)(650/1080.0*gameSizeY),100,100);
		Bucket bucket3 = new Bucket((int)(1700/1920.0*gameSizeX),(int)(650/1080.0*gameSizeY),100,100);
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
		for(int i = 0; i < imageFileNames.size(); i++) {
			String filename = imageFileNames.get(i);
			URL url = getClass().getResource(filename);


			BufferedImage image = ImageIO.read(url);

			if(filename.contains("Bucket")) bucketImage = image;
			else if (filename.contains("Backdrop")) backdropImage = image;
			else if (filename.contains("Catapult")) catapultImage = image;
			else if (filename.contains("CoolSanta")) coolSantaImage = image;
			else if (filename.contains("Nog")) eggnongImage = image;
			else if (filename.contains("NormalSanta")) normalSantaImage = image;
		}
		
		URL url = getClass().getResource("/images/lightR.png");
		BufferedImage image = ImageIO.read(url);
		l1 = image;
		
		url = getClass().getResource("/images/lightG.png");
		image = ImageIO.read(url);
		l2 = image;
		
		url = getClass().getResource("/images/lightB.png");
		image = ImageIO.read(url);
		l3 = image;
	}


	public Eggnog getProjectile() {
		return projectile;
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

	public ImageIcon getButtonImage() {
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
			if(gf.getControlGUI().isFired() && !projectile.isMoving() && gf.getControlGUI().getAmmoCount() > 0){
				gf.getControlGUI().resetFired();
				projectile.reset();
				projectile.setVisible();
				projectile.setMoving(true);
				projectile.setAngle(gf.getControlGUI().getAngle());
				controlGUI.decreaseAmmoCount();
			} else {
				gf.getControlGUI().resetFired();
			}

			if(projectile.isMoving()){
				projectile.updateMotion();
				//check collision here too

				for(Boundary b: boundaryList){
					if(b.detectCollision(projectile)){
						//System.out.println("HIT");
						projectile.setMoving(false);
						projectile.reset();
						if (b instanceof Bucket) b.isVisible = false;
					}

					if(projectile.getPosition().x > gameSizeX|| projectile.getPosition().y > gameSizeY){
						System.out.println("HIT");
						projectile.setMoving(false);
						projectile.reset();
					}

				}
			}

			gf.repaint();
			//check to see if the game is over
			boolean gameOver = true;
			for(Boundary b: boundaryList) {
				if(b instanceof Bucket && b.isVisible) gameOver = false;
			}
			if(gameOver) {
				JDialog gameOverScreen = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
				gameOverScreen.setBounds(300, 300, 300, 300);
				gameOverScreen.setLayout(new GridLayout(3,1));
				JButton btn = new JButton("Reset");
				JButton btn2 = new JButton("Quit");
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						for(Boundary b: GameFrame.getInstance().boundaryList) b.isVisible = true;
					}
				});
				
				btn2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
					
				});
				gameOverScreen.setTitle("You won!");
				gameOverScreen.add(new JLabel("Congratulations! You won!"));
				gameOverScreen.add(btn);
				gameOverScreen.add(btn2);
				gameOverScreen.setVisible(true);
			}
		}

	};

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Clear the panel first

		//TODO - draw the images (buckets, wall, background etc.)
		//Draw only the static images
		g.drawImage(getCoolSantaImage(), (int) (150.0/1920.0*gameSizeX), 0, null);
		g.drawImage(getBackdropImage(), (int) (1000.0/1920.0*gameSizeX), 0, null);
		g.drawImage(getCatapultImage(), 0, (int) (400.0/1080.0*gameSizeY), null);

		//Draw each image
		for(Drawable d: imageList) {
			d.draw(g);
		}

		//Draws the trajectory
		if(!projectile.isMoving()){
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(new Color((float)0.0, (float)0.4, (float)0.0));

			calculateTrajectory(getControlGUI().getAngle());
			for(int i=0;i<trajectoryPoints.size()-1;i++){
				g2.drawLine(trajectoryPoints.get(i).x,trajectoryPoints.get(i).y,trajectoryPoints.get(i+1).x,trajectoryPoints.get(i+1).y);
				
				double slope = ((float)trajectoryPoints.get(i+1).y-(float)trajectoryPoints.get(i).y)/((float)trajectoryPoints.get(i+1).x-(float)trajectoryPoints.get(i).x);
				int dist = trajectoryPoints.get(i+1).x - trajectoryPoints.get(i).x;
				
				g.drawImage(l1, trajectoryPoints.get(i).x,trajectoryPoints.get(i).y,null);
				g.drawImage(l2, trajectoryPoints.get(i).x + (int)(dist*0.33),trajectoryPoints.get(i).y + (int)(slope*dist*0.33)-7,null);
				g.drawImage(l3, trajectoryPoints.get(i).x + (int)(dist*0.66),trajectoryPoints.get(i).y + (int)(slope*dist*0.66)-7,null);
			}
		}

		repaint();
	}


	//////////////MAIN/////////////////////////

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GameFrame gf = GameFrame.getInstance();	

		frame.setSize(gameSizeX, gameSizeY);
		frame.add(gf.getControlGUI(), BorderLayout.SOUTH);
		frame.add(gf, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}


