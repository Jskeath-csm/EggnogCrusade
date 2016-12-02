package game;

import java.awt.Graphics;
import java.awt.Point;

public class Eggnog implements Drawable {
	private double deltaT;
	private double theta;
	
	private int pixelX;
	private int pixelY;
	
	private boolean isVisible;
	
	public Eggnog(int pixelX, int pixelY, boolean isVisible) {
		super();
		this.pixelX = pixelX;
		this.pixelY = pixelY;
		this.isVisible = isVisible;
	}

	public void setDeltaT(double deltaT) {
		// TODO Auto-generated method stub
		
	}

	public void setAngle(double theta) {
		// TODO Auto-generated method stub
		
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void updateMotion() {
		// TODO Auto-generated method stub
		
	}

	public Point getPosition() {
		Point returnPoint = new Point();
		returnPoint.setLocation(pixelX, pixelY);
		return returnPoint;
	}

	public boolean getVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(GameFrame.getInstance().getEggnongImage(), pixelX, pixelY, null);
	}

}
