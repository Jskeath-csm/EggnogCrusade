package game;

import java.awt.Graphics;
import java.awt.Point;

public class Bucket extends Boundary implements Drawable {

	public Bucket() {
		pixelX = 0;
		pixelY = 0;
		sizeX = 0;
		sizeY = 0;
	}
	
	public Bucket(int pixelX, int pixelY, int sizeX, int sizeY) {
		this.pixelX = pixelX;
		this.pixelY = pixelY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public void setPosition(Point point) {
	}

	@Override
	public void draw(Graphics g) {
		if(isVisible) g.drawImage(GameFrame.getInstance().getBucketImage(), pixelX, pixelY, null);

	}
}
