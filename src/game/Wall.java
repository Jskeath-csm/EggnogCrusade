package game;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Boundary implements Drawable{

	public Wall(int pixelX, int pixelY, int sizeX, int sizeY) {
		this.pixelX = pixelX;
		this.pixelY = pixelY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	@Override
	public boolean detectCollision(Boundary testingBoundary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(pixelX, pixelY, sizeX, sizeY);
		g.fillRect(pixelX, pixelY, sizeX, sizeY);
	}

}
