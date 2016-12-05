package game;

import java.awt.Graphics;
import java.awt.Point;

public class Eggnog implements Drawable {
	private double deltaT = 0.2;
	private double time = 0;
	private double theta = 0;
	double rad = 0;

	double projectileForce = 100;
	double gravityForce = -9.8;
	Point origin = new Point(0,0);
	
	
	
	private int pixelX = 0;
	private int pixelY = 0;

	private boolean isVisible;
	private boolean isMoving = false;

	public Eggnog(int pixelX, int pixelY, boolean isVisible) {
		super();
		this.pixelX = pixelX;
		this.pixelY = pixelY;
		this.isVisible = isVisible;
	}

	public void setDeltaT(double deltaT) {
		this.deltaT = deltaT;

	}

	public void setAngle(double theta) {
		this.theta = theta;
		this.rad = this.theta*Math.PI/180;
	}

	public void reset() {
		pixelX = origin.x;
		pixelY = origin.y;
		time = 0;
	}

	public void updateMotion() {
		time += deltaT;
		//note y calculation accounts for image coordinate system
		pixelX = (int) Math.round((origin.x + projectileForce*Math.cos(rad)*time));
		pixelY = (int) Math.round((origin.y -projectileForce*Math.sin(rad)*time - 0.5*gravityForce*time*time));

	}

	public Point getPosition() {
		Point returnPoint = new Point();
		returnPoint.setLocation(pixelX, pixelY);
		return returnPoint;
	}

	public boolean getVisible() {
		return isVisible;
	}
	
	public void setVisible(){
		isVisible = true;
	}
	
	public void setMoving(boolean b){
		isMoving = b;
	}
	
	public boolean isMoving(){
		return isMoving;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(GameFrame.getInstance().getEggnongImage(), 
				pixelX-GameFrame.getInstance().getEggnongImage().getWidth()/2,
				pixelY-GameFrame.getInstance().getEggnongImage().getHeight()/2, null);
	}
	
	public void setOrigin(Point p){
		origin = p;
	}

	public void setParameters(double projectileForce, double gravityForce){
		this.projectileForce = projectileForce;
		this.gravityForce = gravityForce;		
	}
	
}
